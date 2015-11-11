package test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class ReferenceTest {
    public static boolean isRun = true;      
    
    public static void main(String[] args) throws Exception {      
        String abc = new String("abc");      
        System.out.println(abc.getClass() + "@" + abc.hashCode());      
        final ReferenceQueue referenceQueue = new ReferenceQueue<String>();      
        new Thread() {      
            public void run() {      
                while (isRun) {      
                    Object o = referenceQueue.poll();      
                    if (o != null) {      
                        try {      
                            Field rereferent = Reference.class     
                                    .getDeclaredField("referent");      
                            rereferent.setAccessible(true);      
                            Object result = rereferent.get(o);      
                            if(result == null){
                            	System.out.println("reference null");
                            }else{
	                            System.out.println("gc will collect:"     
	                                    + result.getClass() + "@"     
	                                    + result.hashCode());      
                            }
                        } catch (Exception e) {      
     
                            e.printStackTrace();      
                        }      
                    }      
                }      
            }      
        }.start();      
        PhantomReference<String> abcPhantomRef = new PhantomReference<String>(abc,      
                referenceQueue);      
//        WeakReference<String> abcWeakRef = new WeakReference<String>(abc,      
//                referenceQueue);     
//        SoftReference<String> abcSoftRef = new SoftReference<String>(abc,      
//                referenceQueue);     
        abc = null;      
        Thread.currentThread().sleep(3000);      
        System.gc();      
        Thread.currentThread().sleep(3000);      
        isRun = false;      
    }      
}
