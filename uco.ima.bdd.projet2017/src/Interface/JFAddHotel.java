package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.HotelDAO;
import model.Hotel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JFAddHotel extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField jtfName;
	private JTextField jtfAdresse;
	private JTextField jtfProprio;
	private JTextField jtfVille;
	private JTextField jtfCP;
	private JTextField jtfPays;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JCheckBox checkBox;
	private JComboBox comboBox;
	private HotelDAO hDAO = new HotelDAO();
	private Hotel h = new Hotel();
	private String[] item;
	private JLabel lblNbChambre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFAddHotel frame = new JFAddHotel();
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
	public JFAddHotel() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 60));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNom);
		
		jtfName = new JTextField();
		jtfName.setPreferredSize(new Dimension(20, 30));
		panel.add(jtfName);
		jtfName.setColumns(20);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 4, 5, 5));
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblAdresse);
		
		jtfAdresse = new JTextField();
		panel_1.add(jtfAdresse);
		jtfAdresse.setColumns(10);
		
		JLabel lblPropritaire = new JLabel("Propri\u00E9taire");
		lblPropritaire.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPropritaire);
		
		jtfProprio = new JTextField();
		panel_1.add(jtfProprio);
		jtfProprio.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		lblVille.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblVille);
		
		jtfVille = new JTextField();
		panel_1.add(jtfVille);
		jtfVille.setColumns(10);
		
		JLabel lblStanding = new JLabel("Standing");
		lblStanding.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblStanding);
		
		comboBox = new JComboBox();
		item = new String[] {"1", "2", "3", "4", "5"};
		comboBox.setModel(new DefaultComboBoxModel(item));
		panel_1.add(comboBox);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblCodePostal);
		
		jtfCP = new JTextField();
		panel_1.add(jtfCP);
		jtfCP.setColumns(10);
		
		JLabel lblNombreDeChambre = new JLabel("Nombre de chambre");
		lblNombreDeChambre.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNombreDeChambre);
		
		lblNbChambre = new JLabel("0");
		panel_1.add(lblNbChambre);
		
		JLabel lblPays = new JLabel("Pays");
		lblPays.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblPays);
		
		jtfPays = new JTextField();
		panel_1.add(jtfPays);
		jtfPays.setColumns(10);
		
		JLabel lblWifi = new JLabel("Wifi");
		lblWifi.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblWifi);
		
		checkBox = new JCheckBox("");
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		btnValider = new JButton("Valider");
		panel_2.add(btnValider);
		btnValider.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		panel_2.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnValider){
			String nom = jtfName.getText();
			String adresse = jtfAdresse.getText();
			String ville = jtfVille.getText();
			int CP = Integer.parseInt(jtfCP.getText());
			String pays = jtfPays.getText();
			String proprietaire = jtfProprio.getText();
			int standing = Integer.parseInt(item[comboBox.getSelectedIndex()]);
			int nb_chambre = 0;
			boolean wifi = checkBox.isSelected();
			
			boolean a = creationHotel(adresse, CP, nom, nb_chambre, pays, proprietaire, standing, ville, wifi );
			
			if (a==true){
				JOptionPane.showMessageDialog(btnValider, "Votre ajout a bien été effectué", "Validation", JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
			}else{
				JOptionPane.showMessageDialog(btnValider, "Vous avez fait une erreur dans la saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == btnAnnuler){
			this.dispose();
		}
		
	}
	
	private void clearTextField() {
		jtfAdresse.setText("");
		jtfCP.setText("");
		jtfName.setText("");
		jtfPays.setText("");
		jtfProprio.setText("");
		jtfVille.setText("");
		
	}

	/*
	 * Permet de créer une nouvel hotel dans la base de données et de renvoyer un booléen pour savoir si l'opération a été réussi
	 */
	public boolean creationHotel(String adresse, int code_postal, String nom, int nb_chambre_total, String pays, String proprietaire, int standing, String ville, boolean wifi ){
		h.setId_hotel(hDAO.maxId());
		h.setAdresse(adresse);
		h.setCode_postal(code_postal);
		h.setName(nom);
		h.setNb_chambre_total(nb_chambre_total);
		h.setPays(pays);
		h.setProprietaire(proprietaire);
		h.setStanding(standing);
		h.setVille(ville);
		h.setWifi(wifi);

		boolean verif = hDAO.create(h);
		return verif;
	}

	/*
	 * Renvoie le membre du personnel enregistrer dans cette classe
	 */
	public Hotel getHotel(){
		return h;
	}

}
