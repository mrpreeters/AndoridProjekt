package com.pritta.vote4song.data;

import java.util.ArrayList;

public class Glasbenik {

	private String nazivIzvajalca;
	private String zanr;
	private ArrayList<Clan> clani;
	
	public String getNazivIzvajalca() {
		return nazivIzvajalca;
	}
	public void setNazivIzvajalca(String nazivIzvajalca) {
		this.nazivIzvajalca = nazivIzvajalca;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public ArrayList<Clan> getClani() {
		return clani;
	}
	public void setClani(ArrayList<Clan> clani) {
		this.clani = clani;
	}
	
}
