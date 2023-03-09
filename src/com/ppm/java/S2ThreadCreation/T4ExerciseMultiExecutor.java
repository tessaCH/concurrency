package com.ppm.java.S2ThreadCreation;

import java.util.List;

public class T4ExerciseMultiExecutor {

    // Add any necessary member variables here
    private List<Runnable> tasks;
    //better: private final List<Runnable> tasks;
    /*
     * @param tasks to executed concurrently
     */
    public T4ExerciseMultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        tasks.forEach(task -> task.run());
    }
    
    /* sample:
    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
        }

        for(Thread thread : threads) {
            thread.start();
        }
    }
     */
}