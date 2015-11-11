package thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
//3. Semaphore 
//我们先来学习一下JDK1.5 API中关于这个类的详细介绍： 
//“一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，
//在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
//但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。” 
//
//将信号量初始化为 1，使得它在使用时最多只有一个可用的许可，从而可用作一个相互排斥的锁。
//这通常也称为二进制信号量，因为它只能有两种状态：一个可用的许可，或零个可用的许可。
//按此方式使用时，二进制信号量具有某种属性（与很多 Lock 实现不同），即可以由线程释放“锁”，
//而不是由所有者（因为信号量没有所有权的概念）。在某些专门的上下文（如死锁恢复）中这会很有用。
//
//我们一般用它来控制某个对象的线程访问对象 
//
//例如，对于某个容器，我们规定，最多只能容纳n个线程同时操作 
//使用信号量来模拟实现 
//
//具体代码如下（参考 [JCIP]） 
public class TestSemaphore {  
	  
    public static void main(String[] args) {  
        ExecutorService exec = Executors.newCachedThreadPool();  
        TestSemaphore t = new TestSemaphore();  
        final BoundedHashSet<String> set = t.getSet();  
  
        for (int i = 0; i < 3; i++) {//三个线程同时操作add  
            exec.execute(new Runnable() {  
                public void run() {  
                    try {  
                        set.add(Thread.currentThread().getName());  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                }  
            });  
        }  
  
        for (int j = 0; j < 3; j++) {//三个线程同时操作remove  
            exec.execute(new Runnable() {  
                public void run() {  
                    set.remove(Thread.currentThread().getName());  
                }  
            });  
        }  
        exec.shutdown();  
    }  
  
    public BoundedHashSet<String> getSet() {  
        return new BoundedHashSet<String>(2);//定义一个边界约束为2的线程  
    }  
  
    class BoundedHashSet<T> {  
        private final Set<T> set;  
        private final Semaphore semaphore;  
  
        public BoundedHashSet(int bound) {  
            this.set = Collections.synchronizedSet(new HashSet<T>());  
            this.semaphore = new Semaphore(bound, true);  
        }  
  
        public void add(T o) throws InterruptedException {  
            semaphore.acquire();//信号量控制可访问的线程数目  
            set.add(o);  
            System.out.printf("add:%s%n",o);  
        }  
  
        public void remove(T o) {  
            if (set.remove(o))  
                semaphore.release();//释放掉信号量  
            System.out.printf("remove:%s%n",o);  
        }  
    }  
}  
