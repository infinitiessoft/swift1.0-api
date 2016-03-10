package com.infinities.swift.containers.controller;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

import com.infinities.nova.common.model.MetadataTemplate;
import com.infinities.swift.containers.model.ContainerTemplate;
import com.infinities.swift.objects.model.Objects;

public interface ContainersController {

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	Objects index(ContainerRequestContext requestContext, String projectId, String container) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	ContainerTemplate create(ContainerRequestContext requestContext, String projectId,
			String container) throws Exception;
	
	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @param metadata
	 * @return
	 * @throws Exception
	 */
	ContainerTemplate create(ContainerRequestContext requestContext, String projectId, String container, MetadataTemplate metadata) throws Exception;

	/**
	 * @param projectId
	 * @param container
	 * @param requestContext
	 * @throws Exception
	 */
	void delete(String projectId, String container, ContainerRequestContext requestContext) throws Exception;
	
	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	Response show(ContainerRequestContext requestContext, String projectId, String container) throws Exception;

	/**
	 * @param requestContext
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	void modifyMetadata(ContainerRequestContext requestContext, String projectId, String container) throws Exception;

}
