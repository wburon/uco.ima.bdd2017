package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Client;
import model.Personnel;

public class PersonnelDAO extends DAO<Personnel> {

	Connection SC = SingletonConnection.getConnection();

	/*
	 * private int id_personnel; private Personne personne; private int salaire;
	 * private Fonction fonction; private int annee_arrivee;
	 */

	@Override
	public boolean create(Personnel obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into personnel values (?,?,?,?,?,?,?);");

			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getPersonne().getId_personne());
			prepare.setDouble(3, obj.getSalaire());
			prepare.setInt(4, obj.getFonction().getId_fonction());
			prepare.setInt(5, obj.getAnnee_arrivee());
			prepare.setString(6, obj.getPassword());
			prepare.setString(7, obj.getLogin());

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
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM personnel where id_personnel = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				personnel.setId_personnel(result.getInt("id_personnel"));
				personnel.setPersonne(personne.find(result.getInt("id_personne")));
				personnel.setSalaire(result.getInt("salaire"));
				personnel.setFonction(fonction.find(result.getInt("fonction")));
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

}
