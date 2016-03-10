package com.infinities.swift.objects.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Objects implements Iterable<Object>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("objects")
	private List<Object> objects;
	
	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public String toString() {
		return "Objects [list=" + objects + "]";
	}

	@Override
	public Iterator<Object> iterator() {
		return objects.iterator();
	}

}