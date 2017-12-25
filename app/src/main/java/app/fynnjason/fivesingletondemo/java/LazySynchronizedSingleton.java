package app.fynnjason.fivesingletondemo.java;

/**
 * Created by FynnJason on 2017/12/25.
 * 同步锁方式，在懒加载的方式上升级，保证线程安全
 */

public class LazySynchronizedSingleton {
    //第一步：私有构造
    private LazySynchronizedSingleton() {

    }

    //第二步：创建私有对象
    private static LazySynchronizedSingleton INSTANCE;

    //第三步：提供对外访问方法
    public static synchronized LazySynchronizedSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazySynchronizedSingleton();
        }
        return INSTANCE;
    }
}
