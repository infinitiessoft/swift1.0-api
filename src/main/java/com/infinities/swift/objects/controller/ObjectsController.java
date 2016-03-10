package com.infinities.swift.objects.controller;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import com.infinities.swift.objects.model.ObjectTemplate;

public interface ObjectsController {
	
	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	ObjectTemplate show(ContainerRequestContext requestContext, String projectId, String container, String object) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	ObjectTemplate createOrUpdate(ContainerRequestContext requestContext, String projectId, String container, String object) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param object
	 * @param targetContainer
	 * @throws Exception
	 */
	void copy(ContainerRequestContext requestContext, String projectId, String container, String object, String targetContainer) throws Exception;

	
	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param object
	 * @throws Exception
	 */
	void delete(ContainerRequestContext requestContext, String projectId, String container, String object) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	Response showMetadata(ContainerRequestContext requestContext, String projectId, String container, String object) throws Exception;
	
	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	void modifyMetadata(ContainerRequestContext requestContext, String projectId, String container, String object) throws Exception;
}
