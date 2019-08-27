package it.polito.tdp.poweroutages.model;

public class Vicini implements Comparable<Vicini>{
	private Nerc n;
	private int peso;
	public Vicini(Nerc n, int peso) {
		super();
		this.n = n;
		this.peso = peso;
	}
	public Nerc getN() {
		return n;
	}
	public void setN(Nerc n) {
		this.n = n;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((n == null) ? 0 : n.hashCode());
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
		Vicini other = (Vicini) obj;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Vicino: " + n + ", peso=" + peso + "\n";
	}
	@Override
	public int compareTo(Vicini v) {
		return Integer.compare(v.peso, this.peso);
	}
	

}
