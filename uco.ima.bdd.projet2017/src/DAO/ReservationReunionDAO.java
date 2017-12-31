package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.ReservationReunion;

public class ReservationReunionDAO extends DAO<ReservationReunion> {

	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(ReservationReunion obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into reservationReunion values (?,?,?,?,?);");

			prepare.setInt(1, maxId());
			prepare.setInt(2, obj.getSalle().getId_salle());
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
	public boolean delete(ReservationReunion obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prepare = SC.prepareStatement("Delete from reservationReunion where id_reservationR=?");

			prepare.setInt(1, obj.getId_reservReunion());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(ReservationReunion obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"Update reservationReunion set id_salle=?, id_client=?, date_debut=?, date_fin=? where id_reservationR=?");

			prepare.setInt(1, obj.getSalle().getId_salle());
			prepare.setInt(2, obj.getClient().getId_client());
			prepare.setDate(3, obj.getDate_debut());
			prepare.setDate(4, obj.getDate_fin());
			prepare.setInt(5, obj.getId_reservReunion());

			prepare.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public ReservationReunion find(int id) {
		// TODO Auto-generated method stub
		ReservationReunion resa = new ReservationReunion();
		Salle_ReunionDAO salleDao = new Salle_ReunionDAO();
		ClientDAO clientDao = new ClientDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM reservationReunion where id_reservationR = ?");
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				resa.setId_reservReunion(id);
				resa.setSalle(salleDao.find(result.getInt("id_salle")));
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
		// TODO Auto-generated method stub
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_reservationR) FROM reservationReunion");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}
	

	public ReservationReunion[] findResaClient(int id_client){
		
		try {
			
			PreparedStatement prepare;
			prepare = SC.prepareStatement("SELECT * FROM reservationReunion where id_client=?");
			prepare.setInt(1, id_client);
			ResultSet result = prepare.executeQuery();
			
			PreparedStatement prepareCount = SC.prepareStatement("SELECT COUNT(*) FROM reservationReunion where id_client=?"); 
			prepareCount.setInt(1, id_client);
			ResultSet resultCount = prepareCount.executeQuery();
			
			ReservationReunion[] reservation = new ReservationReunion[resultCount.getInt(1)];
			for(int i=0; i<reservation.length;i++){
				reservation[i] = find(result.getInt("Id_reservationR"));
				result.next();
			}
			return reservation;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
