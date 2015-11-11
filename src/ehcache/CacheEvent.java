package ehcache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

public class CacheEvent implements CacheEventListener{

	@Override
	public void notifyElementRemoved(Ehcache cache, Element element)
			throws CacheException {
		System.out.println("notifyElementRemoved");
	}

	@Override
	public void notifyElementPut(Ehcache cache, Element element)
			throws CacheException {
		System.out.println("notifyElementPut"+element.getKey()+"	"+element.getValue());
	}

	@Override
	public void notifyElementUpdated(Ehcache cache, Element element)
			throws CacheException {
		System.out.println("notifyElementUpdated");
	}

	@Override
	public void notifyElementExpired(Ehcache cache, Element element) {
		System.out.println("notifyElementExpired");
	}

	@Override
	public void notifyElementEvicted(Ehcache cache, Element element) {
		System.out.println("notifyElementEvicted");
	}

	@Override
	public void notifyRemoveAll(Ehcache cache) {
		System.out.println("notifyRemoveAll");
	}

	@Override
	public void dispose() {
		System.out.println("dispose");
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
	return super.clone();
}
}
