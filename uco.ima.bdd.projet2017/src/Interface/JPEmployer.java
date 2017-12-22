package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import model.Personnel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class JPEmployer extends JPanel implements ActionListener{

	private JButton btnOperationSurHotel;
	private JButton btnGestionClient;
	private JButton btnQuitter;
	
	private JFInterface JFInterface;
	
	private Personnel PersoConn;

	/**
	 * Create the panel.
	 */
	public JPEmployer(JFInterface JFInterface) {
		this.JFInterface=JFInterface;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.SOUTH);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		btnOperationSurHotel = new JButton("Operation sur hotel");
		GridBagConstraints gbc_btnOperationSurHotel = new GridBagConstraints();
		gbc_btnOperationSurHotel.anchor = GridBagConstraints.NORTH;
		gbc_btnOperationSurHotel.insets = new Insets(0, 0, 5, 0);
		gbc_btnOperationSurHotel.gridx = 7;
		gbc_btnOperationSurHotel.gridy = 3;
		panel_4.add(btnOperationSurHotel, gbc_btnOperationSurHotel);
		btnOperationSurHotel.addActionListener(this);
		
		btnGestionClient = new JButton("Gestion Client");
		GridBagConstraints gbc_btnGestionClient = new GridBagConstraints();
		gbc_btnGestionClient.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionClient.gridx = 7;
		gbc_btnGestionClient.gridy = 4;
		panel_4.add(btnGestionClient, gbc_btnGestionClient);
		btnGestionClient.addActionListener(this);
		
		btnQuitter = new JButton("Quitter");
		GridBagConstraints gbc_btnQuitter = new GridBagConstraints();
		gbc_btnQuitter.gridx = 7;
		gbc_btnQuitter.gridy = 8;
		panel_4.add(btnQuitter, gbc_btnQuitter);
		btnQuitter.addActionListener(this);

	}
	public Personnel getPersoConn() {
		return PersoConn;
	}

	public void setPersoConn(Personnel persoConn) {
		PersoConn = persoConn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOperationSurHotel){
			JFInterface.setContentPane(JFInterface.getJPgeHotel());
			JFInterface.getJPgeHotel().repaint();
			JFInterface.getJPgeHotel().revalidate();
		}else if(e.getSource()==btnGestionClient){
			JFInterface.setContentPane(JFInterface.getJPgeClient());
			JFInterface.getJPgeClient().repaint();
			JFInterface.getJPgeClient().revalidate();
		}else if(e.getSource()==btnQuitter){
			JFInterface.dispose();
		}
	}

}
