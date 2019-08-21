// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Build directories location
val buildOutputRootDirectory = "${rootDir.parent}/__build-output/${rootProject.name}"
val gradleBuildOutputDirectory = "${buildOutputRootDirectory}/GradleBuilds"

fun canonizePath(path: String): String {
    return file(path).canonicalPath.replace('\\', '/')
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Deps.androidGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

// The set containing this project and its subprojects.
allprojects {
    repositories {
        google()
        jcenter()
    }

    buildDir = File(canonizePath("${gradleBuildOutputDirectory}/${project.name}"))


}

// This block encapsulates custom properties and makes them available to all
// modules in the project.
extra.apply {
    // *.apk output directory
    set("apkExportDirectory", canonizePath("${rootDir.parent}/__apk-exports"))
    // External libraries source location
    set("externalLibrariesDirectory", canonizePath("${rootDir.parent}/ExternalLibraries/"))
    // Main project source location
    set("appProjectDirectory", canonizePath("${rootDir.parent}/GunBox/GunBox/Gunbox"))
    // Location for library build artifacts
    set("nativeLibraryArtifactsOutputDirectory", canonizePath("${buildOutputRootDirectory}/__LibraryBuildArtifacts"))
    // Build directories location
    set("nativeStagingDirectory", canonizePath("${buildOutputRootDirectory}/NativeBuilds/"))
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    delete(buildOutputRootDirectory)
}
