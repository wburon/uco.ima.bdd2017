package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Hotel;

public class HotelDAO extends DAO<Hotel> {

	Connection SC = SingletonConnection.getConnection();
	@Override
	public boolean create(Hotel obj) {
		try{
			PreparedStatement prepare = SC.prepareStatement("INSERT INTO hotel VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			prepare.setInt(1, obj.getId_hotel());
			prepare.setString(2, obj.getAdresse());
			prepare.setDouble(3, obj.getNb_etoile_ta());
			prepare.setString(4, obj.getProprietaire());
			prepare.setInt(5, obj.getStanding());
			prepare.setString(6, obj.getVille());
			prepare.setString(7, obj.getPays());
			prepare.setInt(8, obj.getCode_postal());
			prepare.setBoolean(9, obj.isWifi());
			prepare.setInt(10, obj.getNb_chambre_libre());
			
			prepare.executeUpdate();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Hotel obj) {
		PreparedStatement prepare;
		try {
			prepare = SC.prepareStatement("DELETE FROM hotel WHERE id_hotel=?");
			
			prepare.setInt(1, obj.getId_hotel());
			
			prepare.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Hotel obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("Update fonction set adresse=?, nb_etoile_ta=?, proprietaire=?, standing=?, ville=?, pays=?, code_postal=?, wifi=?, nb_chambre_libre=?, where id_hotel=?");
			
			prepare.setString(1, obj.getAdresse());
			prepare.setDouble(2, obj.getNb_etoile_ta());
			prepare.setString(3, obj.getProprietaire());
			prepare.setInt(4, obj.getStanding());
			prepare.setString(5, obj.getVille());
			prepare.setString(6, obj.getPays());
			prepare.setInt(7, obj.getCode_postal());
			prepare.setBoolean(8, obj.isWifi());
			prepare.setInt(9, obj.getNb_chambre_libre());
			prepare.setInt(10, obj.getId_hotel());

			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Hotel find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_hotel) FROM hotel");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}

}
