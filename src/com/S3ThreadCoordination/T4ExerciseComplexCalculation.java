package com.S3ThreadCoordination;

import java.math.BigInteger;

public class T4ExerciseComplexCalculation {
    public static void main(String[] args) throws InterruptedException {
        T4ExerciseComplexCalculation p = new T4ExerciseComplexCalculation();
        BigInteger result = p.calculateResult(new BigInteger("10"), new BigInteger("2"),new BigInteger("8"), new BigInteger("2"));
        System.out.println(result);
    }
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */
        PowerCalculatingThread t1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread t2 = new PowerCalculatingThread(base2, power2);

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        result = t1.getResult().add(t2.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }
        
        @Override
        public void run() {
           /*
           Implement the calculation of result = base ^ power
           */
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0 ; i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}
