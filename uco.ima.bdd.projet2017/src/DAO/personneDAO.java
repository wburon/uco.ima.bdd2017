package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Singleton.SingletonConnection;
import model.personne;

public class personneDAO extends DAO<personne> {

	Connection SC = SingletonConnection.getConnection();
	
	@Override
	public boolean create(personne obj) {
		try {

			PreparedStatement prepare = SC
					.prepareStatement("Insert into abonne values (?,?,?,?);");

			prepare.setInt(1, obj.getIdabonne());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setInt(4, obj.getNbemprunt());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public personne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
