package ssh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteFile {

	public static void main(String[] args) throws Exception {
		File file = new File("/home/zhangjin/share/nohup1.out");
		if(!file.exists()){
			file.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		int i = 0;
		while(true){
			i++;
			Thread.sleep(5*1000);
			bw.write("line "+i);
			bw.newLine();
			bw.flush();
//			System.out.println("write line "+i);
		}
	}
}
