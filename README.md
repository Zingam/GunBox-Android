GunBox-Android
==============

```
    reLogI("Android Studio gradle scripts for GunBox project");
```

## Prerequisites

### Software
1. Android NDK r21
2. Android Studio 4.2
3. C++17 compiler
4. CMake 3.18.0 (installed externally)

### Third Party Libraries
1. FreeType 2.9.1 (or newer)
2. SDL 2.0.10 (or newer)

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
         +- FreeType2
              +- FreeType2       <--- FreeType2 sources
              +- CMakeLists.txt
         +- SDL2
              +- SDL2            <--- SDL2 sources
              +- CMakeLists.txt 
        GunBox-Android           <--- Android project
        GunBoxProject
         +- GunBox               <--- Symlink or source files
            MakeSymlink.bat      <--- Use to create a symlink
```
4. Create a symlink `GunBox` (or alternatively put the **GunBox** project source
   files inside) to the **GunBox** project source directory.

## Notes

1. To prevent `SDL_main` from being stripped. The libraries are compile as
   static and linked with:
```
  $<$<PLATFORM_ID:Android>:-Wl,--whole-archive>
  GunBox_Engine_main
  $<$<PLATFORM_ID:Android>:-Wl,--no-whole-archive>
```
2. `Deps.sdl2_LibraryDirectoryName` is the directory containing the SDL2 sources

* In `SDLActivity.java` modify the method `onCreate` as described in the
  overridden method in `MainActivity.java`.