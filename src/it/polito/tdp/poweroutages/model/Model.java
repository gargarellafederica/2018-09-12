package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.poweroutages.db.PowerOutagesDAO;

public class Model {
	private PowerOutagesDAO dao;
	private SimpleWeightedGraph<Nerc, DefaultWeightedEdge> grafo;
	private List<Adiacenza> vicini;
	private List <Vicini> listapeso;
	
	public Model() {
		grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}

	public void creaGrafo() {
		dao= new PowerOutagesDAO();
		Graphs.addAllVertices(this.grafo, this.dao.loadAllNercs());
		vicini=this.dao.riempivicini();
		for(Adiacenza a:vicini) 
			Graphs.addEdge(this.grafo, a.getNerc1(), a.getNerc2(), this.dao.calcolapeso(a.getNerc1(), a.getNerc2()));
	}

	public int getnVertici() {
		return this.grafo.vertexSet().size();
	}

	public int getnArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Nerc> getvertici() {
		return this.dao.nercList;
	}

	public void calcolavicini(Nerc n) {
		listapeso= new ArrayList<>();
		for (Adiacenza a: vicini) {
			if(a.getNerc1().equals(n)) {
				DefaultWeightedEdge e= this.grafo.getEdge(a.getNerc1(), a.getNerc2());
				listapeso.add(new Vicini (a.getNerc2(), (int) this.grafo.getEdgeWeight(e)));
			} else if(a.getNerc2().equals(n)) {
				DefaultWeightedEdge e= this.grafo.getEdge(a.getNerc2(), a.getNerc1());
				listapeso.add(new Vicini (a.getNerc1(), (int) this.grafo.getEdgeWeight(e)));
			}
		}
		Collections.sort(listapeso);
	}
	
	public List<Vicini> returnpesi(){
		return listapeso;
	}

}
