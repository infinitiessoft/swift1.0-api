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
package com.infinities.nova.common;

import javax.ws.rs.container.ContainerRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.infinities.nova.NovaRequestContext;
import com.infinities.nova.exception.http.HTTPBadRequestException;

public class Resource {

	private final static Logger logger = LoggerFactory.getLogger(Resource.class);


	public static void processStack(ContainerRequestContext requestContext, String projectid, NovaRequestContext context) {
		logger.debug("projectid: {}, context projectid: {}", new Object[] { projectid, context.getProjectId() });
		if (!Strings.isNullOrEmpty(projectid) && context != null && !projectid.equals(context.getProjectId())) {
			String msg =
					String.format("Malformed request URL: URL's project_id '%s' doesn't match Context's project_id '%s'",
							projectid, context.getProjectId());

			throw new HTTPBadRequestException(msg);
		}
	}
}