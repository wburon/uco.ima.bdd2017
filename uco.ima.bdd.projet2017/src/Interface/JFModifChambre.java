package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.ChambreDAO;
import DAO.CommunicanteDAO;
import DAO.Type_ChambreDAO;
import model.Chambre;
import model.Type_Chambre;

@SuppressWarnings("serial")
public class JFModifChambre extends JFrame implements ActionListener{
	private JTextField jtfNumChambre;
	private JTextField jtfTarif;
	private JButton btnUpdate;
	private JCheckBox cbTele, cbHandicap, cbLibre, cbCommunicante, cbAnimaux;
	private JComboBox comboBoxTypeChambre;
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
	private Chambre CurrentChambre;
	private CommunicanteDAO commDAO = new CommunicanteDAO();
	private JPanel contentPane;

	public Chambre getCurrentChambre() {
		return CurrentChambre;
	}

	public void setCurrentChambre(Chambre currentChambre) {
		CurrentChambre = currentChambre;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFModifChambre frame = new JFModifChambre(new Chambre());
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
	public JFModifChambre(Chambre c) {
		this.CurrentChambre = c;
		
		setTitle("Modification d'une chambre");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnUpdate);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
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
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnUpdate){
			if(verificationDonnée()){
				
				
				if(cbCommunicante.isSelected() && CurrentChambre.isCommunicante()==false){
					new JFNowCommunicante(CurrentChambre.getId_chambre());
				}else if(!cbCommunicante.isSelected() && CurrentChambre.isCommunicante()){
					JOptionPane.showMessageDialog(btnUpdate, "Si cette chambre n'est plus communicante, nous changeons automatiquement cette dernière !", "Information",JOptionPane.INFORMATION_MESSAGE);
					Chambre c = chambreDao.find(commDAO.findCommunicante(CurrentChambre.getId_chambre()));
					c.setCommunicante(false);
					chambreDao.update(c);
				}
				else{
					updateChambre();
				}

			}
			else
				System.out.println("TRY AGAIN");
			clear();
		}
	}
	
	private void clear() {
		
		
	}

	private boolean verificationDonnée() {
		ChambreDAO c = new ChambreDAO();
		if(c.NumChambreExisteDeja(jtfNumChambre.getText(), CurrentChambre.getId_chambre(), CurrentChambre.getHotel().getId_hotel()) == false)
			return false;
		if(Double.parseDouble(jtfTarif.getText()) <= 0 )
			return false;
		
		return true;
				
	}
	
	public void preAffichage(Chambre chambre){
		jtfNumChambre.setText(""+chambre.getNumero_chambre());
		jtfTarif.setText(""+chambre.getTarif());
		cbAnimaux.setSelected(chambre.isAnimaux());
		cbCommunicante.setSelected(chambre.isCommunicante());
		cbHandicap.setSelected(chambre.isHandicap());
		cbLibre.setSelected(chambre.isLibre());
		cbTele.setSelected(chambre.isTele());
		comboBoxTypeChambre.setSelectedIndex(chambre.getType_chambre().getId_type_chambre()-1);
	}
	
	public boolean updateChambre() {
		Chambre chambre = new Chambre();
		Type_ChambreDAO tDAO = new Type_ChambreDAO();
		
		chambre.setAnimaux(cbAnimaux.isSelected());
			chambre.setCommunicante(cbCommunicante.isSelected());
			chambre.setHandicap(cbHandicap.isSelected());
			chambre.setHotel(CurrentChambre.getHotel());
			chambre.setLibre(CurrentChambre.isLibre());
			chambre.setNumero_chambre(Integer.parseInt(jtfNumChambre.getText()));
			chambre.setTarif(Double.parseDouble(jtfTarif.getText()));
			chambre.setTele(cbTele.isSelected());
			Type_Chambre tc = tDAO.find(tDAO.findId(comboBoxTypeChambre.getSelectedItem().toString()));
			chambre.setType_chambre(tc);
			chambre.setId_chambre(CurrentChambre.getId_chambre());
		 
			this.setCurrentChambre(chambre);
			return chambreDao.update(chambre);

	}

}
