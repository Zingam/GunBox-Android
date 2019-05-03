import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins { id("com.android.application") }

android {
    compileSdkVersion(Versions.compileSdk)



    defaultConfig {
        applicationId = Versions.applicationId

        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        ndk {
            // Limiting to a smaller set of  ABIs to save time while testing:
            setAbiFilters(Deps.abiFilters)
        }

        externalNativeBuild {
            cmake {
                // Passes optional arguments to CMake:
                //   External library sources root dir
                arguments.add("-D.ExternalLibrariesRootDir:STRING=${rootProject.extra["externalLibrariesDirectory"]}")
                //   Search paths for the external library binaries
                arguments.add("-D.LibraryArtifactsPath_FreeType2:STRING=${rootProject.extra["nativeLibraryArtifactsOutputDirectory"]}/${Deps.freetype2_ModuleName}")
                arguments.add("-D.LibraryArtifactsPath_SDL2:STRING=${rootProject.extra["nativeLibraryArtifactsOutputDirectory"]}/${Deps.sdl2_ModuleName}")
                //   Library type to link to
                arguments.add("-D.UseSharedLibrary_FreeType2:BOOL=${Deps.freetype2_BuildAsShared}")
                //     When linking a static SDL2 library to a shared library SDL_main gets stripped
                //     thus we compile all libraries as static and link them with:
                //       $<$<PLATFORM_ID:Android>:-Wl,--whole-archive>
                //       GunBox_Engine_main
                //       $<$<PLATFORM_ID:Android>:-Wl,--no-whole-archive>
                arguments.add("-D.UseSharedLibrary_SDL2:BOOL=${Deps.sdl2_BuildAsShared}")
                arguments.add("-Doption_EngineLibraryAs_SHARED:BOOL=${Options.engineLibrary_BuildAsShared}")

            }
        }
    }

    sourceSets {
        getByName("debug") {
            // Gradle includes libraries in the following path as dependencies
            // of your CMake or ndk-build project so that they are packaged in
            // your app’s APK.
            val ndkPath = System.getenv("ANDROID_NDK")
            if (null != ndkPath)
            {
                jniLibs.srcDir("$ndkPath/sources/third_party/vulkan/src/build-android/jniLibs/")
            }
            else
            {
                logger.error("Environment variable \"ANDROID_NDK\" is not defined. Vulkan layers won't be available.")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            externalNativeBuild {
                cmake {
                    // Debug options
                    arguments.add("-Doption_CheckGraphicsApiCalls:BOOL=${Options.checkGraphicsApiCalls}")
                    arguments.add("-Doption_EnableLoggingLevel_Verbose:BOOL=${Options.enableLoggingLevel_Verbose}")
                }
            }
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    externalNativeBuild {
        cmake {
            // Tells Gradle to put outputs from external native
            // builds in the path specified below.
            setBuildStagingDirectory("${rootProject.extra["nativeStagingDirectory"]}/${project.name}")

            // Tells Gradle to find the root CMake build script in the same
            // directory as the module's build.gradle.kts.kts file. Gradle requires this
            // build script to add your CMake project as a build dependency and
            // pull your native sources into your Android project.
            setPath("${rootProject.extra["appProjectDirectory"]}/CMakeLists.txt")
            setVersion(Versions.cmake)
        }
    }

    // Specification of the copy tasks
    val apkExportCopySpec: CopySpec = copySpec {
        applicationVariants.all(object : Action<ApplicationVariant> {
            override fun execute(variant: ApplicationVariant) {
                variant.outputs.all(object : Action<BaseVariantOutput> {
                    override fun execute(output: BaseVariantOutput) {
                        val output = output as BaseVariantOutputImpl
                        from(output.outputFile.parent) {
                            include("*.apk")
                        }
                    }
                })
            }
        })
    }

    // Copy existing Android packages (*.apk)
    tasks.register("copy_apk", Copy::class) {
        group = "Export"

        with(apkExportCopySpec)
        into("${rootProject.extra["apkExportDirectory"]}")
    }

    // Build all variants and export the generated Android packages (*.apk)
    tasks.register("exportAll_apk", Copy::class) {
        dependsOn("assemble")
        group = "Export"

        with(apkExportCopySpec)
        into("${rootProject.extra["apkExportDirectory"]}")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // External libraries - the libraries will be build and added to the .apk
    implementation(project(":${Deps.freetype2_ModuleName}"))
    implementation(project(":${Deps.sdl2_ModuleName}"))
}
