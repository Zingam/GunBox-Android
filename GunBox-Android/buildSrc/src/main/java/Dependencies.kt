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

    // Libraries
    //   FreeType2
    var freetype2_BuildAsShared = "YES"    // FreeType2 library type (SHARED)
    //   SDL2
    var sdl2_BuildAsShared = "YES"         // SDL2 library type (SHARED)
    var sdl2_Targets = "SDL2"              // SDL2 library targets to build
    init {
        if (usingStaticLibraries) {
            freetype2_BuildAsShared = "NO" // FreeType2 library type (STATIC)
            sdl2_BuildAsShared = "NO"      // SDL2 library type (STATIC)
            sdl2_Targets = "SDL2-static"   // SDL2 library targets to build
        }
    }

    // Plugins
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    // Final report
    init {
        println("    Building FreeType2 as static library: $freetype2_BuildAsShared")
        println("    Building SDL2 as static library:      $sdl2_BuildAsShared")
        println("    Building SDL2 targets:                $sdl2_Targets")
    }
}