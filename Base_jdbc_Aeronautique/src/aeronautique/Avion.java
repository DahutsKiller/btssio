package aeronautique;

public class Avion {

	private int numAv;
	private String nomAv;
	private int capacite;
	private String loc;
	
	public Avion(String nomAv, int capacite, String loc) {
		super();
		this.numAv = getNumAv();
		this.nomAv = nomAv;
		this.capacite = capacite;
		this.loc = loc;
		
	}
	
	public int getNumAv() {
		return numAv;
	}
	
	public void setNumAv(int numAv) {
		this.numAv = numAv;
	}

	
	public String getNomAv() {
		return nomAv;
	}
	
	public void setNomAv(String nomAv) {
		this.nomAv = nomAv;
	}


	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
}
