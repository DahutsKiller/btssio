package aeronautique;

public class Pilote {

	private int numPil;
	private String nomPil;
	private String adr;
	private int sal;
	
	
	public Pilote(String nomPil, String adr, int sal) {
		super();
		this.numPil = -1;
		this.nomPil = nomPil;
		this.adr = adr;
		this.sal = sal;
	}


	public void setNumPil(int numPil) {
		this.numPil = numPil;
	}


	public int getNumPil() {
		return numPil;
	}
	
	public String getNomPil() {
		return nomPil;
	}


	public void setNomPil(String nomPil) {
		this.nomPil = nomPil;
	}


	public String getAdr() {
		return adr;
	}


	public void setAdr(String adr) {
		this.adr = adr;
	}


	public int getSal() {
		return sal;
	}


	public void setSal(int sal) {
		this.sal = sal;
	}


	@Override
	public String toString() {
		return "Pilote [numPil=" + numPil + ", nomPil=" + nomPil + ", adr=" + adr + ", sal=" + sal + "]";
	}
	

}
