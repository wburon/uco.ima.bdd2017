package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Singleton.SingletonConnection;
import model.Chambre;
import model.Communicante;
import model.Hotel;
import model.Type_Chambre;

public class ChambreDAO extends DAO<Chambre>{
	
	Connection SC = SingletonConnection.getConnection();
	private HotelDAO hotel=new HotelDAO();
	private Type_ChambreDAO type_c= new Type_ChambreDAO();

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
			
			increaseNbChambreHotel(obj.getHotel().getId_hotel());
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	private void increaseNbChambreHotelLibre(int id_hotel) {
		HotelDAO hDAO = new HotelDAO();
		Hotel h = hDAO.find(id_hotel);
		h.setNb_chambre_libre(h.getNb_chambre_libre()+1);
		hDAO.update(h);
		
	}
	
	private void increaseNbChambreHotel(int id_hotel) {
		HotelDAO hDAO = new HotelDAO();
		Hotel h = hDAO.find(id_hotel);
		h.setNb_chambre_total(h.getNb_chambre_total()+1);
		hDAO.update(h);
		
	}
	
	private void decreaseNbChambreHotel(int id_hotel){
		HotelDAO hDAO = new HotelDAO();
		Hotel h = hDAO.find(id_hotel);
		h.setNb_chambre_total(h.getNb_chambre_total()-1);
	}
	
	private void decreaseNbChambreHotelLibre(int id_hotel){
		HotelDAO hDAO = new HotelDAO();
		Hotel h = hDAO.find(id_hotel);
		h.setNb_chambre_libre(h.getNb_chambre_libre()-1);
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
			
			decreaseNbChambreHotel(obj.getHotel().getId_hotel());
			if(obj.isLibre())
				decreaseNbChambreHotelLibre(obj.getHotel().getId_hotel());
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
	 * @return Cette methode met a jour la chambre ayant le même id dans la base de donnee
	 */
	public boolean update(Chambre obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("UPDATE chambre SET numero_chambre=?, tele=?, handicap=?, tarif=?, libre=?, communicante=?, animaux=?, id_type_chambre=? WHERE id_chambre=?");
			
			prepare.setInt(1, obj.getNumero_chambre());
			prepare.setBoolean(2, obj.isTele());
			prepare.setBoolean(3, obj.isHandicap());
			prepare.setDouble(4, obj.getTarif());
			prepare.setBoolean(5, obj.isLibre());
			prepare.setBoolean(6, obj.isCommunicante());
			prepare.setBoolean(7, obj.isAnimaux());
			prepare.setInt(8, obj.getType_chambre().getId_type_chambre());
			prepare.setInt(9, obj.getId_chambre());
			
			prepare.executeUpdate();
			
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
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre WHERE id_chambre=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
	 * Cette methode recherche les chambres avec l'ensembles des paramètres demandés.
	 * @return
	 * Les Chambres qui correspondent aux contraintes
	 */
	public ArrayList<Chambre> findPerfect(Type_Chambre TC, boolean animaux, boolean comm,  boolean handi, boolean tele,  double prixSup,  int id_hotel, Date debut, Date fin){
		ArrayList<Chambre> listChambre = new ArrayList<Chambre>();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre WHERE tele=? AND animaux=? "
					+ "AND handicap=? AND communicante=? AND id_type_chambre=? AND tarif<=? AND id_hotel=?");
			
			
			prepare.setBoolean(1, tele);
			prepare.setBoolean(2, animaux);
			prepare.setBoolean(3, handi);
			prepare.setBoolean(4, comm);
			
			if (TC != null)
				prepare.setInt(5, TC.getId_type_chambre());
			else 
				prepare.setString(5, "id_type_chambre");
			
				
			prepare.setDouble(6, prixSup);
			prepare.setInt(7, id_hotel);

			ResultSet result=prepare.executeQuery();
			
			ArrayList<Integer> notLibre = findChambreIsNotLibre(debut,fin,id_hotel);
			
			while(result.next()){
				if(!notLibre.contains(result.getInt("id_chambre")))
						listChambre.add(createChambre(result));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listChambre;
	}
	
	public Chambre createChambre(ResultSet result){
		Chambre chambre = new Chambre();
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public ArrayList<Integer> findChambreIsNotLibre(Date debutResa, Date finResa, int id_hotel){ 
		ArrayList<Integer> listIdNotLibre = new ArrayList<Integer>();

		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM reservation where (date_debut<? AND date_debut>?) OR (date_fin<? AND date_fin>?) AND id_hotel=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setDate(1, new java.sql.Date(finResa.getTime()));
			prepare.setDate(2, new java.sql.Date(debutResa.getTime()));
			prepare.setDate(3, new java.sql.Date(finResa.getTime()));
			prepare.setDate(4, new java.sql.Date(debutResa.getTime()));
			prepare.setInt(5, id_hotel);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()){
				listIdNotLibre.add(result.getInt("id_chambre"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listIdNotLibre;
		
	}
	
	public boolean findIfChambreIsLibreToday(int id_chambre){
		try {
			
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM reservation where id_chambre=? AND date_debut<=? AND date_fin>=?");
			prepare.setInt(1, id_chambre);
			prepare.setString(2, currentDate());
			prepare.setString(3, currentDate());
			ResultSet result = prepare.executeQuery();
			
			if(result.first())
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean NumChambreExisteDeja(String text,  int idchambre, int idhotel) {
		PreparedStatement prepare;
		try {
			prepare = SC.prepareStatement("SELECT * FROM chambre WHERE id_hotel=? AND numero_chambre=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setInt(1, idhotel);
			prepare.setInt(2, Integer.parseInt(text));
			ResultSet result=prepare.executeQuery();
			
			if(result.first() && result.getInt("id_chambre") == idchambre){
				System.out.println("tout va bien, le num chambre n'a pas été changé");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean NumChambreExisteDeja(String text, int IdHotel) {
		PreparedStatement prepare;
		try {
			prepare = SC.prepareStatement("SELECT * FROM chambre WHERE id_hotel=? AND numero_chambre=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setInt(1, IdHotel);
			prepare.setInt(2, Integer.parseInt(text));
			ResultSet result=prepare.executeQuery();
			
			if(result.first()){
				System.out.println("Ce numéro de chambre existe déjà pour cette hotel !");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public String currentDate(){
		String format = "dd-MM-yyyy"; 

		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 

		return formater.format( date );
	}
	
	public Chambre[] chambresVoisines(int id_chambre){
		Chambre [] voisines = new Chambre[2];
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre where id_hotel=? AND (numero_chambre=? OR numero_chambre=?)");
			Chambre c = find(id_chambre);
			prepare.setInt(1, c.getHotel().getId_hotel());
			prepare.setInt(2, c.getNumero_chambre()-1);
			prepare.setInt(3, c.getNumero_chambre()+1);
			ResultSet result = prepare.executeQuery();
			
			int i=0;
			while(result.next()){
				voisines[i] = find(result.getInt("id_chambre"));
				i++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voisines;
	}

	public ArrayList<Chambre> ListChambre(int id_hotel) {
		ArrayList<Chambre> listChambre = new ArrayList<Chambre>();
		
		Chambre obj = new Chambre();
		try{
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM chambre where id_hotel=?");
			prepare.setInt(1, id_hotel);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()){
				obj = find(result.getInt("id_chambre"));
				listChambre.add(obj);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return listChambre;
	}

	
	
	

}
