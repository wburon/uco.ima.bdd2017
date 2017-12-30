package Interface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.Window;

import DAO.ReservationDAO;
import model.Chambre;
import model.Client;
import model.Reservation;
import model.Table_Client;

public class JPSelectClient extends JPanel implements ActionListener{
	private JTable table;
	private JButton btnReserver;
	private Table_Client tClient = new Table_Client();
	private int s=-1;
	private JFClientReservation f;
	private ReservationDAO rDAO = new ReservationDAO();
	private Window window;

	/**
	 * Create the panel.
	 */
	public JPSelectClient(JFClientReservation frame) {
		this.f = frame;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		add(panel, BorderLayout.SOUTH);
		
		btnReserver = new JButton("Reserver");
		btnReserver.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(btnReserver);
		btnReserver.addActionListener(this);
		
		table = new JTable(tClient);
		add(new JScrollPane(table), BorderLayout.CENTER);
		

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnReserver){
			s = table.getSelectedRow();
			if(s == -1)
				JOptionPane.showMessageDialog(btnReserver, "Selectionner un client !", "Warning",JOptionPane.INFORMATION_MESSAGE);
			else{
				Client cl = tClient.getClient(s);
				for(Reservation c : f.getListReservation()){
					c.setClient(cl);
					rDAO.create(c);
				}
				JOptionPane.showMessageDialog(btnReserver, "Reservation réussi", "Bravo",JOptionPane.INFORMATION_MESSAGE);
				window = SwingUtilities.windowForComponent((Component)arg0.getSource());
				window.dispose();
			}

			
		}
		
	}
	
	

}
