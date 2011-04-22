package ch.helvetia.jax2011.control;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import ch.helvetia.jax2011.common.stereotypes.Service;
import ch.helvetia.jax2011.db.TodoDb;
import ch.helvetia.jax2011.entity.User;

/**
 * Service for functionality around users.
 * 
 */
@Service
public class UserService {

	@Inject
	@TodoDb
	private EntityManager em;

	public User createNewUser() {
		return new User();
	}

	public void saveUser(User user) {
		em.persist(user);
	}

	public User authenticate(String name, String password) {
		User result = null;
		TypedQuery<User> query = em.createNamedQuery("authenticateUser",
				User.class);
		query.setParameter("userName", name);
		query.setParameter("userPassword", password);
		try {
			result = query.getSingleResult();
		} catch (NoResultException ignored) {
		} catch (NonUniqueResultException ignored) {
		}
		return result;
	}
}
