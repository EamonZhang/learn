package thread.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PulsWorker extends Worker {

	@Override
	public Object handler(Object input) {
		Integer i = (Integer)input;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i*i*i;
	}
	public static void main(String[] args) {
		Master master = new Master(new PulsWorker(), 5);
		for (int i = 0; i < 100; i++) {
			master.submit(i);
		}
		master.execute();
		int res = 0;
		Map<String, Object> resultMap = master.getResultMap();
		while(resultMap.size() > 0 || !master.isComplete()){
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if(key != null){
				i = (Integer)resultMap.remove(key);
			}
			if(i != null){
				res += i;
			}
//			if(key != null){
//				resultMap.remove(key);
//			}
		}
		System.out.println(res);
	}
}
