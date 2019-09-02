:fire:Java与Kotlin对应的5种单例模式，强烈建议大家学习学习！工作和面试必备

# 原文地址
http://www.jianshu.com/p/bdaf68599bad

java方式在java包中，[点这里快速查看](https://github.com/FynnJason/FiveSingletonDemo/tree/master/app/src/main/java/app/fynnjason/fivesingletondemo/java)

kotlin方式在kotlin包中[点这里快速查看](https://github.com/FynnJason/FiveSingletonDemo/tree/master/app/src/main/java/app/fynnjason/fivesingletondemo/kotlin)


# 一、前言

单例模式，一直以来是我们在日常开发中最常用的一种设计模式，更是面试中非常重要，也非常容易被问到的问题。在日常开发中，大家常用的语言还是Java，但今天我给大家带来的是在Kotlin语言中，单例模式是怎么编写的，并且会对比Java方式，并说明每种方式的优缺点。

下面会列举5种最为常见的单例模式做对比：

- 1.饿汉式
- 2.懒汉式
- 3.同步锁式
- 4.双重检测式
- 5.内部类式

# 二、Java与Kotlin对比

## 1、饿汉式

饿汉式可以说是我们最先接触单例模式的例子了，是最基本的单例写法，也是最快最懒的方式。


优点 | 缺点
---|---
简单好写 | 类加载就初始化了对象，影响应用启动速度


下面直接看代码吧：
> Java方式

```java
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
```
> Kotlin方式

```kotlin
object BaseSingleton {

}
```
有童鞋要说了，这什么都没写呀。对，饿汉式在Kotlin中，只需要一个object修饰符就行了，这就是Kotlin非常厉害的地方。

## 2.懒汉式
针对饿汉式的缺点，于是懒汉式就出现了，因为比较简单，下面直接分析。

优点 | 缺点
---|---
只有第一次使用时，才会初始化对象 | 线程非安全，多线程中可能会出现创建多个对象

> Java方式

```java
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
```

其实就是增加了一个空判断。

> Kotlin方式

```kotlin
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
```
Kotlin这里有两种写法，一种是纯种，一种是变种。变种大家一看就明白，就是直接把Java的方式翻译过来了。纯种的我们使用了lazy，看英文就知道是懒加载的方式，传入了一个LazyThreadSafetyMode.NONE，英文好的小伙伴一看就明白，这是线程不安全的意思。companion object的意思相当于Java中public static。

## 3.同步锁式
因为懒汉式的出现，虽然解决了饿汉式的不足，但也出现了多线程的问题。于是解决懒汉式的方式就出现了，那就是我们熟知的加锁Synchronized。

优点 | 缺点
---|---
保证线程安全 | 每次都要加锁，获取的时候不经济

> Java方式

```java
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
```

只是加了一个关键字synchronized，没有难理解的地方。

> Kotlin方式

```kotlin
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
```

在Kotlin中，我们要使用的是注解方式@Synchronized，就能达到同步锁的目的了。

## 4.双重检测式

第3种方式应该已经满足日常大部分的需求，但对我们程序员来说，不断的优化才是学习之道。那么针对每次获取都会加锁的问题，要怎么解决呢？双重检测式就出现了。

优点 | 缺点
---|---
第一次获取的时候才会加锁 | 写法有点难，考点记性

> Java方式

```java
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
```

细心的童鞋发现，我们第二步用到了volatile，关于volatile不是本文的重点，所以这里不展开说明。在第三步中，我们首先判断一次空，如果是空，就加锁，然后再判断一次空，如果为空就创建。这样的好处就是上面优点说到的，只会锁一次。缺点大家也发现了，不仅要必须写volatile，方法中的步骤也不能错。

> Kotlin方式

```kotlin
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
```

又来两种写法，变种的我就不解释了。纯种的，我们只改变了lazy的括号的值，mode = LazyThreadSafetyMode.SYNCHRONIZED就是锁的意思，英文好的童鞋一眼就明白了。

## 5.内部类式
最后一种方式，不仅满足了懒加载、线程安全，代码也非常少，是非常推荐的一种方式。

优点 | 缺点
---|---
简单，代码少 | 暂无

> Java方式

```java
public class InnerStaticSingleton {
    private InnerStaticSingleton() {

    }

    private static class Holder {
        private static InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }

    public static InnerStaticSingleton getInstance() {
        return Holder.INSTANCE;
    }
```
内部类Holder，里面有外部的实例。很多童鞋可能要问，这怎么就满足懒汉式和线程安全呢？当我们应用初始化时，getInstance没有被调用，就没有实例对象，那就满足了懒汉式。当我们调用getInstance的时候，Java虚拟机为了保证类加载的安全性，所以这里就保证了线程安全。这种写法是不是惊呆了？那Kotlin又是怎么样写的呢？

> Kotlin方式

```kotlin
class InnerStaticSingleton private constructor() {
    companion object {
        fun getInstance() = Holder.INSTANCE

    }

    private object Holder {
        val INSTANCE = InnerStaticSingleton()
    }
}
```

很简单，内部用object创建一个内部Holder单例，外部一个getInstance来获取的方法。也相当于是Java翻译过来的方式。

# 三、总结
为了方便大家，我创建了一个项目，专门供大家对比学习。

这是Github项目，里面有详细的代码注释。如果对你有帮助，欢迎大家star，谢谢！

https://github.com/FynnJason/FiveSingletonDemo

如果本文对你有帮助，欢迎点赞，赞赏请我喝杯Java...

如果有什么疑问，下方评论留言...
