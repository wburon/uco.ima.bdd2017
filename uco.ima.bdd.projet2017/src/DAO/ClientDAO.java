package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Singleton.SingletonConnection;
import model.Chambre;
import model.Client;

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

			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getPersonne().getId_personne());
			prepare.setInt(3, obj.getNb_resa_en_cours());
			// A la création un client gagne autant de point de fidelité qu'il fait de resa
			prepare.setInt(4, obj.getNb_resa_en_cours());

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
		PersonneDAO personne = new PersonneDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM client where id_client = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_client) FROM client");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}
	
	public ArrayList<Client> ListClient() {
		ArrayList<Client> listClient = new ArrayList<Client>();
		
		Client obj = new Client();
		try{
			miseAJour();
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM client", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()){
				obj = find(result.getInt("id_client"));
				listClient.add(obj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listClient;
	}
	
	private void miseAJour() {
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM client", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()){
				updateClient(result.getInt("id_client"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Actualise le nombre de resa en cours et la fidelité d'un client
	 * @param id_client
	 * @return
	 */
	public boolean updateClient(int id_client){
		
		try {
			//nb reservation en cours
			PreparedStatement prepareNb = SC.prepareStatement("Select * FROM reservation where date_fin>? AND id_client=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepareNb.setDate(1, new java.sql.Date(new java.util.Date().getTime() ));
			prepareNb.setInt(2, id_client);
			ResultSet resultNb = prepareNb.executeQuery();
			int nbResaEnCours = countResult(resultNb);
			
			//nb reservation total = fidelite
			PreparedStatement prepareFid = SC.prepareStatement("Select * from reservation where id_client=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepareFid.setInt(1, id_client);
			ResultSet resultFid = prepareFid.executeQuery();
			int fidelite = countResult(resultFid);
			
			Client client = new Client();
			client.setId_client(id_client);
			client.setNb_resa_en_cours(nbResaEnCours);
			client.setFidelite(fidelite);
			this.update(client);
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public int countResult(ResultSet result){
		int count = 0;
		try {
			if(result.first()){
				do{
					count++;
				}while(result.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

}
