package ch.helvetia.jax2011.db;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

/**
 * This handler makes sure that the EntityManager is enrolled in the current transaction before passing the call through
 * to the delegate
 */
// TODO: use Seam 3 persistence
public class ManagedPersistenceContextProxyHandler implements InvocationHandler {

	private final EntityManager delegate;

	public ManagedPersistenceContextProxyHandler(EntityManager delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.joinTransaction();
		return method.invoke(delegate, args);
	}

	private void joinTransaction() {
		try {
			this.delegate.joinTransaction();
		} catch (TransactionRequiredException e) {
			// do nothing.
		}
	}
}
