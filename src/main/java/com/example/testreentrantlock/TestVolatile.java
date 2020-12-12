package com.example.testreentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shiimin
 * @date 2020/10/27
 */
public class TestVolatile {

    private String str1 = "aaaaaaaaa";
    private volatile String str2 = "bbbbbbbb";
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(false);
        TestVolatile test = new TestVolatile();
        new Thread(() -> {
            for (;;) {
                if (test.getStr1().equals("aaaaaaaaa")){
                    test.setStr1("ccccccccc");
                    if (!test.getStr1().equals("ccccccccc")){
                        System.out.println(Thread.currentThread().getName()+" str1 "+test.getStr1().equals("ccccccccc"));
                    }
                }
                if (test.getStr2().equals("bbbbbbbb")){
                    test.setStr2("ddddddddd");
                    if (!test.getStr2().equals("ddddddddd")){
                        System.out.println(Thread.currentThread().getName()+" str2 "+test.getStr2().equals("ddddddddd"));
                    }
                }
            }
        },"线程1").start();
        new Thread(() -> {

            for (;;) {
                if (test.getStr1().equals("ccccccccc")){
                    test.setStr1("aaaaaaaaa");
                    if (!test.getStr1().equals("aaaaaaaaa")){
                        System.out.println(Thread.currentThread().getName()+" str1 "+test.getStr1().equals("aaaaaaaaa"));
                    }
                }
                if (test.getStr2().equals("ddddddddd")){
                    test.setStr2("bbbbbbbb");
                    if (!test.getStr2().equals("bbbbbbbb")){
                        System.out.println(Thread.currentThread().getName()+" str2 "+test.getStr2().equals("bbbbbbbb"));
                    }
                }
            }
        },"线程2").start();
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

}
