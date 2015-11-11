package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(4,new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
					
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("caught "+e);
					}
				});
				return t;
			}
		});
		exec.execute(new ExceptionThread2());
		while (true) {
		System.out.println("ss");
			
		}
	}
}
class ExceptionThread2 implements Runnable{
	@Override
	public void run() {
		throw new RuntimeException("my exception");
	}
}
