package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ChambreDAO;
import DAO.CommunicanteDAO;
import model.Chambre;
import model.Communicante;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JFNowCommunicante extends JFrame implements ActionListener{

	private JPanel contentPane;
	private ChambreDAO cDAO = new ChambreDAO();
	private JButton btnCellel;
	private JButton btnCelleci;
	private JLabel lblIdPlus;
	private JLabel lblIdMoins;
	private JLabel lblPlus;
	private JLabel lblMoins;
	private Chambre c1;
	private Chambre[] cTab;
	private Chambre chambre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFNowCommunicante frame = new JFNowCommunicante(1);
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
	public JFNowCommunicante(int id_chambre) {
		this.chambre = cDAO.find(id_chambre);
		this.cTab = cDAO.chambresVoisines(id_chambre); 
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel, BorderLayout.NORTH);
		
		c1 = cDAO.find(id_chambre);
		JLabel lblQuelleChambreCommunique = new JLabel("Quelle chambre communique maintenant avec la chambre "+c1.getNumero_chambre());
		lblQuelleChambreCommunique.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblQuelleChambreCommunique);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(30, 10));
		contentPane.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 30));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(30, 10));
		contentPane.add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panelOuest = new JPanel();
		panel_4.add(panelOuest);
		panelOuest.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panelEst = new JPanel();
		panel_4.add(panelEst);
		panelEst.setLayout(new GridLayout(3, 1, 0, 0));
		
		cTab = cDAO.chambresVoisines(id_chambre); 
		if(cTab[0]!=null)
			if(!cTab[0].isCommunicante()){
			lblMoins = new JLabel(""+cTab[0].getNumero_chambre());
			lblMoins.setHorizontalAlignment(SwingConstants.CENTER);
			panelOuest.add(lblMoins);
			
		
			lblIdMoins = new JLabel("ID : "+cTab[0].getId_chambre());
			lblIdMoins.setHorizontalAlignment(SwingConstants.CENTER);
			panelOuest.add(lblIdMoins);
		
			btnCelleci = new JButton("Celle-ci");
			panelOuest.add(btnCelleci);
			btnCelleci.addActionListener(this);
		}
		if(cTab[1]!=null)
			if(!cTab[1].isCommunicante()){
			lblPlus = new JLabel(""+cTab[1].getNumero_chambre());
			lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
			panelEst.add(lblPlus);
			
			lblIdPlus = new JLabel("ID : "+cTab[1].getId_chambre());
			lblIdPlus.setHorizontalAlignment(SwingConstants.CENTER);
			panelEst.add(lblIdPlus);
			
			btnCellel = new JButton("Celle-l\u00E0");
			panelEst.add(btnCellel);
			btnCellel.addActionListener(this);
		}
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CommunicanteDAO commDao = new CommunicanteDAO();
		Communicante comm = new Communicante();
		comm.setC1(c1);
		if(arg0.getSource() == btnCelleci){
			// Récupération de la seconde chambre
			Chambre c2 = cDAO.find(cTab[0].getId_chambre());
			// La chambre en question est maintennant communicante
			c2.setCommunicante(true);
			comm.setC2(c2);
			// Update de c2 et création de la communication
			cDAO.update(c2);
			// Update de c1
			chambre.setCommunicante(true);
			cDAO.update(chambre);
			commDao.create(comm);
			this.dispose();
		}else if(arg0.getSource() == btnCellel){
			// Récupération de la seconde chambre
			Chambre c2 = cDAO.find(cTab[1].getId_chambre());
			// LA chambre en question est maintennant communicante
			c2.setCommunicante(true);
			comm.setC2(c2);
			// Update de c2 et création de la communication
			cDAO.update(comm.getC2());
			commDao.create(comm);
			this.dispose();
		}
		
	}

}
