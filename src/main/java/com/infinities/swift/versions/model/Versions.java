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

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Versions implements Serializable, Iterable<Version> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "versions")
	private List<Version> list;


	/**
	 * 
	 */
	public Versions() {
		super();
	}

	/**
	 * @param list
	 */
	public Versions(List<Version> list) {
		super();
		this.list = list;
	}

	/**
	 * @return the list
	 */
	public List<Version> getList() {
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<Version> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Versions [list=" + list + "]";
	}

	@Override
	public Iterator<Version> iterator() {
		return list.iterator();
	}

}
