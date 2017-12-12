package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Salle_Reunion;

public class Salle_ReunionDAO extends DAO<Salle_Reunion> {
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Salle_Reunion obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into salle_reunion values (?,?,?,?,?);");

			prepare.setInt(1, obj.getId_salle());
			prepare.setInt(2, obj.getHotel().getId_hotel());
			prepare.setInt(3, obj.getNumero_salle());
			prepare.setBoolean(4, obj.isMateriel_informatique());
			prepare.setInt(5, obj.getCapacite());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Salle_Reunion obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from salle_reunion where id_salle=?");

			prepare.setInt(1, obj.getId_salle());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Salle_Reunion obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update salle_reunion set numero_salle=?, materiel_informatique=?, capacite=? where id_parking=?");

			prepare.setInt(1, obj.getNumero_salle());
			prepare.setBoolean(2, obj.isMateriel_informatique());
			prepare.setInt(3, obj.getCapacite());
			prepare.setInt(5, obj.getId_salle());

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Salle_Reunion find(int id) {
		Salle_Reunion salle = new Salle_Reunion();
		HotelDAO hotelDao = new HotelDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM salle_reunion where id_salle = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				salle.setId_salle(result.getInt("id_salle"));
				salle.setHotel(hotelDao.find(result.getInt("id_hotel")));
				salle.setNumero_salle(result.getInt("numero_salle"));
				salle.setMateriel_informatique(result.getBoolean("materiel_informatique"));
				salle.setCapacite(result.getInt("capacite"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salle;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_salle) FROM salle_reunion");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}

}
