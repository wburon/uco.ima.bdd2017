package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			
			prepare.setInt(1, obj.getNb_resa_en_cours());
			prepare.setInt(2, obj.getFidelite());
			prepare.setInt(3, obj.getId_client());

			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Client find(int id) {
		Client client = new Client();
		personneDAO personne = new personneDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM client where id_client = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if(result.first()){
				client.setId_client(result.getInt("id_client"));
				client.setPersonne(personne.find(result.getInt("id_personne")));
				client.setNb_resa_en_cours(result.getInt("nb_resa_en_cours"));
				client.setFidelite(result.getInt("fidelite"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	

}
