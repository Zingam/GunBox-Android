const val usingStaticLibraries = false

object Options {
    const val engineLibrary_BuildAsShared = "NO"
    const val checkGraphicsApiCalls = "YES"
    const val enableLoggingLevel_Verbose = "YES"
}

object Versions {
    const val applicationId = "com.roccoormm.sdl2app.GunBox"

    // Application
    const val versionCode = 1
    const val versionName = "1.0.0"

    // Build configuration
    const val compileSdk = 28
    const val minSdk = 28
    const val targetSdk = 28

    // Plugins
    const val androidGradlePlugin = "4.2.0-alpha03"

    // CMake
    const val cmake = "3.18.0+"
}

object Deps {
    // Application
    val abiFilters = listOf("arm64-v8a") // Android platforms to compile for

    // Libraries
    //   FreeType2
    var freetype2_BuildAsShared = "YES"             // FreeType2 library type (SHARED)
    const val freetype2_ModuleName = "libfreetype2" // The name of the FreeType2 module
    //   SDL2
    var sdl2_BuildAsShared = "YES"                  // SDL2 library type (SHARED)
    const val sdl2_ModuleName = "libsdl2"           // The name of the SDL2 module
    var sdl2_Targets = "SDL2"                       // SDL2 library targets to build
    // Location of SDL2 Java source files
    const val sdl2_LibraryDirectoryName = "SDL2"

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
        println("    Building FreeType2 as shared library: $freetype2_BuildAsShared")
        println("    Building SDL2 as shared library:      $sdl2_BuildAsShared")
        println("    Building SDL2 targets:                $sdl2_Targets")
    }
}
