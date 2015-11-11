package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLocking {
	public static void main(String[] args) throws Exception {
		final File f = new File("/home/zhangjin/test/1/" + 9999);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						FileOutputStream fos = new FileOutputStream(f);
						FileLock flc = getLock(fos.getChannel());
						if (flc != null) {
							System.out.println("write Locked File");
							fos.write(5);
							Thread.sleep(1000 * 5);
							flc.release();// 释放锁
							System.out.println("write Released Lock");
						} else {
							System.out.println("write wait File");
						}
						fos.close();
						Thread.sleep(1000 * 2);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private FileLock getLock(FileChannel fc) {
				FileLock fl = null;
				try {
					fl = fc.tryLock();
				} catch (Exception e) {
					System.out.println("No clock write");
				}
				return fl;
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						FileInputStream fis = new FileInputStream(f);
						FileLock flc = getLock(fis.getChannel());
						if (flc != null) {
							System.out.println("read Locked File");
							System.out.println(fis.read());
							Thread.sleep(1000 * 5);
							flc.release();// 释放锁
							System.out.println("read Released Lock");
						} else {
							System.out.println("read wait File");
						}
						fis.close();
						Thread.sleep(1000 * 3);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private FileLock getLock(FileChannel fc) {
				FileLock fl = null;
				try {
					fl = fc.tryLock(0, Long.MAX_VALUE, true);
				} catch (Exception e) {
					System.out.println("No clock read");
				}
				return fl;
			}
		}).start();
	}
}