package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ClientDAO;
import DAO.PersonneDAO;
import model.Client;
import model.Personne;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class JFAddClient extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField jtfNom;
	private JTextField jtfPrenom;
	private JTextField jtfAdresse;
	private JTextField jtfVille;
	private JTextField jtfCP;
	private JTextField jtfJour;
	private JTextField jtfMois;
	private JTextField jtfAn;
	private JButton btnValider;
	private Personne perso = new Personne();
	private PersonneDAO pDAO = new PersonneDAO();
	private Client client = new Client();
	private ClientDAO clDAO = new ClientDAO();
	private JFClientReservation frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFAddClient frame = new JFAddClient();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public JFAddClient(JFClientReservation frame) {
		this.frame = frame;
		
		setTitle("Nouveau Client");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnValider = new JButton("Valider");
		panel.add(btnValider);
		btnValider.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel lblNom = new JLabel("Nom");
		panel_1.add(lblNom);
		
		jtfNom = new JTextField();
		panel_1.add(jtfNom);
		jtfNom.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		panel_1.add(lblPrnom);
		
		jtfPrenom = new JTextField();
		panel_1.add(jtfPrenom);
		jtfPrenom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		panel_1.add(lblAdresse);
		
		jtfAdresse = new JTextField();
		panel_1.add(jtfAdresse);
		jtfAdresse.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		panel_1.add(lblVille);
		
		jtfVille = new JTextField();
		panel_1.add(jtfVille);
		jtfVille.setColumns(10);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		panel_1.add(lblCodePostal);
		
		jtfCP = new JTextField();
		panel_1.add(jtfCP);
		jtfCP.setColumns(10);
		
		JLabel lblDateDeNaissance = new JLabel("Date de naissance");
		panel_1.add(lblDateDeNaissance);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblJ = new JLabel("J :");
		lblJ.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJ.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblJ);
		
		jtfJour = new JTextField();
		panel_2.add(jtfJour);
		jtfJour.setColumns(3);
		
		JLabel lblM = new JLabel("M :");
		panel_2.add(lblM);
		
		jtfMois = new JTextField();
		panel_2.add(jtfMois);
		jtfMois.setColumns(3);
		
		JLabel lblA = new JLabel("A :");
		panel_2.add(lblA);
		
		jtfAn = new JTextField();
		panel_2.add(jtfAn);
		jtfAn.setColumns(6);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnValider){
			String nom = jtfNom.getText();
			String prenom = jtfPrenom.getText();
			String adresse = jtfAdresse.getText();
			String ville = jtfVille.getText();
			int codePostal = Integer.parseInt(jtfCP.getText());
			int annee = Integer.parseInt(jtfAn.getText()) - 1900;
			int mois = Integer.parseInt(jtfMois.getText()) - 1;
			int jour = Integer.parseInt(jtfJour.getText());
			Date date = new Date(annee, mois, jour);
			
			boolean a = creationPerso(adresse, nom, prenom, ville, codePostal, date);
			boolean b = creationClient(frame.getListReservation().size());
			if (a) {
				JOptionPane.showMessageDialog(btnValider, "Votre ajout a bien été effectué", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(true);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(btnValider, "Vous avez fait une erreur dans la saisie", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				pDAO.delete(perso);
			}
		}
		
	}
	
	/*
	 * Permet d'effacer le contenu des TextField
	 */
	public void clearTextField() {
		jtfNom.setText("");
		jtfPrenom.setText("");
		jtfAdresse.setText("");
		jtfVille.setText("");
		jtfCP.setText("");
		jtfJour.setText("");
		jtfMois.setText("");
		jtfAn.setText("");
	}
	
	/*
	 * Permet de créer une nouvel personne dans la base de données et de
	 * renvoyer un booléen pour savoir si l'opération a été réussi
	 */
	public boolean creationPerso(String adresse, String nom, String prenom, String ville, int codePostal, Date date) {
		perso.setAdresse(adresse);
		perso.setNom(nom);
		perso.setPrenom(prenom);
		perso.setVille(ville);
		perso.setCode_postal(codePostal);
		perso.setDate_de_naissance(date);
		perso.setId_personne(pDAO.maxId());

		boolean verif = pDAO.create(perso);
		return verif;
	}
	
	public boolean creationClient(int nbResaEnCour){
		client.setPersonne(perso);
		client.setNb_resa_en_cours(nbResaEnCour);
		
		boolean verif = clDAO.create(client);
		return verif;
	}
	
	

}
