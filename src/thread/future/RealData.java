package thread.future;

public class RealData implements Data {

	public RealData() {
		try {
			Thread.sleep(1000*10);
			System.out.println("真实数据制作完成！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getResult() {
		return "realData...";
	}

}
