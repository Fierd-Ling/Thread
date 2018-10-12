package thread;

import java.time.LocalDateTime;

/**
 * @author ZhongLingYun
 * @Title: MyThread
 * @Description:创建的线程
 * @date 2018/10/109:57
 */
public class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(LocalDateTime.now());
        while(true){
            //判断是否被中断
            if(Thread.currentThread().isInterrupted()) {
                // 跳出死循环，等同于关闭线程
                break;
            }
            System.out.println(LocalDateTime.now());
        }
    }
}
