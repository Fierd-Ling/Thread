package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: TestThreadLock
 * @Description: ThreadLock
 * @date 2018/10/1611:16
 */
public class TestThreadLock {

   /* static Map<Object,Object> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int x=0;
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x++;
                    map.put("x",x);
                }
            }
        }).start();

       *//* new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(map.get("x"));

                }
            }
        }).start();*//*
       Thread.sleep(2000);
       System.out.println(map.get("x"));
    }*/

    // 底层实现还是map，用于存储数据
    static ThreadLocal threadLocal = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(10);
                System.out.println(threadLocal.get());
            }
        }).start();
        Thread.sleep(1500);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        }).start();
    }


}
