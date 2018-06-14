package com.autonavi.common.test;

import java.util.Objects;

/**
 * 三个线程循环打印ABC
 * Created by Administrator on 2018-05-01.
 */
public class ABCThread {


    public static void main(String[] args) {
        Thread A = new Thread(new Worker("A", 1));
        Thread B = new Thread(new Worker("B", 2));
        Thread C = new Thread(new Worker("C", 3));
        A.start();
        B.start();
        C.start();
    }

    public static final Object LOCK = new Object();
    public static volatile Integer COUNTER = 1;

    private static class Worker implements Runnable {

        private String text;
        private Integer num;

        public Worker(String text, Integer num) {
            this.text = text;
            this.num = num;
        }

//        @Override
        public void run() {
            while(true) {
                synchronized (LOCK) {
                    while(Objects.equals(COUNTER, num)) {
                        System.out.println(text);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        COUNTER++;
                        COUNTER = COUNTER > 3 ? 1 : COUNTER;
                        LOCK.notifyAll();
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
