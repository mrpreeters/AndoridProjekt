package com.pritta.vote4song.data;

public class Singel {
	private int id_singel;
	private String naslov;
	private int trajanje;
	private String izvajalec;
	private double ocena;
	
	public Singel(String id,String n, int t, String i, String o)
	{
		super();
		this.setId_singel(Integer.parseInt(id));
		this.naslov = n;
		this.trajanje = t;
		this.izvajalec = i;
		this.ocena = Double.parseDouble(o);
	}
	public String getIzvajalec() {
		return izvajalec;
	}
	public void setIzvajalec(String izvajalec) {
		this.izvajalec = izvajalec;
	}
	public double getOcena() {
		return ocena;
	}
	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public int getTrajanje() {
		return trajanje;
	}
	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}
	public String toString()
	{
		
		return getIzvajalec() + " - " + getNaslov();
	}
	public int getId_singel() {
		return id_singel;
	}
	public void setId_singel(int id_singel) {
		this.id_singel = id_singel;
	}
	
}
