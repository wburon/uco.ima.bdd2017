package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import DAO.PersonnelDAO;
import model.Hotel;
import model.Personnel;

@SuppressWarnings("serial")
public class JFCréationCompte extends JFrame {

	private JPaddGerant JPaddGerant;
	private JPaddHotel JPaddHotel;
	
	private Personnel NewPerso;
	private PersonnelDAO pDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFCréationCompte frame = new JFCréationCompte();
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
	public JFCréationCompte() {
		JPaddGerant=new JPaddGerant(this);
		JPaddHotel=new JPaddHotel(this);
		Init();
		pDAO = new PersonnelDAO();
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

	public void Init(){
		setTitle("Création d'un compte");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 489, 341);
		setContentPane(JPaddGerant);
	}
	public void AssociationHotelPerso(Hotel hotel){
		NewPerso.setHotel(hotel);
		pDAO.update(NewPerso);
	}

}
