package it.polito.tdp.poweroutages.db;

import it.polito.tdp.poweroutages.model.Nerc;

public class TestDAO {

	public static void main(String[] args) {

		PowerOutagesDAO dao = new PowerOutagesDAO();

		Nerc v1= new Nerc(0, "PJM");
		Nerc v2= new Nerc(1, "ERCOT");
		
		System.out.println(dao.loadAllNercs());
		System.out.println(dao.riempivicini());
		System.out.println(dao.calcolapeso(v1, v2));

	}

}
