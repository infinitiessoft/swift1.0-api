package com.infinities.swift.accounts.controller;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import com.infinities.swift.accounts.model.Containers;

public interface AccountsController {

	/**
	 * @param requestContext
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	Containers index(ContainerRequestContext requestContext, String projectId) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @return
	 * @throws Exception
	 */
	Response show(ContainerRequestContext requestContext, String projectId) throws Exception;

}
