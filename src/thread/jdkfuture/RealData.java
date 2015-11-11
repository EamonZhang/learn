package thread.jdkfuture;

import java.util.concurrent.Callable;

public class RealData implements  Callable<Object>{
	public RealData(Object req) {
	}
	@Override
	public String call() throws Exception {
		Thread.sleep(1000*10);
		return "realData...";
	}

}
