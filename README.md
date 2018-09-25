GunBox-Android
==============

```
    printf("Android Studio gradle scripts for GunBox project")
```

## Prerequisites
1. Put *SDL2 library* in the directory *ExternalLibraries/SDL2*
2. Clone GunBox repository next to the Android project directory, e.g. folder 
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
3. Create a symlink `GunBox` (or alternatively put the **GunBox** project source 
   files inside)

and create a symlink to

## Notes
The libraries are compile as static and linked with:

to prevent `SDL_main` from being stripped.
