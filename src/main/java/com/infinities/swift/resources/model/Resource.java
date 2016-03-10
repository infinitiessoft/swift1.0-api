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
package com.infinities.swift.resources.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.infinities.nova.response.model.Link;

/**
 * @author pohsun
 *
 */
@XmlRootElement
public class Resource implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Link> links = new ArrayList<Link>(0);
	private String name;
	private String collection;


	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links
	 *            the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the collection
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	@Override
	public Resource clone() throws CloneNotSupportedException {
		Resource clone = (Resource) super.clone();
		return clone;
	}

}
