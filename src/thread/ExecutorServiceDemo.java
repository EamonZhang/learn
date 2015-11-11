package thread;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

		public static void main(String[] args) throws IOException, InterruptedException {
	        // 创建一个固定大小的线程池
	        ExecutorService service = Executors.newFixedThreadPool(3);
	        Runnable run = new Runnable() {
                @Override
                public void run() {
                    System.out.println("启动线程"+Thread.currentThread().getName());
                    for(int i =0 ;i<10;i++){
                    	try {
							Thread.sleep(1000*1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
                    }
                    System.out.println("结束线程"+Thread.currentThread().getName());
                }
            };
	        for (int i = 0; i < 10; i++) {
	            // 在未来某个时间执行给定的命令
	            Future<?> f = service.submit(run);
//	            f.cancel(true);
	        }
	        // 关闭启动线程
	        service.shutdown();
	        // 等待子线程结束，再继续执行下面的代码
	        service.awaitTermination(5, TimeUnit.MINUTES);
	        System.out.println("all thread complete");
	    }
	}