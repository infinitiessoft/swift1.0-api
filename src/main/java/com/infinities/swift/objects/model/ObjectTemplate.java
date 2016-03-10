package com.infinities.swift.objects.model;

import java.io.Serializable;

public class ObjectTemplate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;
	
	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}
	
	/**
	 * @param object
	 *            the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	
}
