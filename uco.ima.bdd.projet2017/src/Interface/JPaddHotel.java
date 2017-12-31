package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DAO.HotelDAO;
import model.Hotel;

@SuppressWarnings("serial")
public class JPaddHotel extends JPanel implements ActionListener{
	private JTextField jtfAdresse;
	private JTextField jtfVille;
	private JTextField jtfCP;
	private JTextField jtfPays;

	private JComboBox comboBox;
	
	private JButton btnSuivant;
	private JButton btnAnnuler;
	
	private JCheckBox checkBox;
	private final JLabel lblRentrezLesDonnes = new JLabel("Rentrez les donn\u00E9es de votre h\u00F4tel");
	private JTextField jtfName;
	
	private Hotel h;
	private HotelDAO hDAO;
	
	private String[] item; 
	private JFCréationCompte JFCreationCompte;
	/**
	 * Create the panel.
	 */
	public JPaddHotel(JFCréationCompte JFCreationCompte) {
		this.JFCreationCompte=JFCreationCompte;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 60));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		lblRentrezLesDonnes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_3.add(lblRentrezLesDonnes);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_4.add(lblNom);
		
		jtfName = new JTextField();
		panel_4.add(jtfName);
		jtfName.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(4, 4, 5, 5));
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblAdresse);
		
		jtfAdresse = new JTextField();
		panel_1.add(jtfAdresse);
		jtfAdresse.setColumns(10);
		
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
		
		JLabel lblNbChambre = new JLabel("0");
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
		panel_2.setPreferredSize(new Dimension(10, 30));add(panel_2, BorderLayout.SOUTH);
		
		btnSuivant = new JButton("Suivant >");
		panel_2.add(btnSuivant);
		btnSuivant.addActionListener(this);
		
		btnAnnuler = new JButton("Annuler");
		panel_2.add(btnAnnuler);
		btnAnnuler.addActionListener(this);
		
		h = new Hotel();
		hDAO = new HotelDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnSuivant){
			String nom = jtfName.getText();
			String adresse = jtfAdresse.getText();
			String ville = jtfVille.getText();
			int CP = Integer.parseInt(jtfCP.getText());
			String pays = jtfPays.getText();
			int standing = Integer.parseInt(item[comboBox.getSelectedIndex()]);
			int nb_chambre = 0;
			boolean wifi = checkBox.isSelected();
			
			boolean a = creationHotel(adresse, CP, nom, nb_chambre, pays, standing, ville, wifi );
			
			if (a==true){
				JOptionPane.showMessageDialog(btnSuivant, "Votre hotel est bien enregistré", "Validation", JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
				JFCreationCompte.setHotel(h);
				//Ouvre le JPanel suivant
				JFCreationCompte.setContentPane(JFCreationCompte.getJPaddGerant());
				JFCreationCompte.getJPaddGerant().repaint();
				JFCreationCompte.getJPaddGerant().revalidate();
			}else{
				JOptionPane.showMessageDialog(btnSuivant, "Vous avez fait une erreur dans la saisie", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == btnAnnuler){
			JFCreationCompte.dispose();
		}
	}

	/**
	 * Vide les TextFields
	 */
	private void clearTextField() {
		jtfAdresse.setText("");
		jtfCP.setText("");
		jtfName.setText("");
		jtfPays.setText("");
		jtfVille.setText("");
		
	}
	/**
	 * créer l'objet hotel dans la base de données
	 * @param adresse
	 * @param code_postal
	 * @param nom
	 * @param nb_chambre_total
	 * @param pays
	 * @param standing
	 * @param ville
	 * @param wifi
	 * @return si ça réussi ou nous
	 */
	public boolean creationHotel(String adresse, int code_postal, String nom, int nb_chambre_total, String pays, int standing, String ville, boolean wifi ){
		h.setId_hotel(hDAO.maxId());
		h.setAdresse(adresse);
		h.setCode_postal(code_postal);
		h.setName(nom);
		h.setNb_chambre_total(nb_chambre_total);
		h.setPays(pays);
		h.setStanding(standing);
		h.setVille(ville);
		h.setWifi(wifi);
		h.setProprietaire("");

		boolean verif = hDAO.create(h);
		return verif;
	}

}
