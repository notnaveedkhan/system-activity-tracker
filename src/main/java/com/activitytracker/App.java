package com.activitytracker;


import com.activitytracker.threads.ScreenActivityTracker;

public class App {

    public static void main( String[] args ) {
        new ScreenActivityTracker(
                "ScreenActivity",
                System.getProperty("user.home") + "\\SystemLogs\\crash logs\\Screenshots\\",
                "png"
        ).start();
    }

}
