package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Parking;


public class ParkingDAO extends DAO<Parking>{
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Parking obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into parking values (?,?,?,?,?,?);");

			prepare.setInt(1, obj.getId_parking());
			prepare.setInt(2, obj.getHotel().getId_hotel());
			prepare.setInt(3, obj.getNb_place());
			prepare.setBoolean(4, obj.isGratuit());
			prepare.setDouble(5, obj.getDistance_hotel());
			prepare.setBoolean(6,  obj.isVeilleur_de_nuit());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Parking obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from parking where id_parking=?");

			prepare.setInt(1, obj.getId_parking());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Parking obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update parking set nb_place=?, gratuit=?, veilleur_de_nuit where id_parking=?");

			prepare.setInt(1, obj.getNb_place());
			prepare.setBoolean(2, obj.isGratuit());
			prepare.setBoolean(3, obj.isVeilleur_de_nuit());
			prepare.setInt(4, obj.getId_parking());

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Parking find(int id) {
		Parking parking = new Parking();
		HotelDAO hotelDao = new HotelDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM parking where id_parking = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				parking.setId_parking(result.getInt("id_parking"));
				parking.setHotel(hotelDao.find(result.getInt("id_hotel")));
				parking.setGratuit(result.getBoolean("gratuit"));
				parking.setNb_place(result.getInt("nb_place"));
				parking.setDistance_hotel(result.getDouble("distance_hotel"));
				parking.setVeilleur_de_nuit(result.getBoolean("veilleur_de_nuit"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parking;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_parking) FROM parking");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}



}
