package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.Personnel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class JFInterface extends JFrame{

	
	private JPlog JPlog;
	private JPgeHotel JPgeHotel;
	private JPgePerso JPgePerso;
	private JPgerant JPgerant;
	private JPEmployer JPEmployer;
	private JPgeClient JPgeClient;
	
	private Personnel PersoConn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFInterface frame = new JFInterface();
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
	public JFInterface() {
		setTitle("Progiciel Gerotel");



		JPlog=new JPlog(this);
		JPgerant = new JPgerant(this);
		JPgePerso = new JPgePerso(this);
		JPEmployer = new JPEmployer(this);
		JPgeHotel = new JPgeHotel(this);
		JPgeClient = new JPgeClient(this);
		Init();
		
	}

	public void Init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 575);
		setContentPane(JPlog);
	}

	
	public Personnel getPersoConn() {
		return PersoConn;
	}

	public void setPersoConn(Personnel persoConn) {
		PersoConn = persoConn;
	}

	public JPEmployer getJPEmployer() {
		return JPEmployer;
	}

	public void setJPEmployer(JPEmployer jPEmployer) {
		JPEmployer = jPEmployer;
	}
	public JPgeClient getJPgeClient() {
		return JPgeClient;
	}

	public void setJPgeClient(JPgeClient jPgeClient) {
		JPgeClient = jPgeClient;
	}

	public JPlog getJPlog() {
		return JPlog;
	}

	public void setJPlog(JPlog jPlog) {
		JPlog = jPlog;
	}

	public JPgeHotel getJPgeHotel() {
		return JPgeHotel;
	}

	public void setJPgeHotel(JPgeHotel jPgeHotel) {
		JPgeHotel = jPgeHotel;
	}

	public JPgePerso getJPgePerso() {
		return JPgePerso;
	}

	public void setJPgePerso(JPgePerso jPgePerso) {
		JPgePerso = jPgePerso;
	}

	public JPgerant getJPgerant() {
		return JPgerant;
	}

	public void setJPgerant(JPgerant jPgerant) {
		JPgerant = jPgerant;
	}

}
