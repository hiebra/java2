package com.softalks.github;

public class Repository {
	
	public final String name;
	public final String owner;

	public Repository(String name) {
		int index = name.indexOf('/');
		if (index == -1) {
			throw new IllegalArgumentException("name");
		}
		owner = name.substring(0, index);
		this.name = name.substring(index + 1);
	}
	
	public Repository(String owner, String name) {
		this.owner = owner;
		this.name = name;
	}

	@Override
	public String toString() {
		return owner + "/" + name;
	}
	
}