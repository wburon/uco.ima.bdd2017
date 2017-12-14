package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Chambre;
import model.Communicante;
import model.Type_Chambre;

public class ChambreDAO extends DAO<Chambre>{
	
	Connection SC = SingletonConnection.getConnection();

	@Override
	/**
	 * create
	 * @param Chambre
	 * @reurn Cette methode creer une nouvelle chambre dans la base de donnee
	 */
	public boolean create(Chambre obj) {
		try {
			PreparedStatement prepare = SC.prepareStatement("INSERT INTO chambre VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			prepare.setInt(1, maxId());
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
	/**
	 * delete
	 * @param Chambre
	 * @return Cette methode supprime chambre dans la base de donnee
	 */
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
	/**
	 * update
	 * @param Chambre
	 * @return Cette methode met a jour la chambre ayant le m�me id dans la base de donnee
	 */
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
	/**
	 * find
	 * @param id
	 * @return
	 * Cette methode renvoi la chambre ayant cet id dans la base de donnee
	 */
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

	
	/**
	 * findCommunicante
	 * Cette methode cherche une chambre communicante
	 * retrouve avec qui elle communique
	 * @return
	 * et retourne un Communicante qui contient les deux chambres
	 */
	public Communicante findCommunicante(){
		Chambre chambre=new Chambre(); /** Chambre principale */
		Chambre chambreC = new Chambre(); /** Chambre qui communique avec la principale*/
		HotelDAO hotel=new HotelDAO();
		Type_ChambreDAO type_c= new Type_ChambreDAO(); /** type chambre principale */
		Type_ChambreDAO type_cC= new Type_ChambreDAO(); /** type chambre qui communiquye avec la principale*/
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre WHERE communicante=True");
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
			PreparedStatement prepare2 = SC.prepareStatement("SELECT * FROM communicante WHERE c1=? OR c2=?");
			prepare2.setInt(1, chambre.getId_chambre());
			prepare2.setInt(2, chambre.getId_chambre());
			ResultSet result2=prepare2.executeQuery();
			
			if (result2.getInt("c1")==chambre.getId_chambre()){
				chambreC = find(result2.getInt("c2"));
			}else chambreC = find(result2.getInt("c1"));
			
			Communicante comm = new Communicante();
			comm.setId_communicante(result2.getInt("id_communicante"));
			comm.setC1(chambre);
			comm.setC2(chambreC);
			
			return comm;
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * findPerfect
	 * @param tele
	 * @param animaux
	 * @param handi
	 * @param TC
	 * @param prixInf
	 * @param prixSup
	 * @param comm
	 * Cette methode recherche une chambre avec l'ensembles des param�tre demander.
	 * @return
	 * La Chambre qui correspond au contrainte
	 */
	public Chambre findPerfect(boolean tele, boolean animaux, boolean handi, Type_Chambre TC, double prixInf, 
			double prixSup, boolean comm){
		Chambre chambre=new Chambre();
		HotelDAO hotel=new HotelDAO();
		Type_ChambreDAO type_c= new Type_ChambreDAO();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre WHERE tele=? AND animaux=? "
					+ "AND handicap=? AND communicante=? AND id_type_chambre=? AND tarif>=? AND tarif<=?");
			prepare.setBoolean(1, tele);
			prepare.setBoolean(2, animaux);
			prepare.setBoolean(3, handi);
			prepare.setBoolean(4, comm);
			if (TC != null)
				prepare.setInt(5, TC.getId_type_chambre());
			else 
				prepare.setString(5, "id_type_chambre");
			
			prepare.setDouble(6, prixInf);	
			prepare.setDouble(7, prixSup);

			
			
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
	/**
	 * maxId
	 * @return Cette methode renvoi l'id le plus grand de la table chambre
	 */
	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_chambre) FROM chambre");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}
	
	public boolean findIfChambreIsLibre(Date debutResa, Date finResa){
		// r�cuperation de la date du jour
//		String format = "dd/MM/yyyy"; 
//		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
		
		if (date.before(debutResa) || date.after(finResa))
			return true;
		else
			return false;
	}

}
