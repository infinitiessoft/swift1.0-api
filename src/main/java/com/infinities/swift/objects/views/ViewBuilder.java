package com.infinities.swift.objects.views;

import javax.ws.rs.container.ContainerRequestContext;

import com.infinities.swift.objects.model.Object;
import com.infinities.swift.objects.model.ObjectTemplate;

public class ViewBuilder {
	
	/**
	 * @param requestContext
	 * @param object
	 * @return
	 */
	public ObjectTemplate show(ContainerRequestContext requestContext, Object object) {
		ObjectTemplate template = new ObjectTemplate();
		template.setObject(object);
		return template;
	}

}
