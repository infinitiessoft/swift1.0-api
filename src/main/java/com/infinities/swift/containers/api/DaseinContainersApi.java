package com.infinities.swift.containers.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.infinities.swift.containers.model.Container;
import com.infinities.swift.objects.model.Object;

public class DaseinContainersApi implements ContainersApi{
	
	private ConfigurationHome configurationHome;

	@Inject
	public DaseinContainersApi(ConfigurationHome configurationHome) {
		this.configurationHome = configurationHome;
	}

	@Override
	public List<Object> getObjects(NovaRequestContext context, String projectId, String container) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Iterable<Blob>> result = getSupport(context.getProjectId()).list(container);
		Iterable<Blob> iterable = result.get();
		Iterator<Blob> iterator = iterable.iterator();
		
		List<Object> objects = new ArrayList<Object>() ;
		while (iterator.hasNext()) {
			Blob blob = iterator.next();
			objects.add(toObject(context, blob));
		}
		return objects;
	}
	
	private Object toObject(NovaRequestContext context, Blob blob) throws Exception {
		Object output = new Object();
		output.setName(blob.getBucketName());
		
		AsyncResult<Blob> result = getSupport(context.getProjectId()).getObject(blob.getBucketName(), blob.getObjectName());
		Blob tmp = result.get();
		output.setBytes(tmp.getSize().longValue());
		output.setName(tmp.getObjectName());
		
		Map<String, String> metadata = getSupport(context.getProjectId()).getMetadata(blob.getBucketName(), blob.getObjectName()).get();
		if (metadata.containsKey("Content-Type")) {
			output.setContentType(metadata.get("Content-Type"));
		}
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
	public Container create(NovaRequestContext context, String projectId, String name) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Blob> result = getSupport(context.getProjectId()).createBucket(name, true);
		Blob blob = result.get();
		return toContainer(blob);
	}
	
	private Container toContainer(Blob blob) {
		Container output = new Container();
		output.setName(blob.getBucketName());
		output.setObjectCount(0);
		if (blob.getSize() == null) {
			output.setBytesUsed(0L);
		} else {
			output.setBytesUsed(blob.getSize().longValue());
		}
		return output;
	}

	@Override
	public Container create(NovaRequestContext context, String projectId, String name, Map<String, String> metadata)
			throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Blob> result = getSupport(context.getProjectId()).createBucket(name, true);
		Blob blob = result.get();
		Tag[] tags = new Tag[metadata.size()];
		int i = 0;
		for (Entry<String, String> meta : metadata.entrySet()) {
			tags[i++] = new Tag(meta.getKey(), meta.getValue());
		}
		getSupport(context.getProjectId()).updateTags(blob.getBucketName(), tags);
		return toContainer(blob);
	}

	@Override
	public void deleteContainer(NovaRequestContext context, String projectId, String container) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		getSupport(context.getProjectId()).removeBucket(container);
	}

	@Override
	public Map<String, String> getMetadata(NovaRequestContext context, String container) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		AsyncResult<Map<String, String>> result = getSupport(context.getProjectId()).getMetadata(container);
		Map<String, String> metadata = result.get();
		return metadata;
	}

	@Override
	public void addMetadata(NovaRequestContext context, String container, Tag... tags) throws Exception {
		CachedBlobStoreSupport support = getSupport(context.getProjectId());
		Map<String, String> metadata = support.getBucket(container).get().getTags();
		if (metadata == null || metadata.size() == 0)
			support.setTags(container, tags);
		else
			support.updateTags(container, tags);
	}
	
	@Override
	public void removeMetadata(NovaRequestContext context, String container, Tag... tags) throws Exception {
		getSupport(context.getProjectId()).removeTags(container, tags);
	}
}
