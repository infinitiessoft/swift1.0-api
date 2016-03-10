package com.infinities.swift.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import com.infinities.api.openstack.commons.http.methods.COPY;
import com.infinities.nova.NovaRequestContext;
import com.infinities.nova.common.Resource;
import com.infinities.nova.common.model.MetadataTemplate;
import com.infinities.nova.namebinding.CheckProjectId;
import com.infinities.swift.accounts.controller.AccountsController;
import com.infinities.swift.accounts.model.Containers;
import com.infinities.swift.containers.controller.ContainersController;
import com.infinities.swift.objects.controller.ObjectsController;
import com.infinities.swift.objects.model.ObjectTemplate;
import com.infinities.swift.objects.model.Objects;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@CheckProjectId
public class StoragesResource {

	private AccountsController controller;
	private ContainersController containersController;
	private ObjectsController objectsController;

	@Inject
	public StoragesResource(AccountsController controller, ContainersController containersController,
			ObjectsController objectsController) {
		this.controller = controller;
		this.containersController = containersController;
		this.objectsController = objectsController;
	}

	// ********* Accounts *********

	@GET
	public Containers listContainers(@PathParam("projectId") String projectId, @Context ContainerRequestContext requestContext)
			throws Exception {
		return controller.index(requestContext, projectId);
	}

	@HEAD
	@Produces(MediaType.TEXT_PLAIN)
	public Response showAccountMetadata(@PathParam("projectId") String projectId, @Context ContainerRequestContext requestContext)
			throws Exception {
		return controller.show(requestContext, projectId);
	}

	// ********* Containers *********

	@GET
	@Path("{container}")
	public Objects listObjects(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@Context ContainerRequestContext requestContext) throws Exception {
		return containersController.index(requestContext, projectId, container);
	}

	@PUT
	@Path("{container}")
	public Response create(@PathParam("projectId") String projectId, @PathParam("container") String container,
			MetadataTemplate metadata, @Context ContainerRequestContext requestContext) throws Exception {
		if (metadata == null) {
			containersController.create(requestContext, projectId, container);
		} else {
			containersController.create(requestContext, projectId, container, metadata);
		}
		return Response.status(Status.CREATED).build();
	}

	@DELETE
	@Path("{container}")
	public Response delete(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@Context ContainerRequestContext requestContext) throws Exception {
		containersController.delete(projectId, container, requestContext);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@POST
	@Path("{container}")
	public Response changeContainerMetadata(@Context ContainerRequestContext requestContext, @PathParam("projectId") String projectId, @PathParam("container") String container) throws Exception {
		containersController.modifyMetadata(requestContext, projectId, container);
		return Response.status(Status.NO_CONTENT).build();
	}

	@HEAD
	@Path("{container}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response showContainerMetadata(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@Context ContainerRequestContext requestContext) throws Exception {
		return containersController.show(requestContext, projectId, container);
	}
	
	// ********* Objects *********

	@GET
	@Path("{container}/{object:.*}")
	public ObjectTemplate show(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@PathParam("object") String object, @Context ContainerRequestContext requestContext) throws Exception {
		return objectsController.show(requestContext, projectId, container, object);
	}

	@PUT
	@Path("{container}/{object:.*}")
	public ObjectTemplate createOrUpdate(@PathParam("projectId") String projectId,
			@PathParam("container") String container, @PathParam("object") String object,
			@Context ContainerRequestContext requestContext) throws Exception {
		return objectsController.createOrUpdate(requestContext, projectId, container, object);
	}

	@DELETE
	@Path("{container}/{object:.*}")
	public Response delete(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@PathParam("object") String object, @Context ContainerRequestContext requestContext) throws Exception {
		objectsController.delete(requestContext, projectId, container, object);
		return Response.status(Status.ACCEPTED).build();
	}

	@COPY
	@Path("{container}/{object:.*}")
	public Response copy(@PathParam("projectId") String projectId, @PathParam("container") String container,
			@PathParam("object") String object, @HeaderParam("Destination") String targetContainer,
			@Context ContainerRequestContext requestContext) throws Exception {
		objectsController.copy(requestContext, projectId, container, object, targetContainer);
		return Response.status(Status.CREATED).build();
	}
	
	@HEAD
	@Path("{container}/{object:.*}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response showContainerMetadata(@PathParam("projectId") String projectId, @PathParam("container") String container, @PathParam("object") String object,
			@Context ContainerRequestContext requestContext) throws Exception {
		return objectsController.showMetadata(requestContext, projectId, container, object);
	}
	
	@POST
	@Path("{container}/{object:.*}")
	public Response changeObjectMetadata(@Context ContainerRequestContext requestContext, @PathParam("projectId") String projectId, @PathParam("container") String container, @PathParam("object") String object) throws Exception {
		objectsController.modifyMetadata(requestContext, projectId, container, object);
		return Response.status(Status.NO_CONTENT).build();
	}
}
