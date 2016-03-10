package com.infinities.swift.accounts.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Containers implements Iterable<Container>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("containers")
	private List<Container> containers;
	
	public List<Container> getContainers() {
		return containers;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public String toString() {
		return "Containers [list=" + containers + "]";
	}

	@Override
	public Iterator<Container> iterator() {
		return containers.iterator();
	}

}