package synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhongLingYun
 * @Title: volatile
 * @Description: volatile关键字
 * @date 2018/10/1111:13
 */
public class Volatile {

    // 关键字的作用是告诉底层每次都去找最新的数据（数据每次都要刷新）
    volatile boolean x=true;
    public  void m (){
       System.out.println("begin");
       while(x){
       }
       System.out.println("end");
   }

    public static void main(String[] args) throws InterruptedException {
        final  Volatile v = new Volatile();
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 每次new实体开启的资源就不是同一个
                //new Volatile().m();

                v.m();;
            }
        }).start();// 启动线程
        TimeUnit.SECONDS.sleep(3);
        // 线程不可见所以在
        v.x=false;
    }
}
