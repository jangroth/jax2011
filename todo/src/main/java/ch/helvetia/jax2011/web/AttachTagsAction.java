package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ch.helvetia.jax2011.boundary.CreateTodoTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Tag;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Action to attach tags to a newly created todo-item
 */
@Action
public class AttachTagsAction implements Serializable {

	@Inject
	private CreateTodoTask task;

	private Tag[] selectedTags;

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			task.findAllTags();
		}
	}

	public String attachTags() {
		task.addTags(selectedTags);
		task.saveTodo();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "Tags attached", getMessage()));
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
		// TODO: investigate if this can be handled in a seam 3 way
		task.finish();
		return "/home.xhtml?faces-redirect=true";
	}

	private String getMessage() {
		String result;
		if (selectedTags.length == 0) {
			result = "No tags were attached.";
		} else {
			result = "Attached tags:";
			for (Tag tag : selectedTags) {
				result += " " + tag.getName();
			}
		}
		return result;
	}

	public Todo getTodo() {
		return task.getTodo();
	}

	public List<Tag> getTags() {
		return new ArrayList<Tag>(task.getTags());
	}

	public Tag[] getSelectedTags() {
		return selectedTags;
	}

	public void setSelectedTags(Tag[] selectedTags) {
		this.selectedTags = selectedTags;
	}

}
