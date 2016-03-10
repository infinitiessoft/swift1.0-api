/*******************************************************************************
 * Copyright 2015 InfinitiesSoft Solutions Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.infinities.swift.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infinities.swift.resources.api.ResourcesApi;
import com.infinities.swift.resources.model.Resources;

@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Version2Resource {

	private ResourcesApi versionApi;


	/**
	 * @param versionApi
	 */
	@Autowired
	public Version2Resource(ResourcesApi versionApi) {
		super();
		this.versionApi = versionApi;
	}

	@GET
	public Resources getVersion(@Context UriInfo uri) {
		try {
			return versionApi.show(uri.getRequestUri());
		} catch (CloneNotSupportedException e) {
			throw new WebApplicationException(e, Status.NOT_FOUND);
		}
	}

	@Path("{projectId}")
	public Class<ProjectMapperResource> getProjectMapperResource() {
		return ProjectMapperResource.class;
	}
	//
	// @Path("extensions")
	// public Class<ExtensionsResource> getExtensionsResource() {
	// return ExtensionsResource.class;
	// }

}
