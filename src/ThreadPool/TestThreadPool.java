package ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ZhongLingYun
 * @Title: TestThreadPool
 * @Description: 线程池
 * @date 2018/10/1616:42
 */
public class TestThreadPool {

    public static void main(String[] args) throws InterruptedException {
        // 线程容量池,最大容量为3个线程
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 100个任务
        for(int x=0;x<100;x++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        System.out.println(service);

        Thread.sleep(3000);
        System.out.println(service);

        //不在接受新的任务
        service.shutdown();

        System.out.println(service);

        // 线程池在关闭无法接受新的任务
        for(int z=0;z<10;z++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }


    }
}
