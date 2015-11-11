package thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) {
		final CountDownLatch count = new CountDownLatch(10);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(10000* 1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					count.countDown();
					System.out.println(count.getCount()+"	"+new Date());
				}
			}
		});
		 t .start();
		 System.out.println("start "+new Date());
		 try {
//			Thread.sleep(5*10000);
			count.await(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 System.out.println("hello...."+"	"+new Date());
	}
}
