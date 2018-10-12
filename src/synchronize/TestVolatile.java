package synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: Test
 * @Description: 练习题代码  使用volatile让容器对线程可见
 * @date 2018/10/129:24
 */

public class TestVolatile {
    //自定义容器，提供新增元素（add）和获取元素数量（size）方法。
    //启动两个线程。线程1向容器中新增10个数据。线程2监听容器元素数量，
    // 当容器元素数量为5时，线程2输出信息并终止。

    // 解法1 对容器使用volatile关键字
    public static void main(String[] args) {
        final  MyList myList = new MyList();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"启动");
                for (int x=0;x<10;x++){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(x);
                    myList.add(x);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"启动");
                while(true){
                if(myList.size()==5){
                    System.out.println(Thread.currentThread().getName()+"停止");
                    break;
                }
            }
            }
        }).start();
    }
}

/**
 * @Description: 所谓的并发容器表示的是这个容器是对于 多线程是可见性的容器
 * @param:
 * @return:
 * @auther: ZhongLingYun
 * @date: 2018/10/12 11:01
 */
class MyList{
    /**
     *
     * volatile保证所对应的事例每次都去内存中查找最新的数据保证数据实时性（线程可见性）
     *
     * */
    volatile  List<Object> list = new ArrayList<Object>();

    public void add(Object object){
        list.add(object);
    }

    public int size(){
        return list.size();
    }
}
