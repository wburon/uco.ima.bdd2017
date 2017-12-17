package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import DAO.ChambreDAO;
import DAO.HotelDAO;
import DAO.Type_ChambreDAO;
import model.Chambre;

public class JPAddChambre extends JPanel implements ActionListener{
	private JTextField jtfIdHotel;
	private JTextField jtfNumChambre;
	private JTextField jtfTarif;
	private JComboBox comboBoxTypeChambre;
	private JCheckBox cbAnimaux;
	private JCheckBox cbComm;
	private JCheckBox cbHandi;
	private JCheckBox cbTele;
	private JButton btnAjouter;

	/**
	 * Create the panel.
	 */
	public JPAddChambre() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblAjouterUneChambre = new JLabel("Ajouter une chambre ");
		lblAjouterUneChambre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblAjouterUneChambre);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		add(panel_1, BorderLayout.SOUTH);
		
		btnAjouter = new JButton("AJOUTER");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnAjouter);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel label = new JLabel("ID Hotel");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		
		jtfIdHotel = new JTextField();
		jtfIdHotel.setColumns(10);
		panel_2.add(jtfIdHotel);
		
		JLabel label_1 = new JLabel("Num\u00E9ro de chambre");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_1);
		
		jtfNumChambre = new JTextField();
		jtfNumChambre.setColumns(10);
		panel_2.add(jtfNumChambre);
		
		JLabel label_2 = new JLabel("T\u00E9l\u00E9vision");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_2);
		
		cbTele = new JCheckBox("");
		panel_2.add(cbTele);
		
		JLabel label_3 = new JLabel("Handicap");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_3);
		
		cbHandi = new JCheckBox("");
		panel_2.add(cbHandi);
		
		JLabel label_4 = new JLabel("Communicante");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_4);
		
		cbComm = new JCheckBox("");
		panel_2.add(cbComm);
		
		JLabel label_5 = new JLabel("Animaux");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_5);
		
		cbAnimaux = new JCheckBox("");
		panel_2.add(cbAnimaux);
		
		JLabel lblTypeDeChambre = new JLabel("Type de chambre");
		lblTypeDeChambre.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblTypeDeChambre);
		
		comboBoxTypeChambre = new JComboBox();
		panel_2.add(comboBoxTypeChambre);
		
		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblTarif);
		
		jtfTarif = new JTextField();
		panel_2.add(jtfTarif);
		jtfTarif.setColumns(10);
		
		btnAjouter.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnAjouter){
			Chambre chambre = new Chambre();
			HotelDAO hDAO = new HotelDAO();
			Type_ChambreDAO tDAO = new Type_ChambreDAO();
			ChambreDAO cDAO = new ChambreDAO();
			chambre.setAnimaux(cbAnimaux.isSelected());
			chambre.setCommunicante(cbComm.isSelected());
			chambre.setHandicap(cbHandi.isSelected());
			chambre.setHotel(hDAO.find(Integer.parseInt(jtfIdHotel.getText())));
			chambre.setLibre(true);
			chambre.setNumero_chambre(Integer.parseInt(jtfNumChambre.getText()));
			chambre.setTarif(Double.parseDouble(jtfTarif.getText()));
			chambre.setTele(cbTele.isSelected());
			chambre.setType_chambre(tDAO.find(comboBoxTypeChambre.getSelectedIndex()));
			if(verificationDonnée())
				cDAO.create(chambre);
			else
				System.out.println("TRY AGAIN");
		}
		
	}
	
	private boolean verificationDonnée() {
		ChambreDAO c = new ChambreDAO();
		HotelDAO hDAO = new HotelDAO();
		if(c.NumChambreExisteDeja(jtfNumChambre.getText(), jtfIdHotel) == false)
			return false;
		if(Double.parseDouble(jtfTarif.getText()) <= 0 )
			return false;
		if(hDAO.find(Integer.parseInt(jtfIdHotel.getText())) == null)
			return false;
		
		return true;
				
	}

}
