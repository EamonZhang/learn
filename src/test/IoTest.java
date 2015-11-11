package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class IoTest {

	public static void main(String[] args) {
		
		try {
			BufferedWriter bwErr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/home/zhangjin/logtest.log"))));
			bwErr.write("sssss");
			bwErr.newLine();
			bwErr.flush();
			bwErr.close();
			bwErr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/home/zhangjin/logtest.log"))));
			bwErr.write("aaaaa");
			bwErr.newLine();
			bwErr.flush();
			bwErr.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
