package pool;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool.PoolableObjectFactory;

public class PoolableObjectFactoryDemo implements PoolableObjectFactory {
	private static AtomicInteger counter = new AtomicInteger(0);
	@Override
	public void activateObject(Object obj) throws Exception {
		System.out.println("Before borrow "+obj);
	}

	@Override
	public void destroyObject(Object obj) throws Exception {
		System.out.println("Destorying Object "+obj);
	}

	@Override
	public Object makeObject() throws Exception {
		Object obj = String.valueOf(counter.getAndIncrement());
		System.out.println("Create Object "+obj);
		return obj;
	}

	@Override
	public void passivateObject(Object obj) throws Exception {
		System.out.print("return "+obj);
	}

	@Override
	public boolean validateObject(Object obj) {
		return true;
	}

}
