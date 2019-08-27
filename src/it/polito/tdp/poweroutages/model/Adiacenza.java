package it.polito.tdp.poweroutages.model;

public class Adiacenza {
	private Nerc nerc1;
	private Nerc nerc2;
	
	public Adiacenza(Nerc nerc1, Nerc nerc2) {
		super();
		this.nerc1 = nerc1;
		this.nerc2 = nerc2;
	}
	public Nerc getNerc1() {
		return nerc1;
	}
	public void setNerc1(Nerc nerc1) {
		this.nerc1 = nerc1;
	}
	public Nerc getNerc2() {
		return nerc2;
	}
	public void setNerc2(Nerc nerc2) {
		this.nerc2 = nerc2;
	}
	@Override
	public String toString() {
		return "Adiacenza [nerc1=" + nerc1 + ", nerc2=" + nerc2 + "]";
	}
	

}
