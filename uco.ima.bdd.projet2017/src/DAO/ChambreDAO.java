package DAO;

import java.sql.Connection;

import Singleton.SingletonConnection;
import model.Chambre;

public class ChambreDAO extends DAO<Chambre>{
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Chambre obj) {
		
		
		return false;
	}

	@Override
	public boolean delete(Chambre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Chambre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Chambre find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
