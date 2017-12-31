package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Reservation;

public class ReservationDAO extends DAO<Reservation>{
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Reservation obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into reservation values (?,?,?,?,?,?);");

			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getChambre().getId_chambre());
			prepare.setInt(3, obj.getHotel().getId_hotel());
			prepare.setInt(4, obj.getClient().getId_client());
			prepare.setDate(5, new java.sql.Date(obj.getDate_debut().getTime()));
			prepare.setDate(6, new java.sql.Date(obj.getDate_fin().getTime()));

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Reservation obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from reservation where id_reservation=?");

			prepare.setInt(1, obj.getId_resa());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Reservation obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update reservation set id_chambre=?, id_hotel=?, id_client=?, date_debut=?, date_fin=? where id_reservation=?");

			prepare.setInt(1, obj.getChambre().getId_chambre());
			prepare.setInt(2, obj.getHotel().getId_hotel());
			prepare.setInt(3, obj.getClient().getId_client());
			prepare.setDate(4, new java.sql.Date(obj.getDate_debut().getTime()));
			prepare.setDate(5, new java.sql.Date(obj.getDate_fin().getTime()));

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Reservation find(int id) {
		Reservation resa = new Reservation();
		ChambreDAO chambreDao = new ChambreDAO();
		HotelDAO hotelDao = new HotelDAO();
		ClientDAO clientDao = new ClientDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM reservation where id_reservation = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				resa.setId_resa(result.getInt("id_reservation"));
				resa.setChambre(chambreDao.find(result.getInt("id_chambre")));
				resa.setHotel(hotelDao.find(result.getInt("id_hotel")));
				resa.setClient(clientDao.find(result.getInt("id_client")));
				resa.setDate_debut(result.getDate("date_debut"));
				resa.setDate_fin(result.getDate("date_fin"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resa;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_reservation) FROM reservation");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}
	
	/**
	 * Renvoie la lsite de reservation d'un client
	 * @param id_client
	 * @return
	 */
	public Reservation[] findResaClient(int id_client){
		
		try {
			
			PreparedStatement prepare;
			prepare = SC.prepareStatement("SELECT * FROM reservation where id_client=?");
			prepare.setInt(1, id_client);
			ResultSet result = prepare.executeQuery();
			
			PreparedStatement prepareCount = SC.prepareStatement("SELECT COUNT(*) FROM reservation where id_client=?"); 
			prepareCount.setInt(1, id_client);
			ResultSet resultCount = prepareCount.executeQuery();
			
			Reservation[] reservation = new Reservation[resultCount.getInt(1)];
			for(int i=0; i<reservation.length;i++){
				reservation[i] = find(result.getInt("Id_reservation"));
				result.next();
			}
			return reservation;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
