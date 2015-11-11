package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class PDFReader {

	public static void main(String[] args) throws Exception {
		String s = "/home/zhangjin/mybook/LuceneInAction(第2版)_中文版.pdf";
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(s)),"gb2312"));
		String line =  br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			System.out.println(line);
		}
		br.close();
	}

}
