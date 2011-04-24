package ch.helvetia.jax2011.db;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.jboss.seam.solder.core.ExtensionManaged;

/**
 * Producer for EntityManager
 */
// @ConversationScoped
public class DbProducer implements Serializable {

	@PersistenceUnit(unitName = "todoApp")
	@TodoDb
	@ConversationScoped
	@Produces
	@ExtensionManaged
	EntityManagerFactory emf;

//	@PersistenceUnit(unitName = "todoApp")
//	private EntityManagerFactory emf;
//
//	private EntityManager em;
//
//	@PostConstruct
//	public void init() {
//		em = emf.createEntityManager();
//	}
//
//	@Unwraps
//	@TodoDb
//	public EntityManager getEntityManager() {
//
//		try {
//			em.joinTransaction();
//		} catch (TransactionRequiredException e) {
//			// do nothing.
//		}
//
//		return em;
//	}

}
