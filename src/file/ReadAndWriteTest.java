package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.ArrayBlockingQueue;

public class ReadAndWriteTest {

	public static void main(String[] args) {
		 final ArrayBlockingQueue<File> files = new ArrayBlockingQueue<File>(1000000);
		Thread writeThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					int i =0;
					while(true){
						Thread.sleep(100);
						File file =	new File("/home/zhangjin/test/1/"+i);
						OutputStream os = new FileOutputStream(file);
						os.write(i++);
						os.flush();
						os.close();
						files.put(file);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		writeThread.start();
		while(true){
			try {
				Thread.sleep(1000*5);
				File file = files.take();
				FileInputStream is = new FileInputStream(file);
				int i = is.read();
				System.out.println(file.getName()+"	"+i);
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
