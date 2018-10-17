package Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhongLingYun
 * @Title: Test1
 * @Description: 写一个程序，线程C在线程B后执行，线程B在线程A之后进行
 * @date 2018/10/1619:34
 */
public class Test1 {
    static Lock lock = new ReentrantLock();
    //Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
// 解法一
       /* new Thread(new Runnable() {
            @Override
            public void run() {
               lock.lock();
               for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
               lock.unlock();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
                lock.unlock();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
                lock.unlock();

            }
        }).start();*/


      /* // 解法二

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });


        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });
        a.start();
        a.join();
        b.start();
        b.join();
        c.start();
        c.join();*/


        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        a.join();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<10;x++){
                    try {
                        b.join();
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        a.start();
        b.start();
        c.start();
    }
}
