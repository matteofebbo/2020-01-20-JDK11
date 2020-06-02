package it.polito.tdp.artsmia.model;

public class Adiacenza  implements Comparable<Adiacenza>{
	
	private Artist a1;
	private Artist a2;
	private Integer peso;
	
	public Adiacenza(Artist a1, Artist a2, int peso) {
		this.a1=a1;
		this.a2=a2;
		this.peso=peso;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Artist getA1() {
		return a1;
	}

	public Artist getA2() {
		return a2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
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
		Adiacenza other = (Adiacenza) obj;
		if (a1 == null) {
			if (other.a1 != null)
				return false;
		} else if (!a1.equals(other.a1))
			return false;
		if (a2 == null) {
			if (other.a2 != null)
				return false;
		} else if (!a2.equals(other.a2))
			return false;
		return true;
	}

	@Override
	public int compareTo(Adiacenza o) {
		// TODO Auto-generated method stub
		return -this.peso.compareTo(o.getPeso());
	}
	
	
}
