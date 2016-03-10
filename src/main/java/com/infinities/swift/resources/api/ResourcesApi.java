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
package com.infinities.swift.resources.api;

import java.net.URI;

import com.infinities.swift.resources.model.Resources;
import com.infinities.swift.versions.api.VersionsApi;
import com.infinities.swift.versions.views.ViewBuilder;

public class ResourcesApi {

	public Resources show(URI uri) throws CloneNotSupportedException {
		ViewBuilder builder = ViewBuilder.getViewBuilder(uri);
		Resources resources = builder.buildResources(VersionsApi.VERSIONS.get("v2.0"));
		return resources;
	}

}
