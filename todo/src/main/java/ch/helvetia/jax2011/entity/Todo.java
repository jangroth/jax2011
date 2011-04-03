package ch.helvetia.jax2011.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A todo item.
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "findAllTodos", query = "SELECT t FROM Todo t ORDER BY t.dueDate ASC"),
		@NamedQuery(name = "findAllTodosByTag", query = "SELECT todo FROM Todo todo JOIN todo.tags tag WHERE tag.id = :tag ORDER BY todo.dueDate ASC") })
public class Todo {

	@GeneratedValue
	@Id
	private Long id;

	@NotEmpty
	private String name;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Future
	private Date dueDate;

	private String description;

	private Boolean done = Boolean.FALSE;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Tag> tags = new TreeSet<Tag>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * JSF doesnt like sets...
	 */
	public List<Tag> getTagsAsList() {
		return new ArrayList<Tag>(tags);
	}

}
