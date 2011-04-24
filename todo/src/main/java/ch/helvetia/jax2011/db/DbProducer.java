package ch.helvetia.jax2011.db;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TransactionRequiredException;

import org.jboss.seam.solder.unwraps.Unwraps;

/**
 * Producer for EntityManager
 */
@ConversationScoped
// TODO: use Seam 3 persistence
public class DbProducer implements Serializable {

	@PersistenceUnit(unitName = "todoApp")
	private EntityManagerFactory emf;

	private EntityManager em;

	@PostConstruct
	public void init() {
		em = emf.createEntityManager();
	}

	@Unwraps
	@TodoDb
	public EntityManager getEntityManager() {

		try {
			em.joinTransaction();
		} catch (TransactionRequiredException e) {
			// do nothing.
		}

		return em;
	}

}
