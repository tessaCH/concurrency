package com.S2ThreadCreation;

public class T1ThreadObj {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("In Thread: " + Thread.currentThread().getName());
            throw new RuntimeException("Error!");
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("An error in thread: " + t.getName() + " error: " + e.getMessage());
        });
        System.out.println("Before Thread: " + Thread.currentThread().getName());
        thread.setName("Printer");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        Thread.sleep(10);
        System.out.println("After Thread: " + Thread.currentThread().getName());
        
    }
}
