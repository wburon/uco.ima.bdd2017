package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.Fonction;
import model.Personnel;

public class FonctionDAO extends DAO<Fonction>{
	
	Connection SC = SingletonConnection.getConnection();
	
	/*
	 private int id_fonction;
	private String nom;
	private int niveau_contrainte;
	 */

	@Override
	public boolean create(Fonction obj) {
		try {
			PreparedStatement prepare = SC
					.prepareStatement("Insert into fonction values (?,?,?);");

			prepare.setInt(1, obj.getId_fonction());
			prepare.setString(2, obj.getNom());
			prepare.setInt(3, obj.getNiveau_contrainte());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Fonction obj) {
		try{
			PreparedStatement prepare =SC.prepareStatement("Delete from fonction where id_fonction=?");

			prepare.setInt(1, obj.getId_fonction());
			
			prepare.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Fonction obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("Update fonction set nom=?, niveau_contrainte=? where id_fonction=?");
			
			prepare.setString(1, obj.getNom());
			prepare.setInt(2, obj.getNiveau_contrainte());
			prepare.setInt(3, obj.getId_fonction());

			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Fonction find(int id) {
		Fonction fonction = new Fonction();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM fonction where id_fonction = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if(result.first()){
				fonction.setId_fonction(result.getInt("id_fonction"));
				fonction.setNom(result.getString("nom"));
				fonction.setNiveau_contrainte(result.getInt("niveau_contrainte"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fonction;
	}

	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
