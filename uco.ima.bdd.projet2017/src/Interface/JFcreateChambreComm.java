package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.ChambreDAO;
import model.Chambre;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class JFcreateChambreComm extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnNouvelleChambre;
	private JButton btnChambreExistante;
	private ChambreDAO cDAO = new ChambreDAO();
	private int id_chambre;
	private Chambre c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFcreateChambreComm frame = new JFcreateChambreComm(1);
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
	public JFcreateChambreComm(int id_chambre) {
		this.id_chambre = id_chambre;
		this.c = cDAO.find(id_chambre);
		
		setTitle("Chambre Communicante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(50, 50));
		contentPane.add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel_1, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(50, 10));
		contentPane.add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 50));
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(1, 2, 5, 5));
		
		btnNouvelleChambre = new JButton("Nouvelle Chambre");
		panel_4.add(btnNouvelleChambre);
		btnNouvelleChambre.addActionListener(this);
		
		btnChambreExistante = new JButton("Chambre Existante");
		panel_4.add(btnChambreExistante);
		btnChambreExistante.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNouvelleChambre){
			JFAddChambre newC = new JFAddChambre(c.getId_chambre());
			newC.getCbComm().setEnabled(false);
			newC.setVisible(true);
			this.dispose();
		}else if(e.getSource() == btnChambreExistante){
			JFNowCommunicante nowC = new JFNowCommunicante(this.id_chambre);
			nowC.setVisible(true);
			this.dispose();
		}
		
	}

}
