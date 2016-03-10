package com.infinities.swift.objects.controller;

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
import com.infinities.swift.objects.api.ObjectsApi;
import com.infinities.swift.objects.model.Object;
import com.infinities.swift.objects.model.ObjectTemplate;
import com.infinities.swift.objects.views.ViewBuilder;

public class ObjectsControllerImpl implements ObjectsController{

	private final ObjectsApi objectsApi;
	private ViewBuilder builder = new ViewBuilder();
	private static final String CREATE_HEADER = "x-object-meta-";
	private static final String REMOVE_HEADER = "x-remove-object-meta-";
	
	public ObjectsControllerImpl(ObjectsApi objectsApi) {
		this.objectsApi = objectsApi;
	}
	
	@Override
	public ObjectTemplate show(ContainerRequestContext requestContext, String projectId, String container,
			String object) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		Object result = objectsApi.getObject(context, projectId, container, object);
		return builder.show(requestContext, result);
	}

	@Override
	public ObjectTemplate createOrUpdate(ContainerRequestContext requestContext, String projectId, String container,
			String object) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		Object result = objectsApi.createOrUpdate(context, projectId, container, object);
		return builder.show(requestContext, result);
	}
	
	@Override
	public void copy(ContainerRequestContext requestContext, String projectId, String container,
			String object, String targetContainer) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		objectsApi.copy(context, projectId, container, object, targetContainer);
	}


	@Override
	public void delete(ContainerRequestContext requestContext, String projectId, String container, String object)
			throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		objectsApi.deleteObject(context, projectId, container, object);
	}

	@Override
	public Response showMetadata(ContainerRequestContext requestContext, String projectId, String container,
			String object) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		ResponseBuilder builder = Response.ok();
		
		Map<String, String> metadata = objectsApi.getMetadata(context, container, object);
		for (String key : metadata.keySet()) {
			builder.header(key, metadata.get(key));
		}
		return builder.build();
	}

	@Override
	public void modifyMetadata(ContainerRequestContext requestContext, String projectId, String container,
			String object) throws Exception {
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
			objectsApi.addMetadata(context, container, object, metadataAdded.toArray(new Tag[metadataAdded.size()]));
		}
		if (metadataRemoved.size() != 0) {
			objectsApi.removeMetadata(context, container, object, metadataRemoved.toArray(new Tag[metadataRemoved.size()]));
		}
	}

}
