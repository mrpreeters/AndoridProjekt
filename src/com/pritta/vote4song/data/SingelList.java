package com.pritta.vote4song.data;

import java.util.ArrayList;

public class SingelList extends Object {
	private ArrayList<Singel> list;

	public ArrayList<Singel> getList() {
		return list;
	}

	public SingelList() {
		super();
		list = new ArrayList<Singel>();
	}

	@Override
	public String toString() {
		return "SingelList [list=" + list + "]";
	}
	
	
}
