package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import DAO.ChambreDAO;
import DAO.HotelDAO;
import DAO.Type_ChambreDAO;
import model.Chambre;
import model.Table_Chambre;
import model.Type_Chambre;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class JFAddChambre extends JFrame implements ActionListener{
	private JTextField jtfNumChambre;
	private JTextField jtfTarif;
	private JComboBox comboBoxTypeChambre;
	private JCheckBox cbAnimaux;
	private JCheckBox cbComm;
	private JCheckBox cbHandi;
	private JCheckBox cbTele;
	private JButton btnAjouter;
	private int id_hotel;
	private Type_ChambreDAO tcDAO = new Type_ChambreDAO();
	private Chambre c = new Chambre();
	private ChambreDAO cDAO = new ChambreDAO();

	public JCheckBox getCbComm() {
		return cbComm;
	}

	public void setCbComm(JCheckBox cbComm) {
		this.cbComm = cbComm;
	}

	public Chambre getC() {
		return c;
	}

	public void setC(Chambre c) {
		this.c = c;
	}

	/**
	 * Create the panel.
	 */
	public JFAddChambre(int id_hotel) {
		this.id_hotel = id_hotel;
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 596, 362);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblAjouterUneChambre = new JLabel("Ajouter une chambre ");
		lblAjouterUneChambre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblAjouterUneChambre);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnAjouter = new JButton("AJOUTER");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnAjouter);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(4, 4, 0, 0));
		
		JLabel label = new JLabel("ID Hotel");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		
		JLabel label_6 = new JLabel(""+id_hotel);
		panel_2.add(label_6);
		
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
		comboBoxTypeChambre.setModel(new DefaultComboBoxModel(tcDAO.listNameTypeChambre()));
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
			
			chambre.setAnimaux(cbAnimaux.isSelected());
			chambre.setCommunicante(cbComm.isSelected());
			chambre.setHandicap(cbHandi.isSelected());
			chambre.setHotel(hDAO.find(id_hotel));
			chambre.setLibre(true);
			chambre.setNumero_chambre(Integer.parseInt(jtfNumChambre.getText()));
			chambre.setTarif(Double.parseDouble(jtfTarif.getText()));
			chambre.setTele(cbTele.isSelected());
			Type_Chambre tc = tDAO.find(tDAO.findId(comboBoxTypeChambre.getSelectedItem().toString()));
			chambre.setType_chambre(tc);
			chambre.setId_chambre(cDAO.maxId());
			if(verificationDonnée()){
				cDAO.create(chambre);
				JOptionPane.showMessageDialog(btnAjouter, "Votre ajout a bien été effectué", "Validation",
						JOptionPane.INFORMATION_MESSAGE);
				clearTextField();
//				tChambre.addChambre(chambre);
				this.c = chambre;
				siCommunicante(cDAO.maxId()-1);
			}else
				System.out.println("TRY AGAIN");
			
			
		}
		
	}
	
	private void siCommunicante(int id_chambre) {
		if(cDAO.find(id_chambre).isCommunicante() && cbComm.isEnabled()){
			JFcreateChambreComm cc = new JFcreateChambreComm(id_chambre);
			cc.setVisible(true);
		}
		
	}

	private void clearTextField() {
		jtfNumChambre.setText("");
		jtfTarif.setText("");
		cbAnimaux.setSelected(false);
		cbComm.setSelected(false);
		cbHandi.setSelected(false);
		cbTele.setSelected(false);
	}

	private boolean verificationDonnée() {
		ChambreDAO c = new ChambreDAO();
		if(c.NumChambreExisteDeja(jtfNumChambre.getText(), id_hotel) == false)
			return false;
		if(Double.parseDouble(jtfTarif.getText()) <= 0 )
			return false;
		
		return true;
				
	}

	/*
	 * Renvoie le membre du personnel enregistrer dans cette classe
	 */
	public Chambre getChambre(){
		return c;
	}
	
}
