include_guard()

################################################################################
# Function:
#   IterateDirectories ()
# Parameters:
#     directories          - Output variable to contain the enumerated
#                            directories
#     sourceDirectory      - Directory to look into
#     libraryNamePrefix    - Prefix of the library directories to include
################################################################################

function (EnumerateDirectories directories sourceDirectory libraryNamePrefix)
  if (NOT IS_DIRECTORY "${sourceDirectory}")
    message (FATAL_ERROR "Source file directory not found: ${sourceDirectory}")
  endif ()
  
  file (GLOB __Paths RELATIVE "${sourceDirectory}" "${sourceDirectory}/*")
  
  foreach (__Path ${__Paths})
    if (IS_DIRECTORY "${sourceDirectory}/${__Path}")
      string(REGEX MATCH "^${libraryNamePrefix}" __IsLibraryDirectory "${__Path}")
      if (__IsLibraryDirectory)
          list (APPEND __Subdirectories "${__Path}")
      endif ()
    endif ()
  endforeach ()

  set (${directories} ${__Subdirectories} PARENT_SCOPE)
endfunction ()

################################################################################
# Function:
#   IterateDirectories ()
# Parameters:
#     directories          - Output variable to contain the enumerated
#                            directories
#     sourceDirectory      - Directory to look into
#     libraryNamePrefix    - Prefix of the library directories to include
################################################################################

# Get a list of all directories with the specified index
function (GetLibraryDirectory
  libraryDirectory librarySearchPath sourceLibraryPrefix
)
  # Get a list of candidate source paths
  EnumerateDirectories(__LibraryDirectories
    "${librarySearchPath}" "${sourceLibraryPrefix}"
  )

  # Only one source directory is allowed
  list (LENGTH __LibraryDirectories __LibraryDirectoriesLength)
  if (__LibraryDirectoriesLength GREATER 1)
    set (__ErrorMesage
      "Ambigous source directories for library prefix - \
       \"${sourceLibraryPrefix}\". Only one source directory is allowed:\n"
      )
    foreach (__libraryDirectory ${__LibraryDirectories})
      string (APPEND __ErrorMesage
        "    ${__libraryDirectory}\n"
      )
    endforeach ()
    message (FATAL_ERROR "${__ErrorMesage}")
  endif()

  # Get the source directory name
  list (GET __LibraryDirectories 0 __LibraryDirectory)
  set (${libraryDirectory} "${__LibraryDirectory}" PARENT_SCOPE)
endfunction ()

################################################################################
# Macro:
#   SetFindPackageConfiguration ()
# Parameters:
#   libraryNamePrefix      - Prefix of the library directories to include
################################################################################
macro (SetFindPackageConfiguration libraryPrefix)
  set (.ANDROID_AddLibraryAs_SHARED_${libraryPrefix}
    ${.UseSharedLibrary_${libraryPrefix}}
  )
  set (.ANDROID_LibraryArtifactsPath_${libraryPrefix}
    "${.LibraryArtifactsPath_${libraryPrefix}}/${__VariantSubdir}"
  )
  set (__${libraryPrefix}_RootDir 
    "${.ExternalLibrariesRootDir}/${libraryPrefix}"
  )
  GetLibraryDirectory(__LibraryDirectory
    "${__${libraryPrefix}_RootDir}" "${libraryPrefix}"
  )
  set (.ANDROID_SourcePath_${libraryPrefix}
    "${__${libraryPrefix}_RootDir}/${__LibraryDirectory}"
  )
  unset (__${libraryPrefix}_RootDir)
endmacro ()

################################################################################
