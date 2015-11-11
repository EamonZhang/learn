package thread.jdkfuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Object> future = new FutureTask<Object>(new RealData(null));
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(future);
		service.shutdown();
		System.out.println(future.get());
	}

}
