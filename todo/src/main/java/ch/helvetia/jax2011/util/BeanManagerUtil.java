package ch.helvetia.jax2011.util;

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

}
