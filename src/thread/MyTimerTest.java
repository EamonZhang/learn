package thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTest {
/**
 * 任务周期时间 与 任务时间完成时间对比，当任务完成时间大于任务周期时间时，下一个任务等待上一个任务完成后在执行
 * @param args
 */
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"	"+new Date());
		long delay = 10*1000;//10秒
		Timer timer = new Timer("mytimer");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("开始"+Thread.currentThread().getName()+"	"+new Date());
				for(int i =0;i<8;i++){
					try {
						Thread.sleep(1*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(i +"	"+Thread.currentThread().getName()+"	"+new Date());
				}
				System.out.println("结束"+Thread.currentThread().getName()+"	"+new Date());
			}
		};
		timer.schedule(task, new Date(),delay);
		try {
			Thread.sleep(100*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("取消任务");
		System.out.println(new Date());
		timer.cancel();
	}
}
