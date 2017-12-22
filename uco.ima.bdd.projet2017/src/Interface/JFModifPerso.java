package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.FonctionDAO;
import DAO.PersonneDAO;
import DAO.PersonnelDAO;
import model.Fonction;
import model.Personne;
import model.Personnel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class JFModifPerso extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JTextField tfSalaire;
	private JTextField tfAArrivee;
	private JTextField tfLogin;
	private JPasswordField passwordField;
	
	private JComboBox comboBox;
	private JButton btnValider;
	private JButton btnAnnuler;
	
	private Personnel nel;
	private PersonnelDAO nDAO;
	private Personne perso;
	private PersonneDAO pDAO;
	private FonctionDAO fDAO;
	private Fonction f;
	private JTextField tfAdresse;
	private JTextField tfVille;
	private JTextField tfCodePostal;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFModifPerso frame = new JFModifPerso(new Personnel());
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
	public JFModifPerso(Personnel nel) {
		this.nel=nel;
		this.perso=nel.getPersonne();
		

		pDAO=new PersonneDAO();
		fDAO=new FonctionDAO();
		nDAO=new PersonnelDAO();
		
		
		setTitle("Modification d'un membre du personnel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblSlectionnerUnePersonne = new JLabel("S\u00E9lectionner une personne dans la liste");
		panel.add(lblSlectionnerUnePersonne);
		
		JLabel label = new JLabel("");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnValider = new JButton("Valider");
		panel_2.add(btnValider);
		btnValider.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		panel_2.add(btnAnnuler);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Nom :");
		panel_4.add(lblNewLabel);
		
		tfNom = new JTextField();
		panel_4.add(tfNom);
		tfNom.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom : ");
		panel_4.add(lblNewLabel_1);
		
		tfPrenom = new JTextField();
		panel_4.add(tfPrenom);
		tfPrenom.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Salaire :");
		panel_4.add(lblNewLabel_2);
		
		tfSalaire = new JTextField();
		panel_4.add(tfSalaire);
		tfSalaire.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ann\u00E9e d'arriv\u00E9e :");
		panel_4.add(lblNewLabel_3);
		
		tfAArrivee = new JTextField();
		panel_4.add(tfAArrivee);
		tfAArrivee.setColumns(10);
		
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
		
		JLabel lblCodePostal = new JLabel("Code Postal :");
		panel_4.add(lblCodePostal);
		
		tfCodePostal = new JTextField();
		panel_4.add(tfCodePostal);
		tfCodePostal.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Fonction :");
		panel_4.add(lblNewLabel_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Réceptionniste", "Agent entretien","Gérant" }));
		panel_4.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Login :");
		panel_4.add(lblNewLabel_5);
		
		tfLogin = new JTextField();
		panel_4.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Mot de passe :");
		panel_4.add(lblNewLabel_6);
		
		passwordField = new JPasswordField();
		panel_4.add(passwordField);
	}

	public Personnel getPersonnel() {
		return nel;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){
			String nom = tfNom.getText();
			String prenom = tfPrenom.getText();
			double salaire = Double.parseDouble(tfSalaire.getText());
			int AArrivee = Integer.parseInt(tfAArrivee.getText());
			String fonction = comboBox.getSelectedItem().toString();
			String login = tfLogin.getText();
			String password = passwordField.getText();
			String adresse = tfAdresse.getText();
			String ville = tfVille.getText();
			int codePostal = Integer.parseInt(tfCodePostal.getText());
			
			f=fDAO.find(fDAO.renvoieId(fonction));
			
			boolean a = ModificationPerso(nom, prenom, adresse, codePostal, ville);
			boolean b = ModificationNel(salaire,AArrivee,login,password);
			
			if (a == true && b == true) {
				JOptionPane.showMessageDialog(btnValider, "Votre modification a bien été effectué", "Validation",
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
		}
		
	}
	
	private void clearTextField() {
		tfNom.setText("");
		tfPrenom.setText("");
		tfAArrivee.setText("");
		tfSalaire.setText("");
		tfAdresse.setText("");
		tfVille.setText("");
		tfCodePostal.setText("");
		tfLogin.setText("");
		passwordField.setText("");
	}

	public void preAffichage(Personnel perso){
		tfNom.setText(perso.getPersonne().getNom());
		tfPrenom.setText(perso.getPersonne().getPrenom());
		tfSalaire.setText(String.valueOf(perso.getSalaire()));
		tfAArrivee.setText(String.valueOf(perso.getAnnee_arrivee()));
		tfAdresse.setText(perso.getPersonne().getAdresse());
		tfVille.setText(perso.getPersonne().getVille());
		tfCodePostal.setText(String.valueOf(perso.getPersonne().getCode_postal()));
		//Faire une méthode pour sélectionner le bon item dans jcomboBox
		tfLogin.setText(perso.getLogin());
		
	}
	public boolean ModificationPerso(String nom, String prenom, String ville, int codePostal, String adresse){
		perso.setNom(nom);
		perso.setPrenom(prenom);
		perso.setVille(ville);
		perso.setCode_postal(codePostal);
		perso.setAdresse(adresse);

		boolean verif = pDAO.update(perso);
		return verif;
	}
	public boolean ModificationNel(double salaire, int AArrivee, String login, String password){
		nel.setAnnee_arrivee(AArrivee);
		nel.setFonction(f);
		nel.setPersonne(perso);
		nel.setSalaire(salaire);
		nel.setPassword(password);
		nel.setLogin(login);

		boolean verif = nDAO.update(nel);
		return verif;
	}

}
