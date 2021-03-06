plugins { id("com.android.library") }

android {
    compileSdkVersion(Versions.compileSdk)



    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"

        sourceSets {
            getByName("main").java.srcDirs("${rootProject.extra["externalLibrariesDirectory"]}/SDL2/${Deps.sdl2_LibraryDirectoryName}/android-project/app/src/main/java")
        }

        ndk {
            ndkVersion = Versions.ndkVersion
            // Limiting to a smaller set of  ABIs to save time while testing:
            abiFilters.addAll(Deps.abiFilters)
        }

        externalNativeBuild {
            cmake {
                // Passes optional arguments to CMake:
                //   Location on your host where CMake puts the LIBRARY
                //   target files when built
                arguments.add("-D.LibraryArtifactsOutputDirectory:STRING=${rootProject.extra["nativeLibraryArtifactsOutputDirectory"]}/${project.name}")

                // Specifies the library and executable targets from your CMake
                // project that Gradle should build.
                targets.add(Deps.sdl2_Targets)
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    externalNativeBuild {
        cmake {
            // Tells Gradle to put outputs from external native
            // builds in the path specified below.
            buildStagingDirectory = File("${rootProject.extra["nativeStagingDirectory"]}/${project.name}")

            // Tells Gradle to find the root CMake build script in the same
            // directory as the module's build.gradle file. Gradle requires this
            // build script to add your CMake project as a build dependency and
            // pull your native sources into your Android project.
            path = File("${rootProject.extra["externalLibrariesDirectory"]}/SDL2/CMakeLists.txt")
            setVersion(Versions.cmake)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}

