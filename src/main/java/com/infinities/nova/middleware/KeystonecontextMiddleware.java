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
package com.infinities.nova.middleware;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Priority(1005)
public class KeystonecontextMiddleware extends Middleware {

	private final static Logger logger = LoggerFactory.getLogger(KeystonecontextMiddleware.class);
	@Context
	HttpServletRequest httpRequest;


	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.debug("KeystonecontextMiddleware begin");
		NovaKeystoneContextMiddleware middleware = new NovaKeystoneContextMiddleware();
		middleware.setRequest(httpRequest);
		try {
			middleware.call(requestContext);
		} catch (Exception e) {
			logger.error("keystoneContext problem", e);
			throw new RuntimeException(e);
		}
		logger.debug("KeystonecontextMiddleware end");
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

	}

}
