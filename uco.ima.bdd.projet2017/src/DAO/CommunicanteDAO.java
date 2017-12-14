package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Communicante;

public class CommunicanteDAO extends DAO<Communicante>{
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Communicante obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into communicante values (?,?,?);");
			
			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getC1().getId_chambre());
			prepare.setInt(3, obj.getC2().getId_chambre());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Communicante obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from communicante where id_communicante=?");

			prepare.setInt(1, obj.getId_communicante());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Communicante obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update communicante set c1=?, c2=? where id_communicante=?");

			prepare.setInt(1, obj.getC1().getId_chambre());
			prepare.setInt(2, obj.getC2().getId_chambre());
			prepare.setInt(3, obj.getId_communicante());

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Communicante find(int id) {
		Communicante comm = new Communicante();
		ChambreDAO chambreDao = new ChambreDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM communicante where id_communicante = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				comm.setC1(chambreDao.find(result.getInt("c1")));
				comm.setC2(chambreDao.find(result.getInt("c2")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comm;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_communicante) FROM communicante");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}

}
