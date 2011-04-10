package ch.helvetia.jax2011.control;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.helvetia.jax2011.common.stereotypes.Service;
import ch.helvetia.jax2011.db.TodoDb;
import ch.helvetia.jax2011.entity.Tag;

/**
 * Service for Tags.
 */
@Service
public class TagService {

	@Inject
	@TodoDb
	private EntityManager em;

	public Tag createNewTag() {
		Tag result = new Tag();
		return result;
	}

	public void saveTodo(Tag tag) {
		em.persist(tag);
	}

	public void updateTodo(Tag tag) {
		em.merge(tag);
	}

	/**
	 * return all tags in the database
	 */
	public Set<Tag> findAllTags() {
		List<Tag> tags = em.createNamedQuery("findAllTags", Tag.class)
				.getResultList();
		return new TreeSet<Tag>(tags);
	}

	/**
	 * returning resultlist contains tag and count(tag)
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> countTags(Date callDate) {
		Query query = em.createNamedQuery("countTags");
		query.setParameter("callDate", callDate);
		return query.getResultList();
	}

}
