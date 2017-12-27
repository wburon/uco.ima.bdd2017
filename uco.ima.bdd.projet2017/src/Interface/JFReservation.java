package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ChambreDAO;
import DAO.Type_ChambreDAO;
import model.Chambre;
import model.Client;
import model.Name;
import model.Reservation;
import model.Table_Chambre;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Rectangle;

public class JFReservation extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtJjmmaaaa;
	private JTextField txtJjmmaaaa_1;
	private JTextField textField_2;
	private JTable table;
	private JButton btnRecherche;
	private int currentIdHotel;
	private ChambreDAO cDAO = new ChambreDAO();
	private JComboBox comboBoxTC;
	private Type_ChambreDAO tDAO = new Type_ChambreDAO();
	private JTextField textField;
	private JComboBox comboBoxH;
	private JComboBox comboBoxA;
	private JComboBox comboBoxT;
	private JComboBox comboBoxC;
	private String[] comboBoxBoolean = new String[] { "OUI", "NON", "PEU_IMPORTE" };
	private String[] comboBoxType = new String[] { "", "Suite Royal", "Simple", "Double", "Famille" };
	private JButton btnReserver;
	private ArrayList<Reservation> listReservation = new ArrayList<Reservation>();
	private Table_Chambre tChambre;
	private JButton btnFinaliserLaReservation;
	private int s;
	private SimpleDateFormat format;
	private Date debut;
	private Date fin; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFReservation frame = new JFReservation(1);
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
	public JFReservation(int id_hotel) {
		this.currentIdHotel = id_hotel;

		setTitle("Reserver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblTrouverUnLogement = new JLabel("Trouver un logement");
		panel.add(lblTrouverUnLogement);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 60));
		panel_1.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);

		JLabel lblDbutDuSjour = new JLabel("D\u00E9but du s\u00E9jour ");
		panel_3.add(lblDbutDuSjour);

		txtJjmmaaaa = new JTextField();
		txtJjmmaaaa.setText("dd-MM-yyyy");
		panel_3.add(txtJjmmaaaa);
		txtJjmmaaaa.setColumns(10);

		JLabel lblFinDuSjour = new JLabel("Fin du s\u00E9jour ");
		panel_3.add(lblFinDuSjour);

		txtJjmmaaaa_1 = new JTextField();
		txtJjmmaaaa_1.setText("dd-MM-yyyy");
		panel_3.add(txtJjmmaaaa_1);
		txtJjmmaaaa_1.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);

		JLabel lblPays = new JLabel("Nombre de chambre encore a reserver :");
		panel_4.add(lblPays);

		textField_2 = new JTextField();
		panel_4.add(textField_2);
		textField_2.setColumns(10);

		btnReserver = new JButton("Reserver");
		panel_4.add(btnReserver);
		
		btnFinaliserLaReservation = new JButton("Finaliser la reservation");
		panel_4.add(btnFinaliserLaReservation);

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setPreferredSize(new Dimension(230, 10));
		panel_5.add(panel_6, BorderLayout.WEST);
		panel_6.setLayout(new GridLayout(7, 2, 0, 0));

		JLabel lblTypeDeChambre = new JLabel("Type de chambre");
		panel_6.add(lblTypeDeChambre);

		comboBoxTC = new JComboBox();
		comboBoxTC.setModel(new DefaultComboBoxModel(comboBoxType));
		panel_6.add(comboBoxTC);

		JLabel lblCommunicante = new JLabel("Communicante");
		panel_6.add(lblCommunicante);

		comboBoxC = new JComboBox();
		comboBoxC.setModel(new DefaultComboBoxModel(comboBoxBoolean));
		panel_6.add(comboBoxC);

		JLabel lblTelevision = new JLabel("Television");
		panel_6.add(lblTelevision);

		comboBoxT = new JComboBox();
		comboBoxT.setModel(new DefaultComboBoxModel(comboBoxBoolean));
		panel_6.add(comboBoxT);

		JLabel lblAnimaux = new JLabel("Animaux");
		panel_6.add(lblAnimaux);

		comboBoxA = new JComboBox();
		comboBoxA.setModel(new DefaultComboBoxModel(comboBoxBoolean));
		panel_6.add(comboBoxA);

		JLabel lblHandicap = new JLabel("Handicap");
		panel_6.add(lblHandicap);

		comboBoxH = new JComboBox();
		comboBoxH.setBounds(new Rectangle(5, 5, 5, 5));
		comboBoxH.setModel(new DefaultComboBoxModel(comboBoxBoolean));
		panel_6.add(comboBoxH);

		JLabel lblTarifMaximum = new JLabel("Tarif Maximum");
		panel_6.add(lblTarifMaximum);

		textField = new JTextField();
		panel_6.add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("");
		panel_6.add(label);

		btnRecherche = new JButton("Recherche");
		panel_6.add(btnRecherche);

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new GridLayout(1, 1, 0, 0));

		table = new JTable();
		panel_7.add(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRecherche) {
			try {
				tChambre = new Table_Chambre(this.currentIdHotel);
				format = new SimpleDateFormat("dd-MM-yyyy");
				debut = (Date) format.parse(txtJjmmaaaa.getText());
				fin = (Date) format.parse(txtJjmmaaaa_1.getText());
				tChambre.setListChambre(cDAO.findPerfect(tDAO.find(comboBoxTC.getSelectedIndex() + 2),
						Name.getName(comboBoxBoolean[comboBoxA.getSelectedIndex()]),
						Name.getName(comboBoxBoolean[comboBoxC.getSelectedIndex()]),
						Name.getName(comboBoxBoolean[comboBoxH.getSelectedIndex()]),
						Name.getName(comboBoxBoolean[comboBoxT.getSelectedIndex()]),
						Double.parseDouble(textField.getText()), currentIdHotel, debut, fin));
				table.setModel(tChambre);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == btnReserver){
			textField_2.setEnabled(false);
			s = table.getSelectedRow();
			listReservation.add(createReservation(tChambre.getChambre(s),debut, fin));
		}else if(e.getSource() == btnFinaliserLaReservation){
			JFClientReservation newClient = new JFClientReservation(listReservation);
			newClient.setVisible(true);
		}
	}
	
	private Reservation createReservation(Chambre c, Date debut, Date fin){
		Reservation resa =new Reservation();
		resa.setChambre(c);
		resa.setDate_debut(debut);
		resa.setDate_fin(fin);
		resa.setHotel(c.getHotel());
		return resa;
	}
	
	

}
