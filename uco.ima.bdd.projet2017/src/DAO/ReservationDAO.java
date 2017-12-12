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

			prepare.setInt(1, obj.getId_resa());
			prepare.setInt(2, obj.getChambre().getId_chambre());
			prepare.setInt(3, obj.getHotel().getId_hotel());
			prepare.setInt(4, obj.getClient().getId_client());
			prepare.setDate(5, obj.getDate_debut());
			prepare.setDate(6, obj.getDate_fin());

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
			prepare.setDate(4, obj.getDate_debut());
			prepare.setDate(5, obj.getDate_fin());

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

}