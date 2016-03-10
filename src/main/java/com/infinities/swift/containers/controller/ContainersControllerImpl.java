package com.infinities.swift.containers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.dasein.cloud.Tag;

import com.infinities.nova.NovaRequestContext;
import com.infinities.nova.common.model.MetadataTemplate;
import com.infinities.swift.containers.api.ContainersApi;
import com.infinities.swift.containers.model.Container;
import com.infinities.swift.containers.model.ContainerTemplate;
import com.infinities.swift.containers.views.ViewBuilder;
import com.infinities.swift.objects.model.Object;
import com.infinities.swift.objects.model.Objects;

public class ContainersControllerImpl implements ContainersController{

	private final ContainersApi containersApi;
	private final ViewBuilder builder = new ViewBuilder();
	private static final String CREATE_HEADER = "x-container-meta-";
	private static final String REMOVE_HEADER = "x-remove-container-meta-";
	
	public ContainersControllerImpl(ContainersApi containersApi) {
		this.containersApi = containersApi;
	}
	
	@Override
	public Objects index(ContainerRequestContext requestContext, String projectId, String container) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		List<Object> objects = containersApi.getObjects(context, projectId, container);
		return builder.index(requestContext, objects);
	}

	@Override
	public ContainerTemplate create(ContainerRequestContext requestContext, String projectId, String name) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		Container container = containersApi.create(context, projectId, name);
		return builder.show(requestContext, container);
	}

	@Override
	public ContainerTemplate create(ContainerRequestContext requestContext, String projectId, String name,
			MetadataTemplate metadata) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		Container container = containersApi.create(context, projectId, name, metadata.getMetadata());
		return builder.show(requestContext, container);
	}

	@Override
	public void delete(String projectId, String container, ContainerRequestContext requestContext) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		containersApi.deleteContainer(context, projectId, container);
	}

	@Override
	public Response show(ContainerRequestContext requestContext, String projectId, String container) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		ResponseBuilder builder = Response.noContent();
		
		Map<String, String> metadata = containersApi.getMetadata(context, container);
		for (String key : metadata.keySet()) {
			builder.header(key, metadata.get(key));
		}
		return builder.build();
	}

	@Override
	public void modifyMetadata(ContainerRequestContext requestContext, String projectId, String container)
			throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		MultivaluedMap<String, String> headerParams = requestContext.getHeaders();
		List<Tag> metadataAdded = new ArrayList<Tag>();
		List<Tag> metadataRemoved = new ArrayList<Tag>();
		
		Set<String> keys = headerParams.keySet();
		for (String key : keys) {
			if (key.startsWith(CREATE_HEADER)) {
				Tag tag = new Tag();
				tag.setKey(key.replaceAll(CREATE_HEADER, ""));
				tag.setValue(headerParams.getFirst(key));
				metadataAdded.add(tag);
			} else if (key.startsWith(REMOVE_HEADER)) {
				Tag tag = new Tag();
				tag.setKey(key.replaceAll(REMOVE_HEADER, ""));
				tag.setValue(headerParams.getFirst(key));
				metadataRemoved.add(tag);
			}
		}
		
		if (metadataAdded.size() != 0) {
			containersApi.addMetadata(context, container, metadataAdded.toArray(new Tag[metadataAdded.size()]));
		}
		if (metadataRemoved.size() != 0) {
			containersApi.removeMetadata(context, container, metadataRemoved.toArray(new Tag[metadataRemoved.size()]));
		}
	}
	
}
