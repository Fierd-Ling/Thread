package synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: TestSynchronize
 * @Description: TODO
 * @date 2018/10/1214:35
 */
public class TestSynchronize {
    //自定义容器，提供新增元素（add）和获取元素数量（size）方法。
    //启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，
    // 当容器元素数量为5时，线程2输出信息并终止。

    // 解法2使用锁
    public static void main(String[] args) {
        final MyListt list= new MyListt();
        // 创建锁对象
        final  Object lock = new Object();
        // 当前线程向容器中添加数据
        // 写法1
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"begin");
                synchronized (lock){
                    for(int x=0;x<10;x++){
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        list.add(x);
                        System.out.println(x);
                        // 如果个数达到了5个让线程进入等待
                        if(list.size()==5){
                            try {
                                // 当前线程进入等待
                                lock.wait();
                                // 唤醒当前线程以外的其余的所有线程
                                lock.notifyAll();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        }).start();

        // 当前线程判断是否数据达到了 5个
        new  Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    //while(true){
                    // 如果个数没有达到5个线程进入等待
                    if(list.size()!=5){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        // （1）直接唤醒当前线程，运行完毕会自动释放锁
                        lock.notify();
                    }
                    System.out.println(Thread.currentThread().getName()+"begin");
                    // （2）当前线程执行完毕，需要唤醒上一个wait的线程否则当前的锁无法释放
                    //lock.notifyAll();

                }

            }
           // }
        }).start();
*/

        new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized(lock){
                    if(list.size()!=5){
                        // 数量没有达到5释放锁让其他线程运行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"begin");
                        // 运行完毕唤醒其他的线程
                        lock.notifyAll();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock){
                    for(int x=0;x<10;x++){
                        System.out.println(Thread.currentThread().getName()+":"+x);
                        list.add(x);
                        if(list.size()==5){
                            // 唤醒其他的线程
                            lock.notifyAll();
                            try {
                                // 释放锁让其线程执行
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }
            }
        }).start();




    }


}

/**
 * @Description: 自主创建容器
 * @param:
 * @return:
 * @auther: ZhongLingYun
 * @date: 2018/10/12 14:37
 */
class MyListt{
    List<Object> list = new ArrayList<Object>();

    public void add(Object Object){
        list.add(Object);
    }

    public int size(){
        return  list.size();
    }

}
