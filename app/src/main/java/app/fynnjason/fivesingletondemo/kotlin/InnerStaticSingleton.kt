package app.fynnjason.fivesingletondemo.kotlin

/**
 * Created by FynnJason on 2017/12/25.
 * 静态内部类方式，比较推荐的写法
 */
class InnerStaticSingleton private constructor() {
    companion object {
        fun getInstance() = Holder.INSTANCE

    }

    private object Holder {
        val INSTANCE = InnerStaticSingleton()
    }
}