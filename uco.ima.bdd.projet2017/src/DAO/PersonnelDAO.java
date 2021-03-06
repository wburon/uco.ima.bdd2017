package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Singleton.SingletonConnection;
import model.Hotel;
import model.Personnel;

public class PersonnelDAO extends DAO<Personnel> {

	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Personnel obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into personnel values (?,?,?,?,?,?,?,?);");

			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getPersonne().getId_personne());
			prepare.setDouble(3, obj.getSalaire());
			prepare.setInt(4, obj.getFonction().getId_fonction());
			prepare.setInt(5, obj.getAnnee_arrivee());
			prepare.setString(6, obj.getPassword());
			prepare.setString(7, obj.getLogin());
			prepare.setInt(8, obj.getHotel().getId_hotel());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Personnel obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from personnel where id_personnel=?");

			prepare.setInt(1, obj.getId_personnel());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Personnel obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update personnel set salaire=?, id_fonction=?, annee_arrivee=?, password=?, login=? where id_personnel=?");

			prepare.setDouble(1, obj.getSalaire());
			prepare.setInt(2, obj.getFonction().getId_fonction());
			prepare.setInt(3, obj.getAnnee_arrivee());
			prepare.setString(4, obj.getPassword());
			prepare.setString(5, obj.getLogin());
			prepare.setInt(6, obj.getId_personnel());

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Personnel find(int id) {
		Personnel personnel = new Personnel();
		PersonneDAO personne = new PersonneDAO();
		FonctionDAO fonction = new FonctionDAO();
		HotelDAO hDAO = new HotelDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM personnel where id_personnel = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				personnel.setId_personnel(result.getInt("id_personnel"));
				personnel.setPersonne(personne.find(result.getInt("id_personne")));
				personnel.setHotel(hDAO.find(result.getInt("id_hotel")));
				personnel.setSalaire(result.getInt("salaire"));
				personnel.setFonction(fonction.find(result.getInt("id_fonction")));
				personnel.setAnnee_arrivee(result.getInt("annee_arrivee"));
				personnel.setPassword(result.getString("password"));
				personnel.setLogin(result.getString("login"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_personnel) FROM personnel");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}
	
	public Personnel[] findPersonnelByFonction(String fonction){
		
		try {
			FonctionDAO fonctionDao = new FonctionDAO();
			int id_fonction = fonctionDao.renvoieId(fonction);
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM personnel where id_fonction = ?");
			prepare.setInt(1, id_fonction);
			ResultSet result = prepare.executeQuery();
			
			PreparedStatement prepareCount = SC.prepareStatement("SELECT COUNT(*) FROM personnel where id_fonction=?"); 
			prepareCount.setInt(1, id_fonction);
			ResultSet resultCount = prepareCount.executeQuery();
			
			Personnel[] personnel = new Personnel[resultCount.getInt(1)];
			for(int i=0; i<personnel.length;i++){
				personnel[i] = find(result.getInt("Id_personnel"));
				result.next();
			}
			return personnel;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param login
	 * @param password
	 * @return id_user
	 */
	public int findUser(String login, String password){
		PreparedStatement prepare;
		try {
			prepare = SC.prepareStatement("SELECT * FROM personnel where login=? AND password=?",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setString(1, login);
			prepare.setString(2, password);
			ResultSet result = prepare.executeQuery();
			
			if(result.first())
				return result.getInt("id_personnel");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		System.out.println("ERREUR");
		return -1;
	}
	
	/**
	 * Renvoie la lsite du personnel d'un hotel
	 * @param hotel
	 * @return
	 */
	public ArrayList<Personnel> ListPersonnel(Hotel hotel){
		ArrayList<Personnel> listPersonnel = new ArrayList<Personnel>();
		Statement state;
		Personnel obj = new Personnel();
		int id_hotel = hotel.getId_hotel();
		try{
			state = SC.createStatement();
			state.executeQuery("SELECT * FROM personnel WHERE id_hotel="+id_hotel);
			ResultSet result = state.getResultSet();
			
			while(result.next()){
				obj = find(result.getInt("id_personnel"));
				listPersonnel.add(obj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listPersonnel;
	}
	/*
	 * M�thode d�finissant une fonction de hachage pour les mot de passe utilisateurs afin de s�curiser leurs donn�es
	 */
	public String HashPassword (String passwordToHash){
		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(passwordToHash.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generatedPassword;
	}
	

}
