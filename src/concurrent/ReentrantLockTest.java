package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhongLingYun
 * @Title: ReentrantLock
 * @Description: reentrantLock 重入锁
 * @date 2018/10/1510:21
 */
public class ReentrantLockTest {

    Lock reentrantLock = new ReentrantLock();
/*
    public void t1(){
        // 加锁
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        // 解锁
        reentrantLock.unlock();
    }*/

    public static void main(String[] args) throws InterruptedException {
        MyList list = new MyList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x=0;x<100;x++){
                  /*  try {
                        //Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    list.input(x);
                }
            }
        }
        ).start();
        Thread.sleep(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<30;x++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.get();
                }
            }
        }).start();

    }





}

class MyList{
    List<Object> list = new ArrayList<Object>();

   // Lock lock = new ReentrantLock();// 如果new ReentrantLock(true) 表示公平锁

    // 生产者消费者模式

    private int max=10;

    public int size(){
        return list.size();
    }

    public synchronized  void input(Object object){
        // while 和local等一起配合使用，不能使用if否则造成虚假等待，因为if只会判断 一次
        while(list.size()==max){
            try {
                // 等于最大的数量表示满了，进入等待
                this.wait();
                System.out.println(Thread.currentThread().getName()+"在等待生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+"在生产："+object);
        list.add(object);
        // 唤醒其他的线程进行消费
        this.notifyAll();
    }

    public synchronized Object get(){
        Object object= null;
        while(list.size()==0){
            try {
                // 如果容器中没有数据，就进入等待
                this.wait();
                System.out.println(Thread.currentThread().getName()+"在等待消费");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果有数据
        object =list.get(0);
        list.remove(0);// 永远去掉第一个
        // 唤醒其他的线程进行数据的创造
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+"在消费:"+object);
        return object;
    }
}
