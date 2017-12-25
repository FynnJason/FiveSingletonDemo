package app.fynnjason.fivesingletondemo.kotlin

/**
 * Created by FynnJason on 2017/12/25.
 * 同步锁方式，在懒加载的方式上升级，保证线程安全
 */
class LazySynchronizedSingleton private constructor(){
    companion object {
        private var INSTANCE: LazySynchronizedSingleton? = null

        //在Kotlin中，我们就用的注释方式来加锁
        @Synchronized
        fun getInstance(): LazySynchronizedSingleton {
            if (INSTANCE == null) {
                INSTANCE = LazySynchronizedSingleton()
            }
            return INSTANCE!!
        }
    }
}