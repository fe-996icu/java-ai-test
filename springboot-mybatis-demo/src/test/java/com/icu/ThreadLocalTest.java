package com.icu;

import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    // 创建一个本地线程局部变量
    ThreadLocal<String> local = new ThreadLocal<>();

    @Test
    public void testThreadLocal(){
        // 主线程设置值
        local.set("Main Thread Message");

        new Thread(() -> {
            // 子线程设置值，不会影响主线程的值
            local.set("Thread 1 Message");

            // 子线程输出值
            System.out.println(Thread.currentThread().getName() + "：" + local.get());
        }).start();

        // 主线程输出值，子线程设置的值不会影响主线程
        System.out.println(Thread.currentThread().getName() + "：" + local.get());
    }
}



