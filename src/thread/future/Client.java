package thread.future;

public class Client {
	public Data request(String req){
		final FutureData future = new FutureData();
		new Thread(new Runnable() {
			@Override
			public void run() {
				RealData realData = new RealData();
				future.setRealData(realData);
			}
		}).start();
		return future;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		System.out.println("请求");
		Data data = client.request("d");
		System.out.println("虚拟数据返回");
		String result = data.getResult();
		System.out.println(result);
		System.out.println("真实数据返回");
	}
}
