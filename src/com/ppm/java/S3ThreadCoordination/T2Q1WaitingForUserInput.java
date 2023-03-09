package com.ppm.java.S3ThreadCoordination;

import java.io.IOException;

/*
    The only ways to stop the application are:
     1. For the user to type int the letter 'q'.
     2. Set thread.setDaemon(true); in the main method, before starting the thread
     3. Forcefully kill the application
 */
public class T2Q1WaitingForUserInput {
    public static void main(String [] args) {
        Thread thread = new Thread(new WaitingForUserInput());
        thread.setName("InputWaitingThread");
        thread.start();
    }

    private static class WaitingForUserInput implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    char input = (char) System.in.read();
                    if(input == 'q') {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println("An exception was caught " + e);
            };
        }
    }
}
