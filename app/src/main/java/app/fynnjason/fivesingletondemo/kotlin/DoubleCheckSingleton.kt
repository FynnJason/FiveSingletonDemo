package app.fynnjason.fivesingletondemo.kotlin

/**
 * Created by FynnJason on 2017/12/25.
 * 双重检测法，解决了懒加载和同步锁的缺点
 */
class DoubleCheckSingleton private constructor() {
    companion object {
        //Kotlin原生写法
        val INSTANCE_1 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DoubleCheckSingleton()
        }

        //翻译JAVA代码写法
        @Volatile
        private var INSTANCE_2: DoubleCheckSingleton? = null

        fun getInstance(): DoubleCheckSingleton {
            if (INSTANCE_2 == null) {
                synchronized(DoubleCheckSingleton::class) {
                    if (INSTANCE_2 == null) {
                        INSTANCE_2 = DoubleCheckSingleton()
                    }
                }
            }
            return INSTANCE_2!!
        }
    }
}