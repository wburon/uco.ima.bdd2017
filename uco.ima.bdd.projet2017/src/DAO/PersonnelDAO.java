package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.Client;
import model.Personnel;

public class PersonnelDAO extends DAO<Personnel>{
	
	Connection SC = SingletonConnection.getConnection();
	
	/*
	 private int id_personnel;
	private Personne personne;
	private int salaire;
	private Fonction fonction;
	private int annee_arrivee;
	 */

	@Override
	public boolean create(Personnel obj) {
		try {

			PreparedStatement prepare = SC
					.prepareStatement("Insert into personnel values (?,?,?,?,?);");

			prepare.setInt(1, obj.getId_personnel());
			prepare.setInt(2, obj.getPersonne().getId_personne());
			prepare.setInt(3, obj.getSalaire());
			prepare.setInt(4, obj.getFonction().getId_fonction());
			prepare.setInt(5, obj.getAnnee_arrivee());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Personnel obj) {
		try{
			PreparedStatement prepare =SC.prepareStatement("Delete from personnel where id_personnel=?");

			prepare.setInt(1, obj.getId_personnel());
			
			prepare.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Personnel obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("Update personnel set salaire=?, id_fonction=?, annee_arrivee=?, password=? where id_personnel=?");
			
			prepare.setInt(1, obj.getSalaire());
			prepare.setInt(2, obj.getFonction().getId_fonction());
			prepare.setInt(3, obj.getAnnee_arrivee());
			prepare.setString(4, obj.getPassword());
			prepare.setInt(5, obj.getId_personnel());

			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Personnel find(int id) {
		Personnel personnel = new Personnel();
		personneDAO personne = new personneDAO();
		FonctionDAO fonction = new FonctionDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM personnel where id_personnel = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if(result.first()){
				personnel.setId_personnel(result.getInt("id_personnel"));
				personnel.setPersonne(personne.find(result.getInt("id_personne")));
				personnel.setSalaire(result.getInt("salaire"));
				personnel.setFonction(fonction.find(result.getInt("fonction")));
				personnel.setAnnee_arrivee(result.getInt("annee_arrivee"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}
	
	

}
