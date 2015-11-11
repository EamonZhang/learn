package thread;

public class MyJoinTestDemo {
	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();
		System.out.println("00000");
		try {
			t.join();
			t.interrupt();
			System.out.println("000001111");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("over.....");
	}
}
class MyThread extends Thread{
	public MyThread() {
		super("Mythread");
	}
	@Override
	public void run() {
		for (int i = 0 ;i < 10 ; i++) {
			try {
				System.out.println(Thread.currentThread().getName()+"ddddddddddd");
				Thread.sleep(1000*3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}