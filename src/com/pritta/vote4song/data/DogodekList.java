package com.pritta.vote4song.data;

import java.util.ArrayList;

public class DogodekList extends Object {
	private ArrayList<Dogodek> list;

	public ArrayList<Dogodek> getList() {
		return list;
	}

	public DogodekList() {
		super();
		list = new ArrayList<Dogodek>();
	}

	@Override
	public String toString() {
		return "Dogodeklist [list=" + list + "]";
	}
	
	
}
