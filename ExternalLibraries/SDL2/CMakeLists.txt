cmake_minimum_required (VERSION 3.10)

add_subdirectory ("SDL2")
set_target_properties (SDL2
  PROPERTIES
    ARCHIVE_OUTPUT_DIRECTORY
     "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
    LIBRARY_OUTPUT_DIRECTORY
     "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
)
set_target_properties (SDL2main
  PROPERTIES
    ARCHIVE_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
)
set_target_properties (SDL2-static
  PROPERTIES
    ARCHIVE_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
)
