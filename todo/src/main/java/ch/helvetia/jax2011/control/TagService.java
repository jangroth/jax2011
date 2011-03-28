package ch.helvetia.jax2011.control;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

	public List<Tag> findAllTags() {
		List<Tag> tags = em.createNamedQuery("findAllTags", Tag.class)
				.getResultList();
		return tags;
	}

}
