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
		panel_4.setLayout(new GridLayout(3, 2, 0, 0));
		
		cTab = cDAO.chambresVoisines(id_chambre); 
		if(!cTab[0].isCommunicante()){
			lblMoins = new JLabel(""+cTab[0].getNumero_chambre());
			lblMoins.setHorizontalAlignment(SwingConstants.CENTER);
			panel_4.add(lblMoins);
			
		
			lblIdMoins = new JLabel("ID : "+cTab[0].getId_chambre());
			lblIdMoins.setHorizontalAlignment(SwingConstants.CENTER);
			panel_4.add(lblIdMoins);
		
			btnCelleci = new JButton("Celle-ci");
			panel_4.add(btnCelleci);
		}
		if(!cTab[1].isCommunicante()){
			lblPlus = new JLabel(""+cTab[1].getNumero_chambre());
			lblPlus.setHorizontalAlignment(SwingConstants.CENTER);
			panel_4.add(lblPlus);
			
			lblIdPlus = new JLabel("ID : "+cTab[1].getId_chambre());
			lblIdPlus.setHorizontalAlignment(SwingConstants.CENTER);
			panel_4.add(lblIdPlus);
			
			btnCellel = new JButton("Celle-l\u00E0");
			panel_4.add(btnCellel);
		}
		
		btnCelleci.addActionListener(this);
		btnCellel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CommunicanteDAO commDao = new CommunicanteDAO();
		Communicante comm = new Communicante();
		comm.setC1(c1);
		if(arg0.getSource() == btnCelleci){
			comm.setC2(cDAO.find(cTab[0].getId_chambre()));
			
		}else if(arg0.getSource() == btnCellel){
			comm.setC2(cDAO.find(cTab[1].getId_chambre()));
			
		}
		commDao.create(comm);
	}

}
