package com.infinities.swift.resource;

import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.infinities.swift.versions.api.VersionsApi;

@Singleton
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class SwiftResource {

	private final VersionsApi versionsApi;


	@Inject
	public SwiftResource(VersionsApi versionsApi) {
		this.versionsApi = versionsApi;
	}

	@Path("/v2.0")
	public Class<Version2Resource> getApiV2Resource() {
		return Version2Resource.class;
	}

	@GET
	public Response index(@Context UriInfo uri) throws URISyntaxException {
		return versionsApi.index(uri.getRequestUri());
	}
}

