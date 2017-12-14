package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import DAO.FonctionDAO;
import DAO.PersonneDAO;
import DAO.PersonnelDAO;
import model.Fonction;
import model.Personne;
import model.Personnel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;

public class JFAddPerso extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField textField_5;
	private JTextField textField_8;
	private JTextField textField_9;
	private JComboBox<String> comboBox;

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
	 */
	public JFAddPerso() {
		setTitle("Ajout d'un membre du personnel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		btnNewButton = new JButton("Valider");
		panel_2.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnNewButton_1 = new JButton("Annuler");
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Nom : ");
		panel_4.add(lblNewLabel);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom :");
		panel_4.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Adresse :");
		panel_4.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_4.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ville : ");
		panel_4.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		panel_4.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Code Postal : ");
		panel_4.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		panel_4.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date de naissance : ");
		panel_4.add(lblNewLabel_5);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_9 = new JLabel("J :");
		lblNewLabel_9.setPreferredSize(new Dimension(20, 14));
		panel_5.add(lblNewLabel_9);
		
		textField_5 = new JTextField();
		panel_5.add(textField_5);
		textField_5.setColumns(3);
		
		JLabel lblNewLabel_10 = new JLabel("M :");
		lblNewLabel_10.setPreferredSize(new Dimension(20, 14));
		panel_5.add(lblNewLabel_10);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_5.add(textField_8);
		textField_8.setColumns(3);
		
		JLabel lblNewLabel_11 = new JLabel("A :");
		panel_5.add(lblNewLabel_11);
		
		textField_9 = new JTextField();
		panel_5.add(textField_9);
		textField_9.setColumns(5);
		
		JLabel lblNewLabel_6 = new JLabel("Salaire : ");
		panel_4.add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		panel_4.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Ann\u00E9e d'arriv\u00E9e :");
		panel_4.add(lblNewLabel_7);
		
		textField_7 = new JTextField();
		panel_4.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Fonction : ");
		panel_4.add(lblNewLabel_8);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"R\u00E9ceptionniste", "Agent d entretien"}));
		panel_4.add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Personne perso=new Personne();
		PersonneDAO pDAO = new PersonneDAO();
		Personnel nel=new Personnel();
		PersonnelDAO nDAO=new PersonnelDAO();
		Fonction f=new Fonction();
		FonctionDAO fDAO=new FonctionDAO();
		if (e.getSource()==btnNewButton){
			String nom = textField.getText();
			String prenom = textField_1.getText();
			String adresse = textField_2.getText();
			String ville = textField_3.getText();
			int codePostal = Integer.parseInt(textField_4.getText());
			int annee = Integer.parseInt(textField_9.getText())-1900;
			int mois = Integer.parseInt(textField_8.getText())-1;
			int jour = Integer.parseInt(textField_5.getText());
			Date date = new Date(annee, mois, jour);
			double salaire = Double.parseDouble(textField_6.getText());
			int anneeArrivee = Integer.parseInt(textField_7.getText());
			String fonction = comboBox.getSelectedItem().toString();
			
			f=fDAO.find(fDAO.renvoieId(fonction));
			
			perso.setId_personne(pDAO.maxId());
			perso.setAdresse(adresse);
			perso.setNom(nom);
			perso.setPrenom(prenom);
			perso.setVille(ville);
			perso.setCode_postal(codePostal);
			perso.setDate_de_naissance(date);
			
			pDAO.create(perso);
			
			nel.setAnnee_arrivee(anneeArrivee);
			nel.setFonction(f);
			nel.setId_personnel(nDAO.maxId());
			nel.setPersonne(perso);
			nel.setSalaire(salaire);
			nel.setPassword("");
			
			
			nDAO.create(nel);
		}
		
	}

}
