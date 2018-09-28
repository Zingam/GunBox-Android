val usingStaticLibraries = false

object Versions {
    // Application
    const val versionCode = 1
    const val versionName = "1.0.0"

    // Build configuration
    const val compileSdk = 27
    const val minSdk = 26
    const val targetSdk = 27

    // Plugins
    const val androidGradlePlugin = "3.2.0"
}

object Deps {
    // Application
    val abiFilters = listOf("arm64-v8a") // Android platforms to compile for
    var sdl2Targets = "SDL2"             // SDL2 library targets to build
    var useStaticSDL2Library = "NO"      // SDL2 library type (SHARED)
    init {
        if (usingStaticLibraries) {
            sdl2Targets = "SDL2-static"  // SDL2 library targets to build
            useStaticSDL2Library = "YES" // SDL2 library type (STATIC)
        }
    }

    // Plugins
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    // Final report
    init {
        println("  SDL2 static library: $useStaticSDL2Library")
        println("  SDL2 targets:        $sdl2Targets")
    }
}
