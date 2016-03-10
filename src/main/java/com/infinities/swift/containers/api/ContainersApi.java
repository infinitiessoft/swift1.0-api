package com.infinities.swift.containers.api;

import java.util.List;
import java.util.Map;

import org.dasein.cloud.Tag;

import com.infinities.nova.NovaRequestContext;
import com.infinities.swift.containers.model.Container;
import com.infinities.swift.objects.model.Object;

public interface ContainersApi {

	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @return
	 * @throws Exception
	 */
	List<Object> getObjects(NovaRequestContext context, String projectId, String container) throws Exception;

	Container create(NovaRequestContext context, String projectId, String container) throws Exception;

	Container create(NovaRequestContext context, String projectId, String container, Map<String, String> metadata) throws Exception;
	
	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @throws Exception
	 */
	void deleteContainer(NovaRequestContext context, String projectId, String container) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getMetadata(NovaRequestContext context, String container) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	void addMetadata(NovaRequestContext context, String container, Tag... tags) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	void removeMetadata(NovaRequestContext context, String container, Tag... tags) throws Exception;
}