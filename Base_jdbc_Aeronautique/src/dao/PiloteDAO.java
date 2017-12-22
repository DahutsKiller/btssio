package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import aeronautique.Pilote;


public class PiloteDAO extends DAO<Pilote> {	

	// 2 constantes de classe : le nom de la table, la clé primaire
	
	final static String TABLE = "Pilote";
	final static int CLE_PRIMAIRE = numPil;
	
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
	public boolean create(Pilote obj) {

		boolean succes=true;
		try {
			// TODO Le prepared Statement prépare notre requete.
			// On peut utiliser les méthodes setInt, setTimestamp, setString...
			// Puis on utilise executeUpdate*

			String sql = "INSERT INTO PILOTE (nomPil, adr, sal) VALUES (?, ?, ?)"; 

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			preparedStatement.setString(1, obj.getNomPil()); 

			preparedStatement.setString(2, obj.getAdr()); 

			preparedStatement.setInt(3, obj.getSal());

			preparedStatement.executeUpdate();

			// Ensuite, il faut remettre à jour l'identifiant de l'objet java,
			// généré automatiquement par la base de données

			obj.setNumPil(Connexion.getMaxId("numPil", "Pilote"));

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
	public boolean delete(Pilote obj) {
		boolean succes=true;
		try {
			// TODO prepared Statement et executeUpdate

			String sql = "DELETE FROM Pilote WHERE NumPil = ?";

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			int id = obj.getNumPil();

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
	public boolean update(Pilote obj) {
		boolean succes=true;
		
		try {
			
			String sql = "UPDATE "+TABLE+" SET nomPil = ?, Adr = ?, Sal = ?";
					

			PreparedStatement preparedStatement = Connexion.getInstance().prepareStatement(sql); 

			preparedStatement.setString(1, obj.getNomPil()); 

			preparedStatement.setString(2, obj.getAdr()); 

			preparedStatement.setInt(3, obj.getSal());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			succes=false;
			e.printStackTrace();
		}
		return succes;

	}

	@Override
	public Pilote find(int id) {
		Pilote pilote = null;
		try {
			// TODO manipulation d'un resultSet, création d'un objet Vol
			
			ResultSet rs =Connexion.executeQuery("SELECT * FROM Pilote WHERE numPil="+id);
			
			pilote = new Pilote(rs.getString(2),rs.getString(3),rs.getInt(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pilote;
	}
}
