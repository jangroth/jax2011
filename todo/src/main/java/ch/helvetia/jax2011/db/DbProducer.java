package ch.helvetia.jax2011.db;

import java.io.Serializable;
import java.lang.reflect.Proxy;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Producer for EntityManager
 */
@ApplicationScoped
// TODO: use Seam 3 persistence
public class DbProducer implements Serializable {

	@PersistenceUnit(unitName = "todoApp")
	private EntityManagerFactory emf;

	@Produces
	@TodoDb
	@ConversationScoped
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();

		ManagedPersistenceContextProxyHandler handler = new ManagedPersistenceContextProxyHandler(em);
		em = (EntityManager) Proxy.newProxyInstance(EntityManager.class.getClassLoader(),
				new Class[] { EntityManager.class }, handler);

		return em;
	}

}
