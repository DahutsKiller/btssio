package aeronautique;

import javax.swing.plaf.synth.SynthSeparatorUI;

import dao.Connexion;
import dao.PiloteDAO;


/**
 * - Il faut commencer par faire le métier, puis regarder la classe Connexion,
 * puis le Design Pattern DAO sur la table VOL
 * - Ensuite on étend aux autres tables AVION et PILOTE
 * - faire quelques vérifications de base sur la table vol :
 * lors du create, est-ce que les clés étrangères sont dans la table.
 * Il faut lever une exception précise dans le cas contraire.
 * - Essayer des requêtes plus complexes et les proposer dès le menu.
 * - Algorithmique : soigner l'affichage des réponses pour avoir un titre
 * aux colonnes et qu'elles soient de largeur fixe.
 * 
 * Si vous utilisez le type Money sous SQL Server Express, il faut utiliser
 * DECIMAL	avec JDBC et java.math.BigDecimal pour java.
 * 
 * @author abi
 *
 */
public class Principale {

	public static void main(String[] args) {

		/*new Controleur();		
		Connexion.fermer();*/
		//Connexion.getInstance();
		
		//initialisation();
		//System.out.println("initialisé");
		//testSelect();
		//testGetMaxId();
		testPiloteDAO();
	}
	
	public static void testGetMaxId() {
		Connexion.getMaxId("numVol","VOL");
		Connexion.fermer();
	}

	public static void testSelect() {
		Connexion.afficheSelectEtoile("VOL","numPil=1");
		Connexion.fermer();
	}
	
	public static void testPiloteDAO() {
		Pilote pilote = new Pilote("Thierry","Vannes",1200);
		//pilote.setNumPil(8);
		//System.out.println("avant create"+pilote);
		PiloteDAO pDao = new PiloteDAO();
		//pDao.create(pilote);
		//System.out.println("après create"+pilote);
		//pDao.delete(pilote);
		//System.out.println("deleted");
		pDao.update(pilote);
		Connexion.fermer();
	}
	
	public static void initialisation() {
	
	Connexion.executeUpdate("DROP TABLE VOL");
	Connexion.executeUpdate("DROP TABLE AVION");
	Connexion.executeUpdate("DROP TABLE PILOTE");
	
	Connexion.executeUpdate("CREATE TABLE AVION ("
			+ "   numAv INT IDENTITY PRIMARY KEY not null,"
			+ "   nomAv VARCHAR(100),"
			+ "   capacite INT,"  
			+ "   loc VARCHAR(255));");
	
	Connexion.executeUpdate("INSERT INTO AVION(nomAv,capacite,loc)"  
			+ " VALUES" 
			+ " ('BigDaddy','400','Nice'),"  
			+ " ('LittleDaddy','200','Nice'),"  
			+ " ('Airbus','400','Paris'),"  
			+ " ('Boeing','200','Paris');");
	
	Connexion.executeUpdate("CREATE TABLE PILOTE ("
			+ "	    numPil INT IDENTITY PRIMARY KEY not null,"
			+ "	    nomPil VARCHAR(100),"
			+ "	    adr VARCHAR(255),"
			+ "	    sal INT);");
	
	Connexion.executeUpdate("INSERT INTO PILOTE(nomPil,adr,sal)"
			+ "  VALUES"
			+ "	 ('Robert','Paris','20000'),"
			+ "	 ('Gérard','Nice','10000'),"
			+ "	 ('Dupont','Paris','19000'),"
			+ "	 ('Durand','Nice','10000'),"
			+ "	 ('Bébert','Paris','20000'),"
			+ "	 ('Dupont','Marseille','10000'),"
			+ "	 ('Dupont','Paris','14000');");
	
	Connexion.executeUpdate("CREATE TABLE VOL"
			+ "(numVol INT IDENTITY PRIMARY KEY not null,"
			+ " numpil INT,"
			+ "	numAv INT," 
			+ "	FOREIGN KEY (numPil) REFERENCES PILOTE (numPil)," 
			+ "	FOREIGN KEY (numAv) REFERENCES AVION (numAv),"  
			+ " villeDep VARCHAR(100),"  
			+ " villeArr VARCHAR(100)," 
			+ "	hDep DATETIME," 
			+ "	hArr DATETIME);"); 
			
	Connexion.executeUpdate("INSERT INTO VOL(numPil,numAv,villeDep,villeArr,hDep,hArr)"  
			+ " VALUES"  
			+ " ('1','1','Nice','Paris','27/11/2017 19:00:00','27/11/2017 20:00:00'),"  
			+ " ('2','2','Paris','Marseille','27/11/2017 19:00:00','27/11/2017 20:00:00')," 
			+ " ('3','3','Marseille','Nice','27/11/2017 20:00:00','27/11/2017 21:00:00')," 
			+ " ('4','4','Nice','Paris','27/11/2017 10:00:00','27/11/2017 11:00:00')," 
			+ " ('1','2','Vannes','Nice','27/11/2017 09:00:00','27/11/2017 10:00:00')," 
			+ " ('2','3','Nice','Vannes','28/11/2017 09:00:00','27/11/2017 10:00:00'),"  
			+ " ('1','3','Nice','Marseille','28/11/2017 10:00:00','27/11/2017 11:00:00'),"  
			+ " ('1','2','Paris','Marseille','27/11/2017 16:00:00','27/11/2017 17:00:00'),"  
			+ " ('7','4','Paris','Vannes','27/11/2017 11:00:00','27/11/2017 13:00:00');");
	
	Connexion.fermer();
	}
}
