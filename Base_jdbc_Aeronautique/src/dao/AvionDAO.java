package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aeronautique.Avion;



public class AvionDAO extends DAO<Avion> {	

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
	public boolean create(Avion obj) {

		boolean succes=true;
		try {
			// TODO Le prepared Statement prépare notre requete.
			// On peut utiliser les méthodes setInt, setTimestamp, setString...
			// Puis on utilise executeUpdate*

			String sql = "INSERT INTO PILOTE (nomAv, capacite, loc) VALUES (?, ?, ?)"; 

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			preparedStatement.setString(1, obj.getNomAv()); 

			preparedStatement.setInt(2, obj.getCapacite()); 

			preparedStatement.setString(3, obj.getLoc());

			preparedStatement.executeUpdate();

			// Ensuite, il faut remettre à jour l'identifiant de l'objet java,
			// généré automatiquement par la base de données

			obj.setNumAv(Connexion.getMaxId("numAv", "Avion"));

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
	public boolean delete(Avion obj) {
		boolean succes=true;
		try {
			// TODO prepared Statement et executeUpdate

			String sql = "DELETE FROM Pilote WHERE NumPil = ?";

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			int id = obj.getNumAv();

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
	public boolean update(Avion obj) {
		boolean succes=true;
		
		// TODO requete, preparedStatement, setInt, setString, setTimeStamp etc. puis executeUpdate
		
		try {
			
			String sql = "UPDATE PILOTE (nomAv, capacite, loc) VALUES (?, ?, ?)";
					

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			preparedStatement.setString(1, obj.getNomAv()); 

			preparedStatement.setInt(2, obj.getCapacite()); 

			preparedStatement.setString(3, obj.getLoc());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;

	}

	@Override
	public Avion find(int id) {
		Avion pilote = null;
		try {
			// TODO manipulation d'un resultSet, création d'un objet Vol
			
			ResultSet rs =Connexion.executeQuery("SELECT * FROM Pilote WHERE numPil="+id);
			
			pilote = new Avion(rs.getString(2),rs.getInt(3),rs.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pilote;
	}
}
