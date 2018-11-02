GunBox-Android
==============

```
    printf("Android Studio gradle scripts for GunBox project")
```

## Prerequisites

### Software
1. Android NDK r18
2. Android Studio 3.2
3. C++17 compiler
4. CMake 3.12 (installed externally)

### Third Party Libraries
1. FreeType 2.9.1 (or newer)
2. SDL 2.0.8 (or newer)

## Configuring the Build

1. Put *FreeType* library in the directory *ExternalLibraries/FreeType2*
2. Put *SDL* library in the directory *ExternalLibraries/SDL2*
3. Clone GunBox repository next to the Android project directory, e.g. folder 
   structure:
```
 |
 +- GunBox                       <--- GunBox sources
    GunBox-Android               <--- Android app project
     +- ExternalLibraries
         +- SDL2                 <--- SDL2 sources        
        GunBox-Android           <--- Android project
        GunBoxProject
         +- GunBox               <--- Symlink or source files
            MakeSymlink.bat      <--- Use to create a symlink
```
4. Create a symlink `GunBox` (or alternatively put the **GunBox** project source files inside)

and create a symlink to

## Notes
The libraries are compile as static and linked with:

to prevent `SDL_main` from being stripped.
