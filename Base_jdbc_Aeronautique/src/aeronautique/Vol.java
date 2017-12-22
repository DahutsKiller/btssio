package aeronautique;

import java.util.GregorianCalendar;

/**
 * étape 0 : les classes métier : le vol
 * on regarde les types de la table VOL
 * @author abi
 *
 */

public class Vol {

	private int numVol;
	private String nomVol;
	private Pilote pilote;
	private Avion avion;
	private String villeDep;
	private String villeArr;
	private GregorianCalendar hDep;
	private GregorianCalendar hArr;
	
	// Constructeur sur les champs.
	
	public Vol(int numVol,String nomVol, Pilote numPil, Avion numAv, String villeDep, String villeArr, GregorianCalendar hDep,
			GregorianCalendar hArr) {
		super();
		this.numVol = numVol;
		this.nomVol = nomVol;
		this.pilote = numPil;
		this.avion = numAv;
		this.villeDep = villeDep;
		this.villeArr = villeArr;
		this.hDep = hDep;
		this.hArr = hArr;
	}
		
	
	
	
	
	public int getNumVol() {
		return numVol;
	}





	public void setNumVol(int numVol) {
		this.numVol = numVol;
	}


	public String getNomVol() {
		return nomVol;
	}





	public void setNomVol(String nomVol) {
		this.nomVol = nomVol;
	}


	



	public Pilote getPilote() {
		return pilote;
	}





	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}





	public Avion getAvion() {
		return avion;
	}





	public void setAvion(Avion avion) {
		this.avion = avion;
	}





	public String getVilleDep() {
		return villeDep;
	}





	public void setVilleDep(String villeDep) {
		this.villeDep = villeDep;
	}





	public String getVilleArr() {
		return villeArr;
	}





	public void setVilleArr(String villeArr) {
		this.villeArr = villeArr;
	}





	public GregorianCalendar gethDep() {
		return hDep;
	}





	public void sethDep(GregorianCalendar hDep) {
		this.hDep = hDep;
	}





	public GregorianCalendar gethArr() {
		return hArr;
	}





	public void sethArr(GregorianCalendar hArr) {
		this.hArr = hArr;
	}





	/**
	 * éventuellement utiliser java.sql.Timestamp et getTimeInMillis pour afficher les dates 
	 */
	@Override
	public String toString() {
		// TODO TimeStamp de java.sql se rapproche du dateTime de sql Server
		return null;
	}
	
}
