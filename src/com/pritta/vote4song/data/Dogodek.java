package com.pritta.vote4song.data;

import java.sql.Date;

public class Dogodek {

	private int idDogodka;
	public int getIdDogodka() {
		return idDogodka;
	}
	public void setIdDogodka(int idDogodka) {
		this.idDogodka = idDogodka;
	}
	private String imeDogodka;
	private String opisDogodka;
	private Date datum;
	
	public Dogodek()
	{
		super();
		
	}
	public Dogodek(String i, String o)
	{
		super();
		this.imeDogodka = i;
		this.opisDogodka = o;

	}

	public String getImeDogodka() {
		return imeDogodka;
	}
	public void setImeDogodka(String imeDogodka) {
		this.imeDogodka = imeDogodka;
	}
	public String getOpisDogodka() {
		return opisDogodka;
	}
	public void setOpisDogodka(String opisDogodka) {
		this.opisDogodka = opisDogodka;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	

}
