package app.fynnjason.fivesingletondemo.java;

/**
 * Created by FynnJason on 2017/12/25.
 * 大家熟知的懒加载方式(线程非安全)，也叫懒汉式
 */

public class LazyLoadSingleton {
    //第一步：私有构造
    private LazyLoadSingleton() {

    }
    //第二步：创建私有构造对象
    private static LazyLoadSingleton INSTANCE;

    //第二步：提供对外获取方法
    public static LazyLoadSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyLoadSingleton();
        }
        return INSTANCE;
    }
}
