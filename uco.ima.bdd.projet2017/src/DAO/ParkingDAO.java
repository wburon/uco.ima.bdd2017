package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Parking;

public class ParkingDAO extends DAO<Parking> {

	Connection SC = SingletonConnection.getConnection();

	@Override
	public boolean create(Parking obj) {
		try {

			PreparedStatement prepare = SC.prepareStatement("Insert into parking values (?,?,?,?,?,?,?);");

			prepare.setInt(1, obj.getId_parking());
			prepare.setInt(2, obj.getHotel().getId_hotel());
			prepare.setInt(3, obj.getNb_place());
			prepare.setBoolean(4, obj.isGratuit());
			prepare.setDouble(5, obj.getDistance_hotel());
			prepare.setBoolean(6, obj.isVeilleur_de_nuit());
			prepare.setString(7, obj.getName());

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
					"Update parking set nb_place=?, gratuit=?, veilleur_de_nuit=?, name=? where id_parking=?");

			prepare.setInt(1, obj.getNb_place());
			prepare.setBoolean(2, obj.isGratuit());
			prepare.setBoolean(3, obj.isVeilleur_de_nuit());
			prepare.setString(4, obj.getName());
			prepare.setInt(5, obj.getId_parking());

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
				parking.setName(result.getString("name"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parking;
	}

	@Override
	public int maxId() {
		Statement state;
		int nbRow = 0;
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

	/**
	 * Trouve une liste de parkings selon les parametres
	 * 
	 * @param id_hotel
	 *            : l'hotel dont doit dépendre le parking
	 * @param gratuit
	 *            : le parking est-il gratuit ?
	 * @param veilleurDeNuit
	 *            : le parking est-il surveillé ?
	 * @param distanceMaxToHotel
	 *            : la distance maximale entre l'hotel et le parking
	 * @return list parking
	 */
	public Parking[] findMyPark(int id_hotel, boolean gratuit, boolean veilleurDeNuit, double distanceMaxToHotel) {
		HotelDAO hotelDao = new HotelDAO();
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"SELECT * FROM parking where id_hotel = ? AND gratuit=? AND veilleur_de_nuit=? AND distance_de_nuit<=? ");

			if (hotelDao.find(id_hotel) != null)
				prepare.setInt(1, id_hotel);
			else
				prepare.setString(1, "id_hotel");

			if (gratuit != true && gratuit != false)
				prepare.setString(2, "true or false");
			else
				prepare.setBoolean(2, gratuit);

			if (veilleurDeNuit != true && gratuit != false)
				prepare.setString(3, "true or false");
			else
				prepare.setBoolean(3, veilleurDeNuit);

			if (distanceMaxToHotel != (Double) null)
				prepare.setDouble(4, distanceMaxToHotel);
			else
				prepare.setDouble(4, Double.MAX_VALUE);

			ResultSet result = prepare.executeQuery();

			if (result.first()) {
				return ResultSetConvertToArray(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Trouve le nombre de parking que renvoie la requete de findMyPark
	 * 
	 * @return entier : nombre de parking trouvé
	 */
	public int countResultSetFindMyPark() {
		try {
			PreparedStatement prepare = SC.prepareStatement(
					"SELECT Count(*) FROM parking where id_hotel = ? AND gratuit=? AND veilleur_de_nuit=? AND distance_de_nuit<=? ");
			ResultSet result = prepare.executeQuery();
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Transforme le resultSet en tableau de parking
	 * 
	 * @param result
	 *            : resultat de la requete
	 * @return tableau de parking trouver
	 */
	public Parking[] ResultSetConvertToArray(ResultSet result) {
		try {
			Parking[] parkingT = new Parking[countResultSetFindMyPark()];
			for (int i = 0; i < parkingT.length; i++) {

				parkingT[i] = find(result.getInt("Id_parking"));

				result.next();
			}
			return parkingT;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
