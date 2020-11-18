package com.snail.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//练习Thread，实现多线程同步下载图片
public class TestThread2 extends Thread{

    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("文件名："+name);
    }

    public static void main(String[] args) {
        TestThread2 testThread1 = new TestThread2("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3871417612,1923573351&fm=26&gp=0.jpg","t1.jpg");
        TestThread2 testThread2 = new TestThread2("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1052437823,2294515059&fm=26&gp=0.jpg","t2.jpg");
        TestThread2 testThread3 = new TestThread2("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1264795828,2560993596&fm=26&gp=0.jpg","t3.jpg");

        testThread1.start();
        testThread2.start();
        testThread3.start();
    }
}

class WebDownloader{
    //下载方法
    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}