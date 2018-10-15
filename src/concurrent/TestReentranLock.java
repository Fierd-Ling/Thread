package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZhongLingYun
 * @Title: TestReentrantLock
 * @Description: 使用reentrantLock解决问题
 * @date 2018/10/1519:18
 */
class TestReentrantLock {
    /**
     * 所有的等待和 唤醒都需要有锁，否则会抛出异常，要么是synchronize锁定代码块，要么是lock.lock()---lock.unlock()
     *
     * */

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockList lockList = new ReentrantLockList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<40;x++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lockList.input(x);
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=0;x<50;x++){
                    lockList.get();
                }
            }
        }).start();

    }


}

class ReentrantLockList{
    List<Object> list = new ArrayList<>();

    private int max =10;

    Lock lock = new  ReentrantLock();
    /**
     *
     * 生产者
     *
     * **/
    private Condition production =lock.newCondition();

    /**
     *
     * 消费者
     *
     * */
    private Condition consumer = lock.newCondition();

    public int size(){
        return list.size();
    }

    public void input(Object object){
        // 生产者消费者关系需要先锁住
        lock.lock();
        while(list.size()==max){
            try {
                System.out.println(Thread.currentThread().getName()+"在等待生产");
                // 进入等待
                production.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(object);
        System.out.println("生产"+object);
        // 唤醒所的消费者
        consumer.signalAll();
        // 先释放锁标记
        lock.unlock();
    }

    public void get(){
        // 先锁住
        lock.lock();
        while(list.size()==0){
            try {
                System.out.println(Thread.currentThread().getName()+"在等待消费");
                // 进入锁等待
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费"+list.get(0));
        list.remove(0);
        // 先唤醒其他线程在释放锁
        production.signalAll();
        lock.unlock();
    }
}
