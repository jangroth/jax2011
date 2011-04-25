package ch.helvetia.jax2011.se;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;

public class TodoAppStart {

	public static void main(String[] args) {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		container.event().select(ContainerInitialized.class).fire(new ContainerInitialized());
		weld.shutdown();
	}

}
