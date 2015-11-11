package test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;

public class Test implements Comparable{
	public static void main(String[] args) {
//		String abc=new String("abc");  //1     
//		SoftReference<String> abcSoftRef=new SoftReference<String>(abc);  //2     
//		WeakReference<String> abcWeakRef = new WeakReference<String>(abc); //3     
//		abc=null; //4     
//		abcSoftRef.clear();//5
//		for (Object iter : System.getProperties().keySet()) {
//		}
//		Test t = new Test();
//		int r= t.getValue(1);
//		System.out.println(r);
//		System.gc();
//		System.out.println("ok");
		System.out.println(1==(byte)1);
		ObjectPool poo ;
		PoolableObjectFactory factory;
//		Observer
//		Observable
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@SuppressWarnings("finally")
	public int getValue(int a){
		if(a == 1){
			return 1;
		}
		else{
			int r = 0;
			try {
				r = a/a;
				return r;
			} catch (Exception e) {
			}finally{
				return 8;
			}
		}
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("fff");
		super.finalize();
	}
}
