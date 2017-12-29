package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.HotelDAO;
import model.Hotel;
import model.Personne;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class JFModifHotel extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField jtfNom;
	private JTextField jtfAdresse;
	private JTextField jtfVille;
	private JTextField jtfPays;
	private JTextField jtfCP;
	
	private Hotel hotel;
	private HotelDAO hDAO;
	private JComboBox comboBoxStanding;
	private JCheckBox checkboxWifi;
	private JButton btnValider;
	private String[] standing = new String[] {"0", "1", "2", "3", "4", "5"};
	private JTextField jtfProprio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFModifHotel frame = new JFModifHotel(new Hotel());
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
	public JFModifHotel(Hotel h) {
		this.hotel = h;
		this.hDAO = new HotelDAO();
		
		setTitle("Modification de l'hotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnValider = new JButton("Valider");
		panel.add(btnValider);
		btnValider.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(8, 2, 3, 3));
		
		JLabel lblNom = new JLabel("Nom");
		panel_1.add(lblNom);
		
		jtfNom = new JTextField();
		panel_1.add(jtfNom);
		jtfNom.setColumns(10);
		
		JLabel lblStanding = new JLabel("Standing");
		panel_1.add(lblStanding);
		
		comboBoxStanding = new JComboBox();
		comboBoxStanding.setModel(new DefaultComboBoxModel(standing));
		panel_1.add(comboBoxStanding);
		
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
		
		JLabel lblPays = new JLabel("Pays");
		panel_1.add(lblPays);
		
		jtfPays = new JTextField();
		panel_1.add(jtfPays);
		jtfPays.setColumns(10);
		
		JLabel lblCodePostal = new JLabel("Code Postal");
		panel_1.add(lblCodePostal);
		
		jtfCP = new JTextField();
		panel_1.add(jtfCP);
		jtfCP.setColumns(10);
		
		JLabel lblWifi = new JLabel("Wifi");
		panel_1.add(lblWifi);
		
		checkboxWifi = new JCheckBox("");
		checkboxWifi.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(checkboxWifi);
		
		JLabel lblPropritaire = new JLabel("Propri\u00E9taire");
		panel_1.add(lblPropritaire);
		
		jtfProprio = new JTextField();
		panel_1.add(jtfProprio);
		jtfProprio.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnValider){
			
			String nom = jtfNom.getText();
			int standing = Integer.parseInt(this.standing[comboBoxStanding.getSelectedIndex()]);
			String adresse = jtfAdresse.getText();
			String ville = jtfVille.getText();
			String pays = jtfPays.getText();
			int CP = Integer.parseInt(jtfCP.getText());
			boolean wifi = checkboxWifi.isSelected();
			String proprio = jtfProprio.getText();
			
			boolean a = ModificationHotel(nom, standing, adresse, ville, pays, CP, wifi, proprio);
			
			if (a == true) {
				JOptionPane.showMessageDialog(btnValider, "Votre modification a bien été effectué", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				
			} else {
				JOptionPane.showMessageDialog(btnValider, "Vous avez fait une erreur dans la saisie", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	private boolean ModificationHotel(String nom, int standing, String adresse, String ville, String pays, int cP,
			boolean wifi, String proprio) {
		hotel.setAdresse(adresse);
		hotel.setCode_postal(cP);
		hotel.setName(nom);
		hotel.setPays(pays);
		hotel.setProprietaire(proprio);
		hotel.setStanding(standing);
		hotel.setVille(ville);
		hotel.setWifi(wifi);
		
		return hDAO.update(hotel);
	}

	public void preAffichage(Hotel hotel){
		jtfNom.setText(hotel.getName());
		jtfAdresse.setText(hotel.getAdresse());
		jtfCP.setText(""+hotel.getCode_postal());
		jtfPays.setText(hotel.getPays());
		jtfVille.setText(hotel.getVille());
		jtfProprio.setText(hotel.getProprietaire());
		comboBoxStanding.setSelectedIndex(hotel.getStanding());
		checkboxWifi.setSelected(hotel.isWifi());
	}


}
