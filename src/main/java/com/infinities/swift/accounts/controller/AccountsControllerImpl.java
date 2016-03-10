package com.infinities.swift.accounts.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.infinities.nova.NovaRequestContext;
import com.infinities.swift.accounts.api.AccountsApi;
import com.infinities.swift.accounts.model.Container;
import com.infinities.swift.accounts.model.Containers;
import com.infinities.swift.accounts.views.ViewBuilder;

public class AccountsControllerImpl implements AccountsController{

	private final AccountsApi accountsApi;
	private final ViewBuilder builder = new ViewBuilder();
	
	public AccountsControllerImpl(AccountsApi accountsApi) {
		this.accountsApi = accountsApi;
	}
	
	@Override
	public Containers index(ContainerRequestContext requestContext, String projectId) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		List<Container> containers = accountsApi.getContainers(context);
		return builder.index(requestContext, containers);
	}

	@Override
	public Response show(ContainerRequestContext requestContext, String projectId) throws Exception {
		NovaRequestContext context = (NovaRequestContext) requestContext.getProperty("nova.context");
		ResponseBuilder builder = Response.noContent();
		
		Map<String, String> metadata = accountsApi.getAccountMetadata(context);
		for (String key : metadata.keySet()) {
			builder.header(key, metadata.get(key));
		}
		return builder.build();
	}
	
}
