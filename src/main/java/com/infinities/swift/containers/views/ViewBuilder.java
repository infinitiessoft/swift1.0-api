package com.infinities.swift.containers.views;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;

import com.infinities.swift.containers.model.Container;
import com.infinities.swift.containers.model.ContainerTemplate;
import com.infinities.swift.objects.model.Object;
import com.infinities.swift.objects.model.Objects;

public class ViewBuilder {

	/**
	 * @param requestContext
	 * @param objects
	 * @return
	 */
	public Objects index(ContainerRequestContext requestContext, List<Object> objects) {
		Objects template = new Objects();
		template.setObjects(objects);
		return template;
	}
	
	/**
	 * @param requestContext
	 * @param container
	 * @return
	 */
	public ContainerTemplate show(ContainerRequestContext requestContext, Container container) {
		ContainerTemplate template = new ContainerTemplate();
		template.setContainer(container);
		return template;
	}
	
}
