package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.Chambre;

public class ChambreDAO extends DAO<Chambre>{
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Chambre obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("INSERT INTO chambre VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			prepare.setInt(1, obj.getId_chambre());
			prepare.setInt(2, obj.getHotel().getId_hotel());
			prepare.setInt(3, obj.getNumero_chambre());
			prepare.setBoolean(4, obj.isTele());
			prepare.setBoolean(5, obj.isHandicap());
			prepare.setDouble(6, obj.getTarif());
			prepare.setBoolean(7, obj.isLibre());
			prepare.setBoolean(8, obj.isCommunicante());
			prepare.setBoolean(9, obj.isAnimaux());
			prepare.setInt(10, obj.getType_chambre().getId_type_chambre());
			
			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(Chambre obj) {
		try {
			PreparedStatement prepare=SC.prepareStatement("DELETE FROM chambre WHERE id_chambre=?");
			
			prepare.setInt(1, obj.getId_chambre());
			
			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Chambre obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("UPDATE chambre SET id_hotel=?, numero_chambre=?, tele=?, handicap=?, tarif=?, libre=?, communicante=?, animaux=?, id_type_chambre=? WHERE id_chambre=?");
			
			prepare.setInt(1, obj.getHotel().getId_hotel());
			prepare.setInt(2, obj.getNumero_chambre());
			prepare.setBoolean(3, obj.isTele());
			prepare.setBoolean(4, obj.isHandicap());
			prepare.setDouble(5, obj.getTarif());
			prepare.setBoolean(6, obj.isLibre());
			prepare.setBoolean(7, obj.isCommunicante());
			prepare.setBoolean(8, obj.isAnimaux());
			prepare.setInt(9, obj.getType_chambre().getId_type_chambre());
			prepare.setInt(10, obj.getId_chambre());
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public Chambre find(int id) {
		Chambre chambre=new Chambre();
		HotelDAO hotel=new HotelDAO();
		Type_ChambreDAO type_c= new Type_ChambreDAO();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre WHERE id_chambre=?");
			prepare.setInt(1, id);
			ResultSet result=prepare.executeQuery();
			
			if(result.first()){
				chambre.setId_chambre(result.getInt("id_chambre"));
				chambre.setHotel(hotel.find(result.getInt("id_hotel")));
				chambre.setNumero_chambre(result.getInt("numero_chambre"));
				chambre.setTele(result.getBoolean("tele"));
				chambre.setHandicap(result.getBoolean("handicap"));
				chambre.setTarif(result.getDouble("tarif"));
				chambre.setLibre(result.getBoolean("libre"));
				chambre.setCommunicante(result.getBoolean("communicante"));
				chambre.setAnimaux(result.getBoolean("animaux"));
				chambre.setType_chambre(type_c.find(result.getInt("id_type_chambre")));
									
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return chambre;
	}

	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}