package com.example.testreentrantlock;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shiimin
 * @date 2020/10/27
 */
public class TestNode {

    public static void main(String[] args) {
        Node node = new Node();
        boolean b = node.casNext(null, new Node());
        System.out.println(b);
    }

    private static class Node{
        private static final Unsafe UNSAFE = Unsafe.getUnsafe();
        private static final long nextOffset;

        static {
            try {
                nextOffset = UNSAFE.objectFieldOffset(Node.class.getDeclaredField("next"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                throw new Error(e);
            }
        }

        volatile Node next;
        boolean casNext(Node cmp, Node val){
            /**
             * compareAndSwapObject(Object var1, long var2, Object var3, Object var4)
             * var1 操作的对象
             * var2 操作的对象属性
             * var3 var2与var3比较，相等才更新
             * var4 更新值
             */
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }

        public static Unsafe getUnsafe(){

            try {
                Field f = Unsafe.class.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                return (Unsafe) f.get(null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        }

    }

}
