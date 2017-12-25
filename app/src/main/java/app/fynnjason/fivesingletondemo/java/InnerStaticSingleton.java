package app.fynnjason.fivesingletondemo.java;

/**
 * Created by FynnJason on 2017/12/25.
 * 静态内部类方式，比较推荐的写法
 */

public class InnerStaticSingleton {
    private InnerStaticSingleton() {

    }

    private static class Holder {
        private static InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
