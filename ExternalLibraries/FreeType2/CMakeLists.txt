cmake_minimum_required (VERSION 3.10)

add_subdirectory ("FreeType2")
set_target_properties (freetype
  PROPERTIES
    ARCHIVE_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
    LIBRARY_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
)
