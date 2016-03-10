package com.infinities.swift.accounts.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.dasein.cloud.storage.Blob;

import com.google.common.base.Preconditions;
import com.infinities.nova.Context;
import com.infinities.nova.NovaRequestContext;
import com.infinities.skyport.async.AsyncResult;
import com.infinities.skyport.async.service.storage.AsyncBlobStoreSupport;
import com.infinities.skyport.cache.CachedServiceProvider;
import com.infinities.skyport.service.ConfigurationHome;
import com.infinities.swift.accounts.model.Container;

public class DaseinAccountsApi implements AccountsApi {

	private ConfigurationHome configurationHome;

	@Inject
	public DaseinAccountsApi(ConfigurationHome configurationHome) {
		this.configurationHome = configurationHome;
	}

	@Override
	public List<Container> getContainers(NovaRequestContext context) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		// get bucket name
		AsyncResult<Iterable<Blob>> result = getSupport(context.getProjectId()).list(null);
		Iterable<Blob> iterable = result.get();
		Iterator<Blob> iterator = iterable.iterator();

		List<Container> containers = new ArrayList<Container>();
		while (iterator.hasNext()) {
			Blob blob = iterator.next();
			containers.add(toContainer(context, blob));
		}
		return containers;
	}

	@Override
	public Map<String, String> getAccountMetadata(NovaRequestContext context) throws Exception {
		if (context == null) {
			context = Context.getAdminContext("no");
		}
		String[] str = new String[1];
		str[0] = "";
		AsyncResult<Map<String, String>> result = getSupport(context.getProjectId()).getMetadata("");
		Map<String, String> metadata = result.get();
		Map<String, String> output = new HashMap<String, String>();
		for (String key : metadata.keySet()) {
			if (key.contains("X-Account-Object-Count") || key.contains("X-Account-Container-Count")
					|| key.contains("X-Account-Bytes-Used") || key.contains("X-Timestamp")
					|| key.contains("Accept-Ranges") || key.contains("Content-Type")) {
				output.put(key, metadata.get(key));
			}
		}
		return output;
	}

	private Container toContainer(NovaRequestContext context, Blob blob) throws Exception {
		Container output = new Container();
		output.setName(blob.getBucketName());

		AsyncResult<Iterable<Blob>> result = getSupport(context.getProjectId()).list(blob.getBucketName());
		Iterable<Blob> iterable = result.get();
		Iterator<Blob> iterator = iterable.iterator();
		int count = 0;
		long bytes = 0L;
		while (iterator.hasNext()) {
			count++;
			Blob container = iterator.next();
			bytes += container.getSize().longValue();
		}
		output.setBytesUsed(bytes);
		output.setObjectCount(count);
		return output;
	}

	private AsyncBlobStoreSupport getSupport(String id) throws ConcurrentException {
		CachedServiceProvider provider = configurationHome.findById(id);

		Preconditions.checkArgument(provider != null, "invalid project id:" + id);

		if (provider.hasStorageServices()) {
			if (provider.getStorageServices().hasOnlineStorageSupport()) {
				return provider.getStorageServices().getOnlineStorageSupport();
			}
		}
		throw new UnsupportedOperationException("service not supported for " + id);

	}

}
