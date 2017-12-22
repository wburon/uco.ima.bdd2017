package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Personnel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
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


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFModifPerso frame = new JFModifPerso();
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
	public JFModifPerso() {
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
		
		JLabel lblNewLabel_4 = new JLabel("Fonction :");
		panel_4.add(lblNewLabel_4);
		
		comboBox = new JComboBox();
		comboBox.addItem("Réceptionniste");
		comboBox.addItem("Femme de ménage");
		panel_4.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Login :");
		panel_4.add(lblNewLabel_5);
		
		tfLogin = new JTextField();
		panel_4.add(tfLogin);
		tfLogin.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Password :");
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
			
			
		}
		
	}
	
	public void preAffichage(Personnel perso){
		tfNom.setText(perso.getPersonne().getNom());
		tfPrenom.setText(perso.getPersonne().getPrenom());
		tfSalaire.setText(String.valueOf(perso.getSalaire()));
		tfAArrivee.setText(String.valueOf(perso.getAnnee_arrivee()));
		//Faire une méthode pour sélectionner le bon item dans jcomboBox
		tfLogin.setText(perso.getLogin());
		
	}

}
