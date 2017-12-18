package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Singleton.SingletonConnection;
import model.Hotel;
import model.Personnel;

public class HotelDAO extends DAO<Hotel> {

	Connection SC = SingletonConnection.getConnection();
	@Override
	public boolean create(Hotel obj) {
		try{
			PreparedStatement prepare = SC.prepareStatement("INSERT INTO hotel VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			
			prepare.setInt(1, maxId());
			prepare.setString(2, obj.getAdresse());
			prepare.setDouble(3, obj.getNb_etoile_ta());
			prepare.setString(4, obj.getProprietaire());
			prepare.setInt(5, obj.getStanding());
			prepare.setString(6, obj.getVille());
			prepare.setString(7, obj.getPays());
			prepare.setInt(8, obj.getCode_postal());
			prepare.setBoolean(9, obj.isWifi());
			prepare.setInt(10, obj.getNb_chambre_libre());
			prepare.setInt(11, obj.getNb_chambre_total());
			prepare.setString(12, obj.getName());
			
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
			PreparedStatement prepare=SC.prepareStatement("UPDATE hotel SET adresse=?, nb_etoile_ta=?, proprietaire=?, standing=?, ville=?, pays=?, code_postal=?, wifi=?, nb_chambre_libre=?, nb_chambre_total=?, name=? WHERE id_hotel=?");
			
			prepare.setString(1, obj.getAdresse());
			prepare.setDouble(2, obj.getNb_etoile_ta());
			prepare.setString(3, obj.getProprietaire());
			prepare.setInt(4, obj.getStanding());
			prepare.setString(5, obj.getVille());
			prepare.setString(6, obj.getPays());
			prepare.setInt(7, obj.getCode_postal());
			prepare.setBoolean(8, obj.isWifi());
			prepare.setInt(9, obj.getNb_chambre_libre());
			prepare.setInt(10, obj.getNb_chambre_total());
			prepare.setString(11, obj.getName());
			prepare.setInt(12, obj.getId_hotel());

			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Hotel find(int id) {
		Hotel hotel = new Hotel();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM hotel WHERE id_hotel = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if (result.first()){
				hotel.setId_hotel(result.getInt("id_hotel"));
				hotel.setAdresse(result.getString("adresse"));
				hotel.setNb_etoile_ta(result.getDouble("nb_etoile_ta"));
				hotel.setProprietaire(result.getString("proprietaire"));
				hotel.setStanding(result.getInt("standing"));
				hotel.setVille(result.getString("ville"));
				hotel.setPays(result.getString("pays"));
				hotel.setCode_postal(result.getInt("code_postal"));
				hotel.setWifi(result.getBoolean("wifi"));
				hotel.setNb_chambre_libre(result.getInt("nb_chambre_libre"));
				hotel.setNb_chambre_total(result.getInt("nb_chambre_total"));
				hotel.setName(result.getString("name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return hotel;
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

	/**
	 * etoile
	 * @param note (list)
	 * @return la moyenne des notes
	 */
	public int etoile(ArrayList<Integer> note){
		int etoile =0;
		for (int i=0 ; i<note.size() ; i++) {
			etoile += note.get(i);
		}
		etoile = etoile/note.size();
		return etoile;
	}
	
	public ArrayList<Hotel> ListHotel(){
		ArrayList<Hotel> listHotel = new ArrayList<Hotel>();
		Statement state;
		Hotel obj = new Hotel();
		try{
			state = SC.createStatement();
			state.executeQuery("SELECT * FROM hotel");
			ResultSet result = state.getResultSet();
			
			while(result.next()){
				obj = find(result.getInt("id_hotel"));
				listHotel.add(obj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listHotel;
	}
	
}
