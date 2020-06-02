package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private ArtsmiaDAO dao;
	private SimpleWeightedGraph<Artist, DefaultWeightedEdge> grafo;
	private Map<Integer,Artist> idMap;
	private List<Adiacenza> adiacenze;
	private List<Artist> best;
	
	public Model() {
		dao= new ArtsmiaDAO();
		idMap=new HashMap<>();
		adiacenze= new ArrayList<>();
	}

	public List<String> getRuoli() {
		
		return dao.getRuoli();
	}

	public void creaGrafo(String ruolo) {
		
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao.getArtisti(ruolo,idMap);
		// aggiungo vertici
		Graphs.addAllVertices(this.grafo, idMap.values());
		adiacenze=dao.getAdiacenze(ruolo);
		for (Adiacenza a: adiacenze) {
			if(a.getPeso()>0) {
				Graphs.addEdge(this.grafo, idMap.get(a.getA1().getId()), idMap.get(a.getA2().getId()), a.getPeso());
			}
		}
		System.out.println(String.format("Grafo creato con %d vertici e %d archi\n", this.grafo.vertexSet().size(),this.grafo.edgeSet().size()));
	}

	public List<Adiacenza> getAdiacenze() {
		
		return this.adiacenze;
	}

	public ArtsmiaDAO getDao() {
		return dao;
	}

	public SimpleWeightedGraph<Artist, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public Map<Integer, Artist> getIdMap() {
		return idMap;
	}

	public List<Integer> cammino(String id) {
		
		if(contiene(id)==false) {
			return null;
		}
		Artist artista = new Artist(Integer.parseInt(id),null);
		this.best= new ArrayList<Artist>();
		List<Artist> parziale= new ArrayList<>();
		parziale.add(artista);
		ricorsione(parziale,-1);
		List<Integer> result= new LinkedList<>();
		for(Artist a: best) {
			result.add(a.getId());
		}
		return result;
	}
	//
	private boolean contiene(String id) {
		
		int ID=Integer.parseInt(id);
		Artist a= new Artist(ID,null);
		
		
		return this.grafo.containsVertex(a);
	}

	private void ricorsione(List<Artist> parziale,int peso) {
		
		Artist ultimo= parziale.get(parziale.size()-1);
		//ottengo i vicini
		List<Artist> vicini=Graphs.neighborListOf(this.grafo, ultimo);
		for(Artist vicino: vicini) {
			if(!parziale.contains(vicino) && peso==-1) {
				parziale.add(vicino);
				ricorsione(parziale,(int)this.grafo.getEdgeWeight(this.grafo.getEdge(ultimo, vicino)));
				parziale.remove(vicino);
			} else {
				if(!parziale.contains(vicino) && this.grafo.getEdgeWeight(this.grafo.getEdge(ultimo, vicino))==peso) {
					parziale.add(vicino);
					ricorsione(parziale,peso);
					parziale.remove(vicino);
				}
			}
			
		}
		if(parziale.size()>best.size()) {
			best= new ArrayList<>(parziale);
			System.out.println("cacca");
		}
		
	}
	
	
	
	
}
