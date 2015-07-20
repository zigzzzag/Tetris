package org.zgame.utils;

/**
 * Created by mnikiforov on 20.07.2015.
 */
public class FPSData {

    // Milliseconds between updates when not running the game at max speed
    private static final long SLOW_WAIT_TIME_BETWEEN_UPDATES = 10;
    // Milliseconds between updates when running the game at max speed,
    // in this case, no waiting at all with a time of 0
    private static final long FAST_WAIT_TIME_BETWEEN_UPDATES = 0;

    private long currentUpdateSpeed;
    private long oldTime;
    private long timeSinceLastFPSCalculation;
    private int frames;
    // Holds the latest calculated value of frames per second
    private int fps;


    /**
     * Constructs a new GameData. GameData's constructor creates and
     * schedules an initial updating task
     */
    public FPSData() {
        // Initialize the time, fps, and other variables
        currentUpdateSpeed = SLOW_WAIT_TIME_BETWEEN_UPDATES;
        oldTime = System.nanoTime();
        timeSinceLastFPSCalculation = 0;
        frames = 0;
        fps = 0;
    }

    /**
     * Resets the last known time of the last update,
     * useful for when there has not been an update in a long time,
     * or when the application first starts where there may have been
     * some time the application spent doing other things between when
     * updating starts and the elaboration of the object.
     */
    public synchronized void resetTimeOfLastUpdate() {
        oldTime = System.nanoTime();
    }

    /**
     * Retrieves the time between updates to wait
     */
    public synchronized long getCurrentWaitTimeBetweenUpdates() {
        return currentUpdateSpeed;
    }

    /**
     * Returns the latest calculated frames per second
     */
    public synchronized int getFPS() {
        return fps;
    }

    /**
     * Start updating the game data at a slow update speed
     */
    public synchronized void updateGameDataAtSlowRate() {
        currentUpdateSpeed = SLOW_WAIT_TIME_BETWEEN_UPDATES;
    }

    /**
     * Start updating the game data at a fast update speed
     */
    public synchronized void updateGameDataAtFastRate() {
        currentUpdateSpeed = FAST_WAIT_TIME_BETWEEN_UPDATES;
    }

    /**
     * Updates any objects that need to know how much time has elapsed to
     * update any needed movements, animations, or events.
     */
    public synchronized void updateData() {
        // Calculating a new fps value every second
        if (timeSinceLastFPSCalculation >= 1000000000) {
            fps = frames;
            timeSinceLastFPSCalculation = timeSinceLastFPSCalculation - 1000000000;
            frames = 0;
        }

        long elapsedTime = System.nanoTime() - oldTime;
        oldTime += elapsedTime;
        timeSinceLastFPSCalculation += elapsedTime;
        frames++;
    }

    /**
     * Sleeps the current thread if there's still sometime the application
     * can wait for until the time the next update is needed.
     *
     * @param nanoTimeCurrentUpdateStartedOn Time that current update
     *                                       started
     */
    public void waitUntilNextUpdate(long nanoTimeCurrentUpdateStartedOn) {
        // Only sleep to maintain the update speed if speed is higher than
        // zero, because Thread.sleep(0) is not defined on what that
        // exactly does
        long currentUpdateSpeed = getCurrentWaitTimeBetweenUpdates();
        if (currentUpdateSpeed > 0) {
            // This could be more accurate by sleeping what's needed on
            // average for the past few seconds
            long timeToSleep = currentUpdateSpeed - ((System.nanoTime() - nanoTimeCurrentUpdateStartedOn) / 10000000);
            // If the speed of updating was so slow that it's time for
            // the next update, then choose 0
            timeToSleep = Math.max(timeToSleep, 0);
            // Again, avoiding Thread.sleep(0)
            if (timeToSleep > 0) {
                try {
                    Thread.sleep(timeToSleep);
                } catch (InterruptedException e) {
                    // It's okay if we're interrupted, program will just run
                    // faster.
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
