package app.fynnjason.fivesingletondemo.java;

/**
 * Created by FynnJason on 2017/12/25.
 * 双重检测法，解决了懒加载和同步锁的缺点
 */

public class DoubleCheckSingleton {
    //第一步：私有构造
    private DoubleCheckSingleton() {

    }
    //第二步：创建静态对象
    private static volatile DoubleCheckSingleton INSTANCE;

    //第三步：对外公开获取方法
    public static DoubleCheckSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
