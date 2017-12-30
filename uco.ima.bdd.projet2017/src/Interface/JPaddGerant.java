package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.FonctionDAO;
import DAO.PersonneDAO;
import DAO.PersonnelDAO;
import model.Fonction;
import model.Hotel;
import model.Personne;
import model.Personnel;

import javax.swing.JButton;

public class JPaddGerant extends JPanel implements ActionListener{
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfAdresse;
	private JTextField tfVille;
	private JTextField tfCodePostal;
	private JTextField tfSalaire;
	private JTextField tfAArrivee;
	private JTextField tfJ;
	private JTextField tfM;
	private JTextField tfA;
	
	private JButton btnFinir;
	private JButton btnAnnuler;
	
	private Personne perso;
	private PersonneDAO pDAO;
	private Personnel nel;
	private PersonnelDAO nDAO;
	private Fonction f;
	private FonctionDAO fDAO;
	
	private JFCréationCompte JFCreationCompte;

	/**
	 * Create the panel.
	 */
	public JPaddGerant(JFCréationCompte JFCreationCompte) {
		this.JFCreationCompte=JFCreationCompte;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblVousDeveztre = new JLabel("Vous devez \u00EAtre le g\u00E9rant de l'h\u00F4tel pour vous cr\u00E9ez un compte.");
		lblVousDeveztre.setToolTipText("");
		panel.add(lblVousDeveztre);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		btnFinir = new JButton("Finir");
		panel_2.add(btnFinir);
		btnFinir.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		panel_2.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNom = new JLabel("Nom :");
		panel_4.add(lblNom);
		
		tfNom = new JTextField();
		panel_4.add(tfNom);
		tfNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom :");
		panel_4.add(lblPrenom);
		
		tfPrenom = new JTextField();
		panel_4.add(tfPrenom);
		tfPrenom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		panel_4.add(lblAdresse);
		
		tfAdresse = new JTextField();
		panel_4.add(tfAdresse);
		tfAdresse.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville :");
		panel_4.add(lblVille);
		
		tfVille = new JTextField();
		panel_4.add(tfVille);
		tfVille.setColumns(10);
		
		JLabel lblCodePostal = new JLabel(" Code Postal :");
		panel_4.add(lblCodePostal);
		
		tfCodePostal = new JTextField();
		panel_4.add(tfCodePostal);
		tfCodePostal.setColumns(10);
		
		JLabel lblDateDeNaissance = new JLabel("Date de Naissance :");
		panel_4.add(lblDateDeNaissance);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblJ = new JLabel("J :");
		panel_5.add(lblJ);
		
		tfJ = new JTextField();
		panel_5.add(tfJ);
		tfJ.setColumns(3);
		
		JLabel lblM = new JLabel("M :");
		panel_5.add(lblM);
		
		tfM = new JTextField();
		panel_5.add(tfM);
		tfM.setColumns(3);
		
		JLabel lblA = new JLabel("A :");
		panel_5.add(lblA);
		
		tfA = new JTextField();
		panel_5.add(tfA);
		tfA.setColumns(5);
		
		JLabel lblFonction = new JLabel("Fonction :");
		panel_4.add(lblFonction);
		
		JLabel lblGrant = new JLabel("G\u00E9rant");
		panel_4.add(lblGrant);
		
		JLabel lblNewLabel = new JLabel("Salaire : ");
		panel_4.add(lblNewLabel);
		
		tfSalaire = new JTextField();
		panel_4.add(tfSalaire);
		tfSalaire.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ann\u00E9e d'arriv\u00E9e :");
		panel_4.add(lblNewLabel_1);
		
		tfAArrivee = new JTextField();
		panel_4.add(tfAArrivee);
		tfAArrivee.setColumns(10);
		
		perso = new Personne();
		pDAO = new PersonneDAO();
		nel = new Personnel();
		nDAO = new PersonnelDAO();
		f = new Fonction();
		fDAO = new FonctionDAO();

	}
	public void clearTextField() {
		tfNom.setText("");
		tfPrenom.setText("");
		tfAdresse.setText("");
		tfVille.setText("");
		tfCodePostal.setText("");
		tfJ.setText("");
		tfM.setText("");
		tfA.setText("");
		tfSalaire.setText("");
		tfAArrivee.setText("");

	}
	public boolean creationPerso(String adresse, String nom, String prenom, String ville, int codePostal, Date date) {
		perso.setId_personne(pDAO.maxId());
		perso.setAdresse(adresse);
		perso.setNom(nom);
		perso.setPrenom(prenom);
		perso.setVille(ville);
		perso.setCode_postal(codePostal);
		perso.setDate_de_naissance(date);

		boolean verif = pDAO.create(perso);
		return verif;
	}
	public boolean creationNel(int anneeArrivee, double salaire, String password, String login, Hotel hotel) {
		nel.setAnnee_arrivee(anneeArrivee);
		nel.setFonction(f);
		nel.setId_personnel(nDAO.maxId());
		nel.setPersonne(perso);
		nel.setSalaire(salaire);
		nel.setPassword(password);
		nel.setLogin(login);
		nel.setHotel(hotel);

		boolean verif = nDAO.create(nel);
		return verif;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnFinir) {
			String nom = tfNom.getText();
			String prenom = tfPrenom.getText();
			String adresse = tfAdresse.getText();
			String ville = tfVille.getText();
			int codePostal = Integer.parseInt(tfCodePostal.getText());
			int annee = Integer.parseInt(tfA.getText()) - 1900;
			int mois = Integer.parseInt(tfM.getText()) - 1;
			int jour = Integer.parseInt(tfJ.getText());
			Date date = new Date(annee, mois, jour);
			double salaire = Double.parseDouble(tfSalaire.getText());
			int anneeArrivee = Integer.parseInt(tfAArrivee.getText());

			f = fDAO.find(3);

			String password = nDAO.HashPassword("");
			String login = (prenom.charAt(0) + nom);

			boolean a = creationPerso(adresse, nom, prenom, ville, codePostal, date);
			boolean b = creationNel(anneeArrivee, salaire, password, login, JFCreationCompte.getHotel());

			if (a == true && b == true) {
				JOptionPane.showMessageDialog(btnFinir, "La création de votre compte a réussi.\n Lors de votre première connexion votre login est composé de la première lettre de votre prénom et votre nom.\n Le mot de passe est vide", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				JFCreationCompte.associationProprioHotel(nel);
				JFCreationCompte.dispose();
				
				
			} else {
				JOptionPane.showMessageDialog(btnFinir, "Vous avez fait une erreur dans la saisie", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				if (a)
					pDAO.delete(perso);
				else if (b)
					nDAO.delete(nel);
			}

		} else if (e.getSource() == btnAnnuler){
			clearTextField();
			JFCreationCompte.dispose();
		}

	}

}
