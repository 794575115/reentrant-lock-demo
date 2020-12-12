package com.example.testreentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shiimin
 * @date 2020/10/12
 */
public class ReentrantLockTest2 {

    private static final Lock LOCK = new ReentrantLock(false);

    public static void main(String[] args) {
        new Thread(ReentrantLockTest2::test, "线程A").start();
        new Thread(ReentrantLockTest2::test, "线程B").start();
        new Thread(ReentrantLockTest2::test, "线程C").start();
        new Thread(ReentrantLockTest2::test, "线程D").start();
        new Thread(ReentrantLockTest2::test, "线程E").start();
    }

    public static void test() {
        for (int i = 0; i < 2; i++) {
            try {
                LOCK.lock();
                System.out.println(Thread.currentThread().getName() + "获取了锁");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }
    }

}
