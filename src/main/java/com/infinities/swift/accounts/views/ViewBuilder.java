package com.infinities.swift.accounts.views;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;

import com.infinities.swift.accounts.model.Container;
import com.infinities.swift.accounts.model.Containers;

public class ViewBuilder {

	/**
	 * @param requestContext
	 * @param containers
	 * @return
	 */
	public Containers index(ContainerRequestContext requestContext, List<Container> containers) {
		Containers ret = new Containers();
		ret.setContainers(containers);
		return ret;
	}
}
