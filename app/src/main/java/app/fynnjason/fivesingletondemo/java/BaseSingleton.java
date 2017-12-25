package app.fynnjason.fivesingletondemo.java;

/**
 * Created by FynnJason on 2017/12/25.
 * 最基本的单例写法，也是最快最懒的方式，也有一种说法叫饿汉式
 */

public class BaseSingleton {
    //第一步：私有构造
    private BaseSingleton() {

    }

    //第二步：私有静态对象
    private static BaseSingleton INSTANCE = new BaseSingleton();

    //第三步：提供对外获取方法
    public static BaseSingleton getInstance() {
        return INSTANCE;
    }
}
