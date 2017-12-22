package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

import aeronautique.Avion;
import aeronautique.Pilote;
import aeronautique.Vol;

/**
 * étape 2 : le patron de conception DAO, le lien vers notre table PILOTE
 * On étend la classe DAO avec un Pilote
 * @author abi
 *
 */
public class VolDAO extends DAO<Vol> {
	
	

	// 2 constantes de classe : le nom de la table, la clé primaire


	/* !!! DATES : pour les bases de données, on passera par java.sql.Timestamp 
	 * Pour le find :
	 * GregorianCalendar hArr = new GregorianCalendar();
	 * hArr.setTimeInMillis (rs.getTimestamp("harr").getTime());
	 * Pour le create :
	 * Timestamp ts = new Timestamp (vol.gethDep().getTimeInMillis());
	 * pst.setTimestamp(3,ts);
	 * 
	 * NB : les mois dans le constructeur de Gregorian Calendar vont de 0 à 11.
	 * 
	 * En utilisant SimpleDateFormat, on peut avoir un affichage avec des termes français.
	 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy zzzz G", Locale.FRENCH);
	 * 
	 */

	
	/**
	 * On donne un vol en entrée qu'on va écrire dans la base de données
	 * La requête à utiliser est un INSERT INTO
	 * On utilise encore TimeStamp
	 */
	@Override
	public boolean create(Vol obj) {

		boolean succes=true;
		try {
			// TODO Le prepared Statement prépare notre requete.
			// On peut utiliser les méthodes setInt, setTimestamp, setString...
			// Puis on utilise executeUpdate

			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy zzzz G", Locale.FRENCH);
			
			String sql = "INSERT INTO VOL SET nomVol = ?, numPil = ?, numAv = ?, villeDep = ?, villeArr = ?, hDep = ?, hArr = ?"; 
	 
			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 
			
			preparedStatement.setString(1, obj.getNomVol()); 
		
			preparedStatement.setInt(2, obj.getPilote().getNumPil()); 

			preparedStatement.setInt(3, obj.getAvion().getNumAv());

			preparedStatement.setString(4,obj.getVilleDep()); 
			
			preparedStatement.setString(5,obj.getVilleArr()); 
			
			Timestamp hDepTs = new Timestamp (obj.gethDep().getTimeInMillis());
			
			preparedStatement.setTimestamp(6,hDepTs);
			
			Timestamp hArrTs = new Timestamp (obj.gethArr().getTimeInMillis());
			
			preparedStatement.setTimestamp(7,hArrTs);

			preparedStatement.executeUpdate();
			
			// Ensuite, il faut remettre à jour l'identifiant de l'objet java,
						// généré automatiquement par la base de données
			 
			obj.setNumVol(Connexion.getMaxId("numVol", "Vol"));
			
		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;
	}

	/**
	 * On donne un vol en entrée qu'on va supprimer de la base de données
	 * La requête à utiliser est un DELETE FROM
	 * 
	 */
	@Override
	public boolean delete(Vol obj) {
		boolean succes=true;
		
		try {
			// TODO prepared Statement et executeUpdate
			
			String sql = "DELETE FROM Vol WHERE NumVol = ?";
			
			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 
			
			int id = obj.getNumVol();
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;		
	}

	/**
	 * On donne un vol en entrée qu'on va mettre à jour dans la base de données
	 * La requête à utiliser est un UPDATE SET
	 * 
	 */
	@Override
	public boolean update(Vol obj) {
		boolean succes=true;

		// TODO requete, preparedStatement, setInt, setString, setTimeStamp etc. puis executeUpdate
		
		try {
			
		String sql = "UPDATE VOL SET nomVol = ?, numPil = ?, numAv = ?, villeDep = ?, villeArr = ?, hDep = ?, hArr = ?"; 
		 
		PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 
		
		preparedStatement.setString(1, obj.getNomVol()); //1
	
		preparedStatement.setInt(2, obj.getPilote().getNumPil()); //2

		preparedStatement.setInt(3, obj.getAvion().getNumAv()); //3

		preparedStatement.setString(4,obj.getVilleDep()); //4
		
		preparedStatement.setString(5,obj.getVilleArr()); //5
		
		Timestamp hDepTs = new Timestamp (obj.gethDep().getTimeInMillis());
		
		preparedStatement.setTimestamp(6,hDepTs);//6
		
		Timestamp hArrTs = new Timestamp (obj.gethArr().getTimeInMillis());
		
		preparedStatement.setTimestamp(7,hArrTs);//7

		preparedStatement.executeUpdate();
		
			
		} catch (SQLException e) {
			succes = false;
			e.printStackTrace();
		} 
		return succes;	
	}

	/**
	 * On donne un identifiant entier en entrée qu'on cherche dans la base de données
	 * La requête à utiliser est un SELECT FROM WHERE avec la clé primaire
	 * 
	 */
	@Override
	public Vol find(int id) {
		Vol vol = null;
		try {
			// TODO manipulation d'un resultSet, création d'un objet Vol
			
			/* Pour le find :
				 * GregorianCalendar hArr = new GregorianCalendar();
				 * hArr.setTimeInMillis (rs.getTimestamp("harr").getTime());*/
			
			ResultSet rs =Connexion.executeQuery("SELECT * FROM Vol WHERE numVol="+id);
			
			GregorianCalendar hDep = new GregorianCalendar();
			hDep.setTimeInMillis (rs.getTimestamp(7).getTime());
			
			GregorianCalendar hArr = new GregorianCalendar();
			hArr.setTimeInMillis (rs.getTimestamp(8).getTime());
			
			vol = new Vol(id,rs.getString(2),rs.getInt(PiloteDAO.find(vol.getNumPil)),rs.getInt(AvionDAO.find()),rs.getString(5),rs.getString(6),hDep,hArr);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vol;
	}

test

}
