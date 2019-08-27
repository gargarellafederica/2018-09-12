package it.polito.tdp.poweroutages.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.poweroutages.model.Adiacenza;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutagesDAO {
	public List<Nerc> nercList = new ArrayList<>();

	
	public List<Nerc> loadAllNercs() {

		String sql = "SELECT id, value FROM nerc";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Adiacenza> riempivicini() {

		String sql = "SELECT nerc_one, nerc_two " + 
				"FROM nercrelations ";
		List<Adiacenza> listavicini = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Adiacenza nuovo= new Adiacenza(nercList.get(res.getInt("nerc_one")), nercList.get(res.getInt("nerc_two")));
				listavicini.add(nuovo);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listavicini;
	}
	
	public int calcolapeso(Nerc v1, Nerc v2) {

		String sql = "SELECT COUNT( distinct YEAR(p.date_event_began), MONTH (p.date_event_began) ) AS cnt " + 
				"FROM poweroutages p " + 
				"WHERE p.nerc_id=? OR p.nerc_id=? ";
		int peso=0;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, v1.getId());
			st.setInt(2, v2.getId());
			
			ResultSet res = st.executeQuery();
			
			if (res.next()) {
				peso=res.getInt("cnt");
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return peso;
	}
}
