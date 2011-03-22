package ch.helvetia.jax2011.db;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TransactionRequiredException;

/**
 * Producer for EntityManager
 */
@ConversationScoped
public class DbProducer implements Serializable {

	@PersistenceUnit(unitName = "todoApp")
	private EntityManagerFactory emf;

	private EntityManager em;

	@PostConstruct
	public void init() {
		em = emf.createEntityManager();
	}

	@Produces
	@TodoDb
	@RequestScoped
	// TODO: use @unwraps
	public EntityManager getEntityManager() {
		try {
			em.joinTransaction();
		} catch (TransactionRequiredException e) {
			// do nothing.
		}
		return em;
	}

}
