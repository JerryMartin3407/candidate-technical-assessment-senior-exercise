package com.jmartin.demo.model;

public class Actor {

	private Long actorId;
	private String actorName;

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	@Override
	public String toString() {
		return "[ Actor Id : " + actorId + ", Actor Name : " + actorName + "]";
	}
}