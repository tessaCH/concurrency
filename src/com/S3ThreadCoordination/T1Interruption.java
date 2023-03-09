package com.S3ThreadCoordination;

public class T1Interruption {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.interrupt();
    }
    
    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Exiting blocking thread");
            }
        }
    }
}
