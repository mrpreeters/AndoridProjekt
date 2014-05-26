package com.pritta.vote4song;


import java.util.ArrayList;

import com.pritta.vote4song.data.Singel;
import com.pritta.vote4song.data.SingelList;
import com.pritta.vote4song.util.DataMock;

import android.app.Application;

public class ControlApplication extends Application {

	private SingelList singli;
	ArrayList<Singel> listSingli;
	
	public ArrayList<Singel> getListSingli() {
		return listSingli;
	}
	public void setListSingli(ArrayList<Singel> listSingli) {
		this.listSingli = listSingli;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		//all = new MelodijaList();
		
		singli = DataMock.getSingelListMock();
	}
public SingelList getSingli() {

			if (singli==null) { //ni datoteke daj privzete
				singli = DataMock.getSingelListMock();
			}
		
		return singli;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
