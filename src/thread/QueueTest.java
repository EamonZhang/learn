package thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {

	public static void main(String[] args) {
		final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(
				100, true);
		Timer timeProductor = new Timer("productor");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				try {
					queue.put("a");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeProductor.schedule(task, new Date(), 1 * 1000);

		Timer timeCustomer = new Timer("Customer");
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				try {
					while (queue.size()>0) {
						System.out.println(queue.size());
						Thread.sleep(5*100);
						String s =queue.take();
						System.out.println(s);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		timeCustomer.schedule(task1, new Date(), 10 * 1000);
	}
}
