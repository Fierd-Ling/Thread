package synchronize;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: CountDown
 * @Description: 门栓 门栓表示一个门上可以挂非常多的锁，但是减少锁需要一把把的减少
 * 门栓效率比锁高
 * @date 2018/10/1118:12
 */
public class CountDown {
    /**
     *  门栓，数字表示门上锁的数量
     */
    CountDownLatch countDownLatch = new CountDownLatch(10);
    int i=0;
    public void t1(){
        try {
            // 等待门栓开放
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("t1运行");
        while(true){
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
    }

    public void t2(){
        while(true){
            if(countDownLatch.getCount()==0){
                break;
            }
            i++;
            System.out.println(Thread.currentThread().getName()+": "+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 减少锁数量每次减少一个
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        CountDown countDown = new CountDown();
        new Thread(new Runnable() {
            // 运行t1
            @Override
            public void run() {
                countDown.t1();
            }
        }).start();
        // 运行t2
        new Thread(new Runnable() {
            @Override
            public void run() {
                countDown.t2();
            }
        }).start();
    }
}
