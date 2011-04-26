package ch.helvetia.jax2011.util;

import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * A utility for use in non-managed classes, which are not able to obtain a
 * reference to the {@link BeanManager} using injection.
 * A JNDI environment is need for lookup.
 * TODO Use Seam Solder
 */
public class BeanManagerUtil {

	public static BeanManager getBeanManager() {
		try {
			return (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
		} catch (NamingException e) {
			try {
				return (BeanManager) new InitialContext().lookup("java:app/BeanManager");
			} catch (NamingException e1) {
				throw new RuntimeException(e1);
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> beanClass) {
		BeanManager manager = getBeanManager();
		Set<Bean<?>> beans = manager.getBeans(beanClass);
		if (beans.size() != 1) {
			if (beans.size() == 0) {
				throw new RuntimeException("No beans of class "
						+ beanClass + " found.");
			} else {
				throw new RuntimeException("Multiple beans of class "
						+ beanClass + " found: " + beans + ".");
			}
		}
		Bean<T> myBean = (Bean<T>) beans.iterator().next();
		return (T) manager.getReference(myBean, beanClass,
				manager.createCreationalContext(myBean));
	}

}
