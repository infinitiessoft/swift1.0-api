package com.infinities.swift.containers.model;

import java.io.Serializable;

public class ContainerTemplate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container container;
	
	/**
	 * @return the container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(Container container) {
		this.container = container;
	}

}
