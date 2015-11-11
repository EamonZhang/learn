package ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheTest {

	public static void main(String[] args) {
		//Create a singleton CacheManager using defaults, then list caches.  
//		CacheManager manager = CacheManager.getInstance() ;
//		System.out.println(manager);
		
//		CacheManager manager = new CacheManager("/home/zhangjin/workspace/learn/src/myehcache.xml");  
//		String[] cacheNames = manager.getCacheNames();  
//		for (String string : cacheNames) {
//			System.out.println(string);
//		}
		
		//creates a cache called testCache, which  
		//will be configured using defaultCache from the configuration  
//		CacheManager singletonManager = CacheManager.getInstance();  
//		singletonManager.addCache("testCache");  
//		Cache test = singletonManager.getCache("testCache"); 
//		System.out.println(test);
	
//	        CacheManager singletonManager = CacheManager.create();  
//	        String[] names = singletonManager.getCacheNames();
//	        for (String string : names) {
//				System.out.println(string);
//			}
//	        Cache cache = singletonManager.getCache("mysampleCache3");
//	        System.out.println(cache);
//	        Element element = new Element("key1","value1");
//	        cache.put(element);
//	        cache.put(new Element("key1", "value2"));
//	        Element element1 = cache.get("key1");
//	        System.out.println(element1.getObjectValue());
//	        singletonManager.shutdown();
//	        Cache memoryOnlyCache = new Cache("testCache", 5000, false, false, 5, 2);  
//	        singletonManager.addCache(memoryOnlyCache);  
//	        Cache testCache = singletonManager.getCache("testCache");  
//	        System.out.println(testCache);
//	        singletonManager.getInstance().shutdown();
//		System.out.println(System.getProperty("java.io.tmpdir"));
//		CacheManager manager = CacheManager.getInstance();
//		Cache cache = manager.getCache("sampleCache3");
//		for(int i = 0;i<1000;i++){
//			Element element = new Element("key"+i,"value"+i);
//			cache.put(element);
//		}
//		int diskStoresize  = cache.getDiskStoreSize();
//		int size = cache.getSize();
//		long memSize = cache.getMemoryStoreSize();
//		System.out.println(diskStoresize+":"+size+":"+memSize);
//		cache.flush();
//		diskStoresize  = cache.getDiskStoreSize();
//		size = cache.getSize();
//		memSize = cache.getMemoryStoreSize();
//		System.out.println(diskStoresize+":"+size+":"+memSize);
//		manager.shutdown();
		
//		CacheManager manager = new CacheManager();  
//		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();  
//		ManagementService.registerMBeans(manager, mBeanServer, false, false, false, true); 
//		
//		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//		ManagementService.registerMBeans(cacheManager, mBeanServer, registerCacheManager, registerCaches, registerCacheConfigurations, registerCacheStatistics);
		
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache("myCache");
		cache.removeAll();
		for (int i = 0; i < 100000000; i++) {
			cache.put(new Element("key"+i, "v"+i));
		}
		cache.put(new Element("key4", "vh"));
		System.out.println(cache.getSize());
		cache.flush();
		System.out.println(cache.getSize());
		for (Object key : cache.getKeys()) {
			System.out.println(cache.get(key).getObjectValue());
		}
		cacheManager.shutdown();
	}
}
