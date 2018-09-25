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

    // Plugins
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
}
