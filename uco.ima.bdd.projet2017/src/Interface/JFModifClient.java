package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.ClientDAO;
import DAO.PersonneDAO;
import model.Client;
import model.Personne;

public class JFModifClient extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JTextField tfNom;
	private JTextField tfPrenom;
	private JButton btnValider;
	private JButton btnAnnuler;
	
	private Personne perso;
	private PersonneDAO pDAO;
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
					JFModifClient frame = new JFModifClient(new Client());
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
	public JFModifClient(Client client) {
		this.perso = client.getPersonne();
		pDAO=new PersonneDAO();
		
		
		setTitle("Modification d'un client");
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
		panel_4.setLayout(new GridLayout(5, 2, 0, 0));
		
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
		
	}
	
public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnValider){
			String nom = tfNom.getText();
			String prenom = tfPrenom.getText();
			String adresse = tfAdresse.getText();
			String ville = tfVille.getText();
			int codePostal = Integer.parseInt(tfCodePostal.getText());
			
			boolean a = ModificationPerso(nom, prenom, adresse, codePostal, ville);
			
			if (a == true) {
				JOptionPane.showMessageDialog(btnValider, "Votre modification a bien été effectué", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
				this.dispose();
				
			} else {
				JOptionPane.showMessageDialog(btnValider, "Vous avez fait une erreur dans la saisie", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private void clearTextField() {
		tfNom.setText("");
		tfPrenom.setText("");
		tfAdresse.setText("");
		tfVille.setText("");
		tfCodePostal.setText("");
	}

	public void preAffichage(Personne perso){
		tfNom.setText(perso.getNom());
		tfPrenom.setText(perso.getPrenom());
		tfAdresse.setText(perso.getAdresse());
		tfVille.setText(perso.getVille());
		tfCodePostal.setText(String.valueOf(perso.getCode_postal()));
		
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

}
