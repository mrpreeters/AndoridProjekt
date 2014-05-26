package com.pritta.vote4song.util;


import com.pritta.vote4song.data.Dogodek;
import com.pritta.vote4song.data.Singel;
import com.pritta.vote4song.data.DogodekList;
import com.pritta.vote4song.data.SingelList;

public class DataMock {
	public static DogodekList getDogodekListMock() {
		DogodekList a = new DogodekList();
		a.getList().add(new Dogodek("Koncert Queen","Super dogodek"));
		a.getList().add(new Dogodek("Reunion Beatles","The best bend"));
		a.getList().add(new Dogodek("Roling Stones koncert","Fajn bo"));
		return a;
		
	}
	public static SingelList getSingelListMock() {
		SingelList a = new SingelList();
		
		return a;
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
