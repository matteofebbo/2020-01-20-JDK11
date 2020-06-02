package it.polito.tdp.artsmia.model;

public class Artist {
	
	private int id;
	private String nome;
	
	
	public Artist(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}


	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
