package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Table_Client;

@SuppressWarnings("serial")
public class JPgeClient extends JPanel implements ActionListener {

	private JButton btnActualiser;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JTable table;
	private Table_Client tClient = new Table_Client();
	private JFInterface JFInterface;
	private JButton btnRetourMenu;
	private JButton btnReservation;
	/**
	 * Create the panel.
	 */
	public JPgeClient(JFInterface JFInterface) {
		
		this.JFInterface=JFInterface;
		
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		btnReservation = new JButton("Reservation");
		panel_2.add(btnReservation);
		btnReservation.addActionListener(this);

		btnActualiser = new JButton("Actualiser");
		panel_2.add(btnActualiser);
		btnActualiser.addActionListener(this);

		btnModif = new JButton("Modifier");
		panel_2.add(btnModif);
		btnModif.addActionListener(this);

		btnSupprimer = new JButton("Supprimer");
		panel_2.add(btnSupprimer);
		btnSupprimer.addActionListener(this);
		
		btnRetourMenu = new JButton("Retour Menu");
		panel_2.add(btnRetourMenu);
		btnRetourMenu.addActionListener(this);

		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);

		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);

		table = new JTable(tClient);
		panel_4.add(new JScrollPane(table));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnRetourMenu){
			if (JFInterface.getPersoConn().getFonction().getNiveau_contrainte()==2){
				JFInterface.setContentPane(JFInterface.getJPgerant());
				JFInterface.repaint();
				JFInterface.revalidate();
			}
			else{
				JFInterface.setContentPane(JFInterface.getJPEmployer());
				JFInterface.repaint();
				JFInterface.revalidate();
			}
		}else if(arg0.getSource() == btnReservation){
			JFReservation JFresa = new JFReservation(JFInterface.getPersoConn().getHotel().getId_hotel());
			JFresa.setVisible(true);
		}else if(arg0.getSource() == btnModif){
			JFModifClient modifclient = new JFModifClient(tClient.getClient(table.getSelectedRow()));
			modifclient.preAffichage(tClient.getClient(table.getSelectedRow()).getPersonne());
			modifclient.setVisible(true);
		}else if(arg0.getSource() == btnActualiser){
			table.setVisible(false);
			table.setVisible(true);
		}
	}

}
