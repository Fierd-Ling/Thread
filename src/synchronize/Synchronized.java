package synchronize;


import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: Synchronized
 * @Description: 锁// 使用过程中尽量同步代码块不要同步function，同步范围尽量小
 * @date 2018/10/1014:45
 */
public class Synchronized {

    // 只要不是new出来的多个常量如果内容相等那么指向的内容是一样的
    String s1 = "s";
    String s2 ="s";

    public void m3(){
        // 当锁所锁的对象是常量，而且多个锁，锁的常量内容相同的情况的（没有出现new）多
        // 个线程访问会出现后一个线程无法访问资源的情况
        synchronized(s1){
            System.out.println("m3");
            while(true){

            }
        }

    }

    public void m4(){
        synchronized(s2){
            System.out.println("m4");
            while(true){
            }
        }
    }

    @Test
    public void test3(){
        Synchronized syn = new Synchronized();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 syn.m3();
             }
         }).start();

         new Thread(new Runnable() {
             @Override
             public void run() {
                 syn.m4();
             }
         }).start();
    }








   /**
     * @Description: 静态方法锁，锁的是类对象
     * 在被调用test1方法过程中上锁
     * @param:
     * @return:
     * @auther: ZhongLingYun
     * @date: 2018/10/10 14:47
     */
    public static synchronized  void test1 (){
        // function 操作
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 锁可以重入
    synchronized void m1(){
        System.out.println("m1 begin");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        System.out.println("m2 begin");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
        //m1();
    }

    @Test
     public void test2(){
       m1();
     }

}
