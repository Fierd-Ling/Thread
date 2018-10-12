package synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: TryCatch
 * @Description: synchronized异常的时候释放锁
 * @date 2018/10/1110:24
 */
public class TryCatch {
int i=0;
    synchronized void m(){
        System.out.println("begin ");
        while(true){
            i++;
            System.out.println(Thread.currentThread().getName()+":"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==5){
                // 在被锁住的代码区域如果抛出了异常会释放锁
               //throw new RuntimeException();
                System.out.println(i);
                int x=0/0;
            }

        }
    }

    public static void main(String[] args) {
       final TryCatch tryCatch = new TryCatch();
        new Thread(new Runnable() {
            @Override
            public void run() {
                tryCatch.m();
            }
        } ,"Thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                tryCatch.m();
            }
        },"Thread2").start();
    }

}
