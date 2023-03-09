package com.ppm.java.S3ThreadCoordination;

/*
    The application won't stop by the interruption.
    We need to add a return; statement inside the catch (InterruptedException e) block to stop the application.
 */
public class T2Q2CaughtInterruption {
    public static void main(String [] args) {
        Thread thread = new Thread(new SleepingThread());
        thread.start();
        thread.interrupt();
    }

    private static class SleepingThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
