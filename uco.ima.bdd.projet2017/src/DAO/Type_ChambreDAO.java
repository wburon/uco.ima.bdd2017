package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.Type_Chambre;

public class Type_ChambreDAO extends DAO<Type_Chambre>{

	Connection SC = SingletonConnection.getConnection();
	
	@Override
	public boolean create(Type_Chambre obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("INSERT INTO type_chambre VALUES (?,?,?,?,?)");
			
			prepare.setInt(1, obj.getId_type_chambre());
			prepare.setString(2, obj.getNom());
			prepare.setInt(3, obj.getNb_piece());
			prepare.setInt(4, obj.getNb_lit_double());
			prepare.setInt(5, obj.getNb_lit_simple());
			
			prepare.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Type_Chambre obj) {
		try{
			PreparedStatement prepare = SC.prepareStatement("DELETE FROM type_chambre WHERE id_type_chambre = ?");
			
			prepare.setInt(1, obj.getId_type_chambre());
			
			prepare.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Type_Chambre obj) {
		try{
			PreparedStatement prepare = SC.prepareStatement("UPDATE type_chambre SET nom=?, nb_piece=?, nb_lit_double=?, nb_lit_simple=? WHERE id_type_chambre=?");
			
			prepare.setString(1, obj.getNom());
			prepare.setInt(2, obj.getNb_piece());
			prepare.setInt(3, obj.getNb_lit_double());
			prepare.setInt(4, obj.getNb_lit_simple());
			prepare.setInt(5, obj.getId_type_chambre());
			
			prepare.executeUpdate();
			
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Type_Chambre find(int id) {
		Type_Chambre type_c = new Type_Chambre();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM type_chambre WHERE id_type_chambre=?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if(result.first()){
				type_c.setId_type_chambre(result.getInt("id_type_chambre"));
				type_c.setNom(result.getString("nom"));
				type_c.setNb_piece(result.getInt("nb_piece"));
				type_c.setNb_lit_double(result.getInt("nb_lit_double"));
				type_c.setNb_lit_simple(result.getInt("nb_lit_simple"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return type_c;
	}

	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
