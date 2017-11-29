package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.Client;
import model.Personne;

public class ClientDAO extends DAO<Client>{
	
	Connection SC = SingletonConnection.getConnection();
	
	/*
	private int id_client;
	private Personne personne;
	private int nb_resa_en_cours;
	private int fidelite;
	 */

	@Override
	public boolean create(Client obj) {
		try {

			PreparedStatement prepare = SC
					.prepareStatement("Insert into client values (?,?,?,?);");

			prepare.setInt(1, obj.getId_client());
			prepare.setInt(2, obj.getPersonne().getId_personne());
			prepare.setInt(3, obj.getNb_resa_en_cours());
			prepare.setInt(4, obj.getFidelite());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Client obj) {
		try{
			PreparedStatement prepare =SC.prepareStatement("Delete from client where id_client=?");

			prepare.setInt(1, obj.getId_client());
			
			prepare.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Client obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("Update client set nb_resa_en_cours=?, fidelite=? where id_client=?");
			
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());
			prepare.setString(3, obj.getVille());
			prepare.setInt(4, obj.getCode_postal());
			prepare.setString(5, obj.getAdresse());
			prepare.setDate(6, obj.getDate_de_naissance());
			
			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Client find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
