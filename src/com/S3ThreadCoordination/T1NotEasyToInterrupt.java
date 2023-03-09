package com.S3ThreadCoordination;

import java.math.BigInteger;

public class T1NotEasyToInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("10000000")));
        // NOTICE: LongComputationTask won't be stopped by simply 'interrupt'
        // Ways to stop:
        // 1. Add thread.setDaemon(true); => that would end the task while main thread finished        
        thread.start();
        Thread.sleep(2);
        // 2. Add  Thread.currentThread().isInterrupted() in thread task to check if any interruption calls 
        thread.interrupt();
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            System.out.println(base+"^"+power+" = "+ pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation when i = " + i);
                    return result;
                }
                result = result.multiply(base);
            }
            return result;
        }
    }
}
