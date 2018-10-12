package synchronize;

/**
 * @author ZhongLingYun
 * @Title: test
 * @Description: 多线程基础
 * @date 2018/10/109:55
 */
public class TestThread {

    //@Test
    public static void main(String[] args) {

        // 似乎 只在main下面可以启动，直接 使用Junit的test注解似乎无法启动线程
        thread.MyThread myThread = new thread.MyThread();
        Thread  thread = new Thread(myThread);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 中断线程 如果只是中断线程还是会继续执行
        thread.interrupt();
    }
}
