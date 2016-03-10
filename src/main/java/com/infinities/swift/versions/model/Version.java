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
package com.infinities.swift.versions.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.infinities.nova.response.model.Link;
import com.infinities.swift.resources.model.Resource;

public class Version implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String updated;
	private List<Link> links = new ArrayList<Link>(0);
	@XmlElement(name = "media-types", nillable = false)
	private List<MediaType> mediaTypes = new ArrayList<MediaType>(0);
	@XmlTransient
	private List<Resource> resources = new ArrayList<Resource>(0);


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<MediaType> getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(List<MediaType> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

	/**
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources
	 *            the resources to set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((links == null) ? 0 : links.hashCode());
		result = prime * result + ((mediaTypes == null) ? 0 : mediaTypes.hashCode());
		result = prime * result + ((resources == null) ? 0 : resources.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Version other = (Version) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (links == null) {
			if (other.links != null)
				return false;
		} else if (!links.equals(other.links))
			return false;
		if (mediaTypes == null) {
			if (other.mediaTypes != null)
				return false;
		} else if (!mediaTypes.equals(other.mediaTypes))
			return false;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Version [id=" + id + ", status=" + status + ", updated=" + updated + ", links=" + links + ", mediaTypes="
				+ mediaTypes + ", resources=" + resources + "]";
	}

	@Override
	public Version clone() throws CloneNotSupportedException {
		Version clone = (Version) super.clone();

		clone.links = new ArrayList<Link>(this.links.size());
		for (int i = 0; i < links.size(); i++) {
			Link link = links.get(i).clone();
			clone.links.add(link);
		}

		clone.mediaTypes = new ArrayList<MediaType>(this.mediaTypes.size());
		for (int i = 0; i < mediaTypes.size(); i++) {
			MediaType mediaType = mediaTypes.get(i).clone();
			clone.mediaTypes.add(mediaType);
		}

		clone.resources = new ArrayList<Resource>(this.resources.size());
		for (int i = 0; i < resources.size(); i++) {
			Resource resource = resources.get(i).clone();
			clone.resources.add(resource);
		}

		return clone;
	}

}
