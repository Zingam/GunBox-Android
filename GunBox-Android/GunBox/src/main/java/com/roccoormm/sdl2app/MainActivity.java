package com.roccoormm.sdl2app;

import android.os.Bundle;

import org.libsdl.app.HIDDeviceManager;
import org.libsdl.app.SDLActivity;

/**
 * A sample wrapper class that just calls SDLActivity
 */

public class MainActivity extends SDLActivity {
    /**
     * This method is called by SDL before starting the native application thread.
     * It can be overridden to provide the arguments after the application name.
     * The default implementation returns an empty array. It never returns null.
     *
     * @return arguments for the native application.
     */
    @Override
    protected String[] getArguments() {
        return new String[]{
                //"--renderer:", "Vulkan"
                "--renderer:", "OpenGL"
        };
    }

    /**
     * This method is called by SDL before loading the native shared libraries.
     * It can be overridden to provide names of shared libraries to be loaded.
     * The default implementation returns the defaults. It never returns null.
     * An array returned by a new implementation must at least contain "SDL2".
     * Also keep in mind that the order the libraries are loaded may matter.
     *
     * @return names of shared libraries to be loaded (e.g. "SDL2", "main").
     */
    @Override
    protected String[] getLibraries() {
        return new String[]{
                "GunBox_Game"
        };
    }

    // Setup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // In SDLActivity.java define:
        //   protected static boolean mHIDAPIEnabled = true;
        // In SDLActivity.java in:
        //   protected void onCreate(Bundle savedInstanceState)
        // modify:
        //   mHIDDeviceManager = HIDDeviceManager.acquire(this);
        // to:
        //   if (SDLActivity.mHIDAPIEnabled) {
        //        mHIDDeviceManager = HIDDeviceManager.acquire(this);
        //   }

        // Disable HIDAPI library usage
        SDLActivity.mHIDAPIEnabled = false;

        super.onCreate(savedInstanceState);
    }
}
