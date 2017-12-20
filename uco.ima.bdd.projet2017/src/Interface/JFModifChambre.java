package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import DAO.ChambreDAO;
import DAO.Type_ChambreDAO;
import model.Chambre;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class JFModifChambre extends JPanel implements ActionListener{
	private JTextField jtfNumChambre;
	private JTextField jtfTarif;
	private JButton btnUpdate, btnOK;
	private JCheckBox cbTele, cbHandicap, cbLibre, cbCommunicante, cbAnimaux;
	private JComboBox comboBoxTypeChambre;
	private JTextField idhotel;
	private JTextField idchambre;
	private JLabel lblNumroDeChambre;
	private JLabel lblTarif;
	private JLabel lblTlevision;
	private JLabel lblHandicap;
	private JLabel lblLibre;
	private JLabel lblCommunicante;
	private JLabel lblAnimaux;
	private JLabel lblTypeDeChambre;
	private JPanel panel_2;
	private JPanel panel_1;
	private ChambreDAO chambreDao = new ChambreDAO();
	private Chambre chambre = new Chambre();
	private int idCurrentChambre;

	/**
	 * Create the panel.
	 */
	public JFModifChambre(int id_chambre) {
		this.idCurrentChambre = id_chambre;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblIdhotel = new JLabel("id_Hotel : ");
		lblIdhotel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblIdhotel);
		
		idhotel = new JTextField();
		panel.add(idhotel);
		idhotel.setColumns(10);
		
		JLabel lblIdchambre = new JLabel("Id_chambre : ");
		lblIdchambre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblIdchambre);
		
		idchambre = new JTextField();
		panel.add(idchambre);
		idchambre.setColumns(10);
		
		btnOK = new JButton("OK");
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(btnOK);
		
		panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setPreferredSize(new Dimension(10, 50));
		add(panel_1, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnUpdate);
		
		panel_2 = new JPanel();
		panel_2.setVisible(false);
		add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 40));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));
		
		lblNumroDeChambre = new JLabel("Num\u00E9ro de chambre");
		lblNumroDeChambre.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNumroDeChambre);
		
		jtfNumChambre = new JTextField();
		panel_3.add(jtfNumChambre);
		jtfNumChambre.setColumns(10);
		
		lblTarif = new JLabel("Tarif");
		lblTarif.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblTarif);
		
		jtfTarif = new JTextField();
		panel_3.add(jtfTarif);
		jtfTarif.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(3, 4, 0, 0));
		
		lblTlevision = new JLabel("T\u00E9levision");
		lblTlevision.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTlevision);
		
		cbTele = new JCheckBox("");
		panel_4.add(cbTele);
		
		lblHandicap = new JLabel("Handicap");
		lblHandicap.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblHandicap);
		
		cbHandicap = new JCheckBox("");
		panel_4.add(cbHandicap);
		
		lblLibre = new JLabel("Libre");
		lblLibre.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblLibre);
		
		cbLibre = new JCheckBox("");
		panel_4.add(cbLibre);
		
		lblCommunicante = new JLabel("Communicante");
		lblCommunicante.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblCommunicante);
		
		cbCommunicante = new JCheckBox("");
		panel_4.add(cbCommunicante);
		
		lblAnimaux = new JLabel("Animaux");
		lblAnimaux.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblAnimaux);
		
		cbAnimaux = new JCheckBox("");
		panel_4.add(cbAnimaux);
		
		lblTypeDeChambre = new JLabel("Type de chambre");
		lblTypeDeChambre.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTypeDeChambre);
		
		comboBoxTypeChambre = new JComboBox();
		Type_ChambreDAO type = new Type_ChambreDAO();
		comboBoxTypeChambre.setModel(new DefaultComboBoxModel(type.listNameTypeChambre()));
		panel_4.add(comboBoxTypeChambre);
		
		btnUpdate.addActionListener(this);
		btnOK.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnUpdate){
			if(verificationDonnée()){
				ChambreDAO chambreDao = new ChambreDAO();
				Chambre chambre = new Chambre();
				if(cbCommunicante.isSelected()){
					JFNowCommunicante NowC = new JFNowCommunicante(idCurrentChambre);
				}
			}
			else
				System.out.println("TRY AGAIN");
			clear();
		}
		else if(arg0.getSource() == btnOK){
			panel_1.setVisible(true);
			panel_2.setVisible(true);
			chambre = chambreDao.find(Integer.parseInt(idchambre.getText()));
			jtfNumChambre.setText(""+chambre.getNumero_chambre());
			jtfTarif.setText(""+chambre.getTarif());
			cbAnimaux.setSelected(chambre.isAnimaux());
			cbCommunicante.setSelected(chambre.isCommunicante());
			cbHandicap.setSelected(chambre.isHandicap());
			cbLibre.setSelected(chambre.isLibre());
			cbTele.setSelected(chambre.isTele());
			comboBoxTypeChambre.setSelectedIndex(chambre.getType_chambre().getId_type_chambre());
		}
		
	}

	private void clear() {
		
		
	}

	private boolean verificationDonnée() {
		ChambreDAO c = new ChambreDAO();
		if(c.NumChambreExisteDeja(jtfNumChambre.getText(), idchambre, idhotel) == false)
			return false;
		if(Double.parseDouble(jtfTarif.getText()) <= 0 )
			return false;
		
		return true;
				
	}

}
