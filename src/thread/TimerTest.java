package thread;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimerTest {

	public static void main(String[] args) {
		long PERIOD_DAY = 1 * 1* 100;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
//					System.out.println("start task	"+new Date());
					MyTread thread = new MyTread();
					thread.latch = new CountDownLatch(10);
					ExecutorService executor = Executors.newFixedThreadPool(10);
					for (int i = 0; i < 10; i++) {
						executor.execute(thread);
					}
					executor.shutdown();
					Map map=Thread.getAllStackTraces();
					 System.out.println("map size "+map.size());
//					thread.latch.await();
//					System.out.println("over "+new Date());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, new Date(), PERIOD_DAY);
	}
	
}
class MyTread implements Runnable{
	CountDownLatch latch;
	int i = 0;
	@Override
	public void run() {
		i++;
//			System.out.println(Thread.currentThread().getName()+"	"+new Date()+"		"+i);
			if(i % 8 ==0){
				try {
					System.out.println("sleep....."+Thread.currentThread().getName());
					Thread.sleep(10 * 60 * 1000);
					System.out.println("get up ....."+Thread.currentThread().getName());
					Map map=Thread.getAllStackTraces();
					 System.out.println("map size "+map.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			latch.countDown();
	}
}