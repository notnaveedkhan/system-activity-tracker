package com.activitytracker.threads;

import com.activitytracker.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ScreenActivityTracker extends Thread {

    private final Logger log;
    private final String fileName;
    private final String filePath;
    private final String fileExtension;
    private String fileFullPath;
    private boolean executing;
    private int delay;

    public ScreenActivityTracker(String fileName, String filePath, String fileExtension) {
        this.fileName = fileName;
        String day = Utils.getDate();
        if (!filePath.endsWith("\\")) {
            this.filePath = filePath + "\\" + day + "\\";
        } else {
            this.filePath = filePath + day + "\\";
        }
        if (!new File(this.filePath).exists()) {
            boolean created = new File(this.filePath).mkdirs();
            if (!created) {
                throw new IllegalArgumentException("Could not create directory: " + this.filePath);
            }
        }
        this.fileExtension = fileExtension;
        this.executing = true;
        this.delay = Utils.getRandomDelay(1, 5);
        this.log = Logger.getLogger(this.getClass().getName());
    }

    public void run() {
        while (isExecuting()) {
            log.info("Waiting for " + (delay * 60 * 1000) + " seconds...");
            Utils.sleep((long) delay * 60 * 1000);
            log.info("Taking screenshot...");
            takeScreenShot();
            String timeStamp = Utils.getTimeStamp();
            log.info("Screenshot taken successfully at " + timeStamp);
            log.info("ScreeShot saved at " + fileFullPath);
        }
    }

    private void takeScreenShot() {
        String timeStamp = Utils.getTimeStamp();
        fileFullPath = filePath + fileName + " " + timeStamp + "." + fileExtension;
        try {
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            File file = new File(fileFullPath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IllegalArgumentException("Could not create file: " + fileFullPath);
                }
            }
            ImageIO.write(image, fileExtension, new File(fileFullPath));
        } catch (AWTException | IOException e) {
            log.severe("Error while taking screenshot: " + e.getMessage());
        }
    }

    public boolean isExecuting() {
        return executing;
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
