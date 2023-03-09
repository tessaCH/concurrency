package com.S2ThreadCreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T2RacingThreads {
    private static int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random rand = new Random();
        Vault vault = new Vault(rand.nextInt(MAX_PASSWORD));
        
        List<Thread> threads = new ArrayList<>();        
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());
        
        threads.forEach(thread -> thread.start());
    }

    private static class Vault {
        private int password;
        public Vault(int password) {
            this.password = password;
            System.out.println("Password: " + password);
        }
        
        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {                
            }
            return this.password == guess;
        }
    }

    private static abstract class HackThread extends Thread {
        protected Vault vault;
        
        public HackThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting " + this.getName());
            super.start();
        }
    }
    
    private static class AscendingHackerThread extends HackThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }
    
    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {                    
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
