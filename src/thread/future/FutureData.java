package thread.future;

public class FutureData implements Data {
	protected RealData realData = null;
	private boolean isReady = false;
	@Override
	public synchronized String getResult() {
		while(!isReady){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realData.getResult();
	}
	public synchronized void setRealData(RealData realData){
		if(isReady){
			return;
		}
		this.realData = realData;
		isReady = true;
		notifyAll();
	}
}
