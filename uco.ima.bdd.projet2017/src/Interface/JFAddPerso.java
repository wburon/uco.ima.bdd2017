package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.FonctionDAO;
import DAO.PersonneDAO;
import DAO.PersonnelDAO;
import model.Fonction;
import model.Personne;
import model.Personnel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

/*
 * La classe est une JFrame qui s'ouvre lorsqu'uon veut ajouter un membre du personnel
 */
@SuppressWarnings("serial")
public class JFAddPerso extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfAdresse;
	private JTextField tfVille;
	private JTextField tfCodePostal;
	private JTextField tfSalaire;
	private JTextField tfAArrivee;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JTextField tfJ;
	private JTextField tfM;
	private JTextField tfA;
	private JComboBox<String> comboBox;

	private Personne perso;
	private PersonneDAO pDAO;
	private Personnel nel;
	private PersonnelDAO nDAO;
	private Fonction f;
	private FonctionDAO fDAO;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFAddPerso frame = new JFAddPerso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param tfVille 
	 */
	public JFAddPerso() {
		setTitle("Ajout d'un membre du personnel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 596, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		btnValider = new JButton("Valider");
		panel_2.add(btnValider);
		btnValider.addActionListener(this);

		btnAnnuler = new JButton("Annuler");
		panel_2.add(btnAnnuler);
		btnAnnuler.addActionListener(this);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Nom : ");
		panel_4.add(lblNewLabel);

		tfNom = new JTextField();
		panel_4.add(tfNom);
		tfNom.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom :");
		panel_4.add(lblNewLabel_1);

		tfPrenom = new JTextField();
		panel_4.add(tfPrenom);
		tfPrenom.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Adresse :");
		panel_4.add(lblNewLabel_2);

		tfAdresse = new JTextField();
		panel_4.add(tfAdresse);
		tfAdresse.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Ville : ");
		panel_4.add(lblNewLabel_3);

		tfVille = new JTextField();
		panel_4.add(tfVille);
		tfVille.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Code Postal : ");
		panel_4.add(lblNewLabel_4);

		tfCodePostal = new JTextField();
		panel_4.add(tfCodePostal);
		tfCodePostal.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Date de naissance : ");
		panel_4.add(lblNewLabel_5);

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);

		JLabel lblNewLabel_9 = new JLabel("J :");
		lblNewLabel_9.setPreferredSize(new Dimension(20, 14));
		panel_5.add(lblNewLabel_9);

		tfJ = new JTextField();
		tfJ.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_5.add(tfJ);
		tfJ.setColumns(3);

		JLabel lblNewLabel_10 = new JLabel("M :");
		lblNewLabel_10.setPreferredSize(new Dimension(20, 14));
		panel_5.add(lblNewLabel_10);

		tfM = new JTextField();
		tfM.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_5.add(tfM);
		tfM.setColumns(3);

		JLabel lblNewLabel_11 = new JLabel("A :");
		panel_5.add(lblNewLabel_11);

		tfA = new JTextField();
		tfA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_5.add(tfA);
		tfA.setColumns(5);

		JLabel lblNewLabel_6 = new JLabel("Salaire : ");
		panel_4.add(lblNewLabel_6);

		tfSalaire = new JTextField();
		panel_4.add(tfSalaire);
		tfSalaire.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Ann\u00E9e d'arriv\u00E9e :");
		panel_4.add(lblNewLabel_7);

		tfAArrivee = new JTextField();
		panel_4.add(tfAArrivee);
		tfAArrivee.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fonction : ");
		panel_4.add(lblNewLabel_8);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Réceptionniste", "Agent entretien","Gérant" }));
		panel_4.add(comboBox);

		perso = new Personne();
		pDAO = new PersonneDAO();
		nel = new Personnel();
		nDAO = new PersonnelDAO();
		f = new Fonction();
		fDAO = new FonctionDAO();
		


	}

	@SuppressWarnings("deprecation")
	@Override
	/*
	 * Permet d'intéragir avec les boutons valider et annuler
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnValider) {
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
			String fonction = comboBox.getSelectedItem().toString();

			f = fDAO.find(fDAO.renvoieId(fonction));

			String password = nDAO.HashPassword("");
			String login = (prenom.charAt(0) + nom);

			boolean a = creationPerso(adresse, nom, prenom, ville, codePostal, date);
			boolean b = creationNel(anneeArrivee, salaire, password, login);

			if (a == true && b == true) {
				JOptionPane.showMessageDialog(btnValider, "Votre ajout a bien été effectué", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
				this.dispose();
				
			} else {
				JOptionPane.showMessageDialog(btnValider, "Vous avez fait une erreur dans la saisie", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				if (a)
					pDAO.delete(perso);
				else if (b)
					nDAO.delete(nel);
			}

		} else if (e.getSource() == btnAnnuler)
			clearTextField();

	}

	/*
	 * Permet d'effacer le contenu des TextField
	 */
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

	/*
	 * Permet de créer une nouvel personne dans la base de données et de
	 * renvoyer un booléen pour savoir si l'opération a été réussi
	 */
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

	/*
	 * Permet de créer une nouveau membre du personnel dans la base de données
	 * et de renvoyer un booléen pour savoir si l'opération a été réussi
	 */
	public boolean creationNel(int anneeArrivee, double salaire, String password, String login) {
		nel.setAnnee_arrivee(anneeArrivee);
		nel.setFonction(f);
		nel.setId_personnel(nDAO.maxId());
		nel.setPersonne(perso);
		nel.setSalaire(salaire);
		nel.setPassword(password);
		nel.setLogin(login);

		boolean verif = nDAO.create(nel);
		return verif;
	}

	/*
	 * Renvoie le membre du personnel enregistrer dans cette classe
	 */
	public Personnel getPersonnel() {
		return nel;
	}
}
