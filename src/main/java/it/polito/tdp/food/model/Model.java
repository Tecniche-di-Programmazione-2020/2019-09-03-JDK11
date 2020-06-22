package it.polito.tdp.food.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;


public class Model {
	FoodDao dao;
	List<String> porzioniNome;
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	private List<Arco> archi;
public Model() {
	dao=new FoodDao();
	
}	

public void creaGrafo(int c) {
	porzioniNome=dao.listPortionsByCalories(c);
	grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	Graphs.addAllVertices(grafo,porzioniNome);
	
	archi=dao.listArchi();
	for(Arco arco:archi) {
		if(grafo.vertexSet().contains(arco.getVertice1())&&grafo.vertexSet().contains(arco.getVertice2())) {
			Graphs.addEdge(this.grafo, arco.getVertice1(), arco.getVertice2(), arco.getPeso());
	}
}
	System.out.println("Numero di vertici: "+grafo.vertexSet().size());
	System.out.println("Numero di archi: "+grafo.edgeSet().size());
}
public int numberVertex() {
	return grafo.vertexSet().size();
}
public int numberEdges() {
	return grafo.edgeSet().size();
}
public List<String> getPorzioni(){
	return porzioniNome;
}

public List<String> correlazione(String porzione){
	
	List<String> vertici= new LinkedList<>();
	for(String s:Graphs.neighborListOf(grafo, porzione )) {
		Integer peso= (int)grafo.getEdgeWeight(this.grafo.getEdge(porzione, s));
		String result= s+" "+peso;
		vertici.add(result);
	}
	return vertici;
		
	}
	
	
}
	

