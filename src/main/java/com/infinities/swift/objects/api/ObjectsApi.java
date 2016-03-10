package com.infinities.swift.objects.api;

import java.util.Map;

import org.dasein.cloud.Tag;

import com.infinities.nova.NovaRequestContext;
import com.infinities.swift.objects.model.Object;

public interface ObjectsApi {

	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	Object getObject(NovaRequestContext context, String projectId, String container, String object) throws Exception;

	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	Object createOrUpdate(NovaRequestContext context, String projectId, String container, String object) throws Exception;

	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @param object
	 * @param targetContainer
	 * @throws Exception
	 */
	void copy(NovaRequestContext context, String projectId, String container, String object, String targetContainer) throws Exception;
	
	/**
	 * @param context
	 * @param projectId
	 * @param container
	 * @param object
	 * @throws Exception
	 */
	void deleteObject(NovaRequestContext context, String projectId, String container, String object) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @param object
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getMetadata(NovaRequestContext context, String container, String object) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	void addMetadata(NovaRequestContext context, String container, String object, Tag... tags) throws Exception;
	
	/**
	 * @param context
	 * @param container
	 * @param tags
	 * @return
	 * @throws Exception
	 */
	void removeMetadata(NovaRequestContext context, String container, String object, Tag... tags) throws Exception;
}
