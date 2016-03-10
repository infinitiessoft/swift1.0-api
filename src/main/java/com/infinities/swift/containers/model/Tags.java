package com.infinities.swift.containers.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.dasein.cloud.Tag;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tags implements Iterable<Tag>, Serializable{
	
	public static enum Action implements Serializable {
		CREATE, REMOVE;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("tags")
	private List<Tag> tags;
	
	private Action action;
	

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String toString() {
		return "Tag [list=" + tags + "]";
	}

	@Override
	public Iterator<Tag> iterator() {
		return tags.iterator();
	}

}
