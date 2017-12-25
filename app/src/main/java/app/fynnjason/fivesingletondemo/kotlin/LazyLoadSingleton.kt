package app.fynnjason.fivesingletondemo.kotlin

/**
 * Created by FynnJason on 2017/12/25.
 * 大家熟知的懒加载方式(这里会列出Kotlin的原生写法和翻译Java写法)
 */
class LazyLoadSingleton private constructor(){

    companion object {
        //原生写法
        //LazyThreadSafetyMode.NONE 线程不安全
        val INSTANCE_1 by lazy(LazyThreadSafetyMode.NONE) {
            LazyLoadSingleton()
        }

        //翻译JAVA的写法
        private var INSTANCE_2: LazyLoadSingleton? = null

        fun getInstance(): LazyLoadSingleton {
            if (INSTANCE_2 == null) {
                INSTANCE_2 = LazyLoadSingleton()
            }
            return INSTANCE_2!!
        }
    }
}