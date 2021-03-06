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
package com.infinities.nova.response.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "flavor")
public class Flavor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private Integer vcpus;

	private Integer ram;

	private Integer disk;

	@XmlElement(name = "OS-FLV-EXT-DATA:ephemeral")
	private Integer ephemeral;

	private String swap;

	@XmlElement(name = "rxtx_factor")
	private Float rxtxFactor;

	@XmlElement(name = "OS-FLV-DISABLED:disabled")
	private Boolean disabled;

	@XmlElement(name = "rxtx_quota")
	private Integer rxtxQuota;

	@XmlElement(name = "rxtx_cap")
	private Integer rxtxCap;

	private List<Link> links = new ArrayList<Link>();

	@XmlElement(name = "os-flavor-access:is_public")
	private Boolean isPublic;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vcpus
	 */
	public Integer getVcpus() {
		return vcpus;
	}

	/**
	 * @param vcpus the vcpus to set
	 */
	public void setVcpus(Integer vcpus) {
		this.vcpus = vcpus;
	}

	/**
	 * @return the ram
	 */
	public Integer getRam() {
		return ram;
	}

	/**
	 * @param ram the ram to set
	 */
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	/**
	 * @return the disk
	 */
	public Integer getDisk() {
		return disk;
	}

	/**
	 * @param disk the disk to set
	 */
	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	/**
	 * @return the ephemeral
	 */
	public Integer getEphemeral() {
		return ephemeral;
	}

	/**
	 * @param ephemeral the ephemeral to set
	 */
	public void setEphemeral(Integer ephemeral) {
		this.ephemeral = ephemeral;
	}

	/**
	 * @return the swap
	 */
	public String getSwap() {
		return swap;
	}

	/**
	 * @param swap the swap to set
	 */
	public void setSwap(String swap) {
		this.swap = swap;
	}

	/**
	 * @return the rxtxFactor
	 */
	public Float getRxtxFactor() {
		return rxtxFactor;
	}

	/**
	 * @param rxtxFactor the rxtxFactor to set
	 */
	public void setRxtxFactor(Float rxtxFactor) {
		this.rxtxFactor = rxtxFactor;
	}

	/**
	 * @return the rxtxQuota
	 */
	public Integer getRxtxQuota() {
		return rxtxQuota;
	}

	/**
	 * @param rxtxQuota the rxtxQuota to set
	 */
	public void setRxtxQuota(Integer rxtxQuota) {
		this.rxtxQuota = rxtxQuota;
	}

	/**
	 * @return the rxtxCap
	 */
	public Integer getRxtxCap() {
		return rxtxCap;
	}

	/**
	 * @param rxtxCap the rxtxCap to set
	 */
	public void setRxtxCap(Integer rxtxCap) {
		this.rxtxCap = rxtxCap;
	}

	/**
	 * @return the disabled
	 */
	public Boolean getDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the isPublic
	 */
	public Boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Flavor [id=" + id + ", name=" + name + ", vcpus=" + vcpus
				+ ", ram=" + ram + ", disk=" + disk + ", ephemeral="
				+ ephemeral + ", swap=" + swap + ", rxtxFactor=" + rxtxFactor
				+ ", disabled=" + disabled + ", rxtxQuota=" + rxtxQuota
				+ ", rxtxCap=" + rxtxCap + ", links=" + links + ", isPublic="
				+ isPublic + "]";
	}

}
