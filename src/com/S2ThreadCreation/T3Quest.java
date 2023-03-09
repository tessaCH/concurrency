package com.S2ThreadCreation;

public class T3Quest {
    public static void main(String[] args) {
        Thread thread1 = new TaskThread1();
        thread1.start();
        
        Thread thread2 = new Thread(new Task2());
        thread2.start();
    }

    public static class TaskThread1 extends Thread {
        @Override
        public void run(){
            System.out.println("Hello from new thread1");
        }
    }


    public static class Task2 implements Runnable {
        @Override
        public void run(){
            System.out.println("Hello from new thread2");
        }
    }
}
