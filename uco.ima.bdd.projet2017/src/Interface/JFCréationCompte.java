package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import DAO.HotelDAO;
import model.Hotel;
import model.Personnel;

@SuppressWarnings("serial")
public class JFCr�ationCompte extends JFrame {

	private JPaddGerant JPaddGerant;
	private JPaddHotel JPaddHotel;
	
	private Personnel NewPerso;
	private HotelDAO hDAO;
	private Hotel hotel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFCr�ationCompte frame = new JFCr�ationCompte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFCr�ationCompte() {
		JPaddGerant=new JPaddGerant(this);
		JPaddHotel=new JPaddHotel(this);
		Init();
		hDAO = new HotelDAO();
	}

	public Personnel getNewPerso() {
		return NewPerso;
	}

	public void setNewPerso(Personnel newPerso) {
		NewPerso = newPerso;
	}
	public JPaddGerant getJPaddGerant() {
		return JPaddGerant;
	}

	public void setJPaddGerant(JPaddGerant jPaddGerant) {
		JPaddGerant = jPaddGerant;
	}

	public JPaddHotel getJPaddHotel() {
		return JPaddHotel;
	}

	public void setJPaddHotel(JPaddHotel jPaddHotel) {
		JPaddHotel = jPaddHotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void Init(){
		setTitle("Cr�ation d'un compte");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 512, 373);
		setContentPane(JPaddHotel);
	}
	/*
	 * Cette m�thode associe les informations r�cup�rer dans JPaddHotel et JPaddG�rant 
	 * Pour que le g�rant aie comme id_hotel l'hotel cr�er juste avant
	 */
	public void associationProprioHotel(Personnel personnel){
		hotel.setProprietaire(personnel.getPersonne().getNom());
		hDAO.update(hotel);
	}

}
