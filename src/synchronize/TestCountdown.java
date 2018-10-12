
package synchronize;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: TestCountdown
 * @Description: 使用门栓
 * @date 2018/10/1215:42
 * */
public class TestCountdown {

    //自定义容器，提供新增元素（add）和获取元素数量（size）方法。
    //启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，
    // 当容器元素数量为5时，线程2输出信息并终止。

    public static void main(String[] args) {
        // 新建一个门栓，锁的数量为一把
        final MyList2 t =new MyList2();
        final CountDownLatch latch = new CountDownLatch(1);
    new Thread(new Runnable() {
        @Override
        public void run() {
            if (t.size() != 5) {
                // 等待门栓开放
                try {
                    // await 等待门栓开放，wait是让线程进入等待状态
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName());
        }
    }).start();

    new Thread(new Runnable() {
        @Override
        public void run() {
            for (int x = 0; x < 10; x++) {
                t.add(x);
                if (t.size() == 5) {
                    // 减少锁
                    latch.countDown();
                }
                System.out.println(Thread.currentThread().getName() + ":" + x);
                // 保证其余线程在刚好等于5的情况下进入
                   /* try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            }
        }
    }).start();

}
       /* new Thread(new Runnable(){
            @Override
            public void run() {
                if(t.size() != 5){
                    try {
                        latch.await(); // 等待门闩的开放。 不是进入等待队列
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("size = 5");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){
                    System.out.println("add Object to Container " + i);
                    t.add(new Object());
                    if(t.size() == 5){
                        latch.countDown(); // 门闩-1
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

}

class MyList2 {
    List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }
}




