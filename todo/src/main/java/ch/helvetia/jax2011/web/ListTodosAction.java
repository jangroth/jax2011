package ch.helvetia.jax2011.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import ch.helvetia.jax2011.boundary.ListTodosTask;
import ch.helvetia.jax2011.common.stereotypes.Action;
import ch.helvetia.jax2011.entity.Tag;
import ch.helvetia.jax2011.entity.Todo;

/**
 * Action to create a new todo-item.
 */
@Action
public class ListTodosAction implements Serializable {

	@Inject
	private ListTodosTask task;

	private Date callDate = new Date();

	private TagCloudModel tagCloudModel = new DefaultTagCloudModel();

	// TODO: introduce seam3 view-action
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			task.findAllTodos(callDate);
			initTagCloudModel();
		}
	}

	public void dateChanged(DateSelectEvent e) {
		callDate = e.getDate();
		task.findAllTodos(callDate);
	}

	private void initTagCloudModel() {
		TagCloudModel result = new DefaultTagCloudModel();
		for (Object[] tagCount : task.countTags()) {
			Tag loopTag = (Tag) tagCount[0];
			int loopCount = ((Long) tagCount[1]).intValue();
			result.addTag(new DefaultTagCloudItem(loopTag.getName(), "#",
					loopCount));
		}
		tagCloudModel = result;
	}

	//
	// getter & setter
	//

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public List<Todo> getTodos() {
		return task.getTodos();
	}

	public ListTodosTask getTask() {
		return task;
	}

	public TagCloudModel getTagCloudModel() {
		return tagCloudModel;
	}

}
