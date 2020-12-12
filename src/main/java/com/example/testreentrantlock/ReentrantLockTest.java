package com.example.testreentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shiimin
 * @date 2020/10/12
 */
public class ReentrantLockTest {

    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(ReentrantLockTest::test, "线程A").start();
        new Thread(ReentrantLockTest::test, "线程B").start();
    }

    public static void test() {
        try {
            LOCK.lock();
            System.out.println(Thread.currentThread().getName() + "获取了锁");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            LOCK.unlock();
        }
    }

}
