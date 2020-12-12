package com.example.testreentrantlock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shiimin
 * @date 2020/10/12
 */
public class ReentrantLockTest3 {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadDemo(lock1, lock2), "线程1");
        Thread thread1 = new Thread(new ThreadDemo(lock1, lock2), "线程2");
        thread.start();
        thread1.start();
        thread.interrupt();
    }

    static class ThreadDemo implements Runnable {

        Lock firstLock;
        Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                TimeUnit.MILLISECONDS.sleep(50);
                secondLock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + "获取到了资源，正常结束!");
            }
        }
    }

}
