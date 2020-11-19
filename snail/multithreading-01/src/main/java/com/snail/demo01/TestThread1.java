package com.snail.demo01;

//创建线程方式一：继承Thread类，重写run()方法，调用start开启线程

//线程开启不一定立即执行，由cpu调度执行
public class TestThread1 extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("代码=="+i);
        }
    }

    public static void main(String[] args) {
        //创建一个线程对象
        TestThread1 testThread1 = new TestThread1();

        //调用start()方法开启线程
        testThread1.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("main=="+i);
        }
    }
}
