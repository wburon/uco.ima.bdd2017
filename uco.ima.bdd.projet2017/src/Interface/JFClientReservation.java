package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Chambre;
import model.Reservation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class JFClientReservation extends JFrame implements ActionListener{

	private JPanel contentPane;
	private ArrayList<Reservation> listReservation;
	private JButton btnSelect;
	private JButton btnAdd;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					JFClientReservation frame = new JFClientReservation();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public ArrayList<Reservation> getListReservation() {
		return listReservation;
	}

	public void setListReservation(ArrayList<Reservation> listReservation) {
		this.listReservation = listReservation;
	}

	/**
	 * Create the frame.
	 */
	public JFClientReservation(ArrayList<Reservation> listReservation) {
		setTitle("Client");
		this.listReservation = listReservation;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 5, 5));
		
		btnSelect = new JButton("Client Existant");
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(btnSelect);
		btnSelect.addActionListener(this);
		
		btnAdd = new JButton("Nouveau Client");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(btnAdd);
		btnAdd.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSelect){
			this.setContentPane(new JPSelectClient(this));
			this.revalidate();
			this.repaint();
			
		}else if(e.getSource() == btnAdd){
			
		}
		
	}

}
