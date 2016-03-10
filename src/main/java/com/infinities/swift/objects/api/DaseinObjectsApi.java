package com.infinities.swift.objects.api;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.dasein.cloud.Tag;
import org.dasein.cloud.storage.Blob;

import com.google.common.base.Preconditions;
import com.infinities.nova.Context;
import com.infinities.nova.NovaRequestContext;
import com.infinities.skyport.async.AsyncResult;
import com.infinities.skyport.cache.CachedServiceProvider;
import com.infinities.skyport.cache.service.storage.CachedBlobStoreSupport;
import com.infinities.skyport.service.ConfigurationHome;
import com.infinities.swift.objects.model.Object;

public class DaseinObjectsApi implements ObjectsApi{
	
	private ConfigurationHome configurationHome;


	@Inject
	public DaseinObjectsApi(ConfigurationHome configurationHome) {
		this.configurationHome = configurationHome;
	}

	@Override
	public Object getObject(NovaRequestContext context, String projectId, String container, String object)
			throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Blob> result = getSupport(context.getProjectId()).getObject(container, object);
		Blob blob = result.get();
		return toObject(context, blob);
	}
	
	@Override
	public Object createOrUpdate(NovaRequestContext context, String projectId, String container, String object)
			throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		File file = new File(File.separator + object);
		String[] tmp = object.split("/");
		String objectName = tmp[tmp.length - 1];
		
		AsyncResult<Blob> result = getSupport(context.getProjectId()).upload(file, container, objectName);
		Blob blob = result.get();
		return toObject(context, blob);
	}
	
	@Override
	public void deleteObject(NovaRequestContext context, String projectId, String container, String object)
			throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		getSupport(context.getProjectId()).removeObject(container, object);
	}
	
	@Override
	public void copy(NovaRequestContext context, String projectId, String container, String object, String targetContainer) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		getSupport(context.getProjectId()).copy(container, object, targetContainer, object);
	}
	
	@Override
	public Map<String, String> getMetadata(NovaRequestContext context, String container, String object) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Map<String, String>> result = getSupport(context.getProjectId()).getMetadata(container, object);
		Map<String, String> metadata = result.get();
		Map<String, String> output = new HashMap<String, String>();
		for (String key : metadata.keySet()) {
			if (key.equals("Content-Length:") || key.equals("Accept-Ranges:") || key.equals("X-Timestamp:") || key.equals("Content-Type:")) {
				output.put(key, metadata.get(key));
			}
		}
		return metadata;
	}
	
	private Object toObject(NovaRequestContext context, Blob blob) throws Exception {
		Object output = new Object();
		output.setName(blob.getBucketName());
		output.setBytes(blob.getSize().longValue());
		output.setName(blob.getObjectName());
		return output;
	}
	
	private CachedBlobStoreSupport getSupport(String id) throws ConcurrentException {
		CachedServiceProvider provider = configurationHome.findById(id);

		Preconditions.checkArgument(provider != null, "invalid project id:" + id);

		if (provider.hasStorageServices()) {
			if (provider.getStorageServices().hasOnlineStorageSupport()) {
				return provider.getStorageServices().getOnlineStorageSupport();
			}
		}
		throw new UnsupportedOperationException("service not supported for " + id);
	}

	@Override
	public void addMetadata(NovaRequestContext context, String container, String object, Tag... tags) throws Exception {
		CachedBlobStoreSupport support = getSupport(context.getProjectId());
		Map<String, String> metadata = support.getObject(container, object).get().getTags();
		if (metadata == null || metadata.size() == 0)
			support.setObjectTags(container, object, tags);
		else
			support.updateObjectTags(container, object, tags);
	}

	@Override
	public void removeMetadata(NovaRequestContext context, String container, String object, Tag... tags) throws Exception {
		getSupport(context.getProjectId()).removeObjectTags(container, object, tags);
	}

}
