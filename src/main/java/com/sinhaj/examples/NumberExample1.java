package com.sinhaj.examples;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ajaysinha on 1/24/14.
 */
public class NumberExample1 {

    public static void main(String[] args) throws InterruptedException {
        PipedWriter evenWriter = new PipedWriter();
        PipedWriter oddWriter = new PipedWriter();
        NumberWriter evenNumberWriter = new NumberWriter(10, true, evenWriter);
        NumberWriter oddNumberWriter = new NumberWriter(10, false, oddWriter);
        NumberReader numberReader = new NumberReader(evenWriter, oddWriter);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(evenNumberWriter);
        executorService.submit(oddNumberWriter);
        executorService.submit(numberReader);
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    static class NumberWriter implements Runnable {
        private int counter = 0;
        private int max;
        private boolean isEven;
        private PipedWriter pipedWriter;

        NumberWriter(int max, boolean isEven, PipedWriter pipedWriter) {
            this.max = max;
            this.isEven = isEven;
            this.pipedWriter = pipedWriter;
        }

        @Override
        public void run() {
            while (counter <= max) {
                try {
                    if(isEven && counter % 2 == 0) {
                        pipedWriter.write(counter);
                    }
                    else if(!isEven && counter % 2 != 0) {
                        pipedWriter.write(counter);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                counter++;
            }
            try {
                pipedWriter.write(-1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class NumberReader implements Runnable {
        private PipedReader reader1;
        private PipedReader reader2;

        NumberReader(PipedWriter writer1, PipedWriter writer2) {
            try {
                reader1 = new PipedReader(writer1);
                reader2 = new PipedReader(writer2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            int num1 = 0;
            int num2 = 0;

            while (num1 != -1 || num2 != -2) {
                try {
                    num1 = reader1.read();
                    num2 = reader2.read();
                    int sum = num1 + num2;
                    if(sum % 5 == 0) {
                        System.out.println(num1 + ", " + num2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
