cmake_minimum_required (VERSION 3.10)

project("FreeType2")

include("${CMAKE_CURRENT_LIST_DIR}/../__cmake/ExternalLibraries.cmake")

# Get a list of all directories with the specified prefix
GetLibraryDirectory(__LibraryDirectory "${CMAKE_CURRENT_LIST_DIR}" "FreeType2")

# Add the sources
add_subdirectory ("${__LibraryDirectory}")
set_target_properties (freetype
  PROPERTIES
    ARCHIVE_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
    LIBRARY_OUTPUT_DIRECTORY
      "${.LibraryArtifactsOutputDirectory}/${CMAKE_BUILD_TYPE}/${ANDROID_ABI}"
)
