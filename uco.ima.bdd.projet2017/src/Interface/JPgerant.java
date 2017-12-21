package Interface;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class JPgerant extends JPanel implements ActionListener{

	
	private JButton btnGestionPersonnel;
	private JButton btnOperationSurHotel;
	private JButton btnGestionClient;
	private JButton btnQuitter; 
	
	private JFInterface JFInterface;
	/**
	 * Create the panel.
	 */
	public JPgerant(JFInterface JFInterface) {
		this.JFInterface=JFInterface;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{181, 125, 0};
		gridBagLayout.rowHeights = new int[]{106, 23, 23, 23, 56, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnGestionPersonnel= new JButton("Gestion Personnel");
		GridBagConstraints gbc_btnGestionPersonnel = new GridBagConstraints();
		gbc_btnGestionPersonnel.anchor = GridBagConstraints.NORTH;
		gbc_btnGestionPersonnel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGestionPersonnel.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionPersonnel.gridx = 1;
		gbc_btnGestionPersonnel.gridy = 1;
		add(btnGestionPersonnel, gbc_btnGestionPersonnel);
		btnGestionPersonnel.addActionListener(this);
		
		btnOperationSurHotel= new JButton("Opération sur hotel");
		GridBagConstraints gbc_btnOperationSurHotel = new GridBagConstraints();
		gbc_btnOperationSurHotel.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOperationSurHotel.insets = new Insets(0, 0, 5, 0);
		gbc_btnOperationSurHotel.gridx = 1;
		gbc_btnOperationSurHotel.gridy = 2;
		add(btnOperationSurHotel, gbc_btnOperationSurHotel);
		btnOperationSurHotel.addActionListener(this);
		
		btnGestionClient = new JButton("Gestion Client");
		GridBagConstraints gbc_btnGestionClient = new GridBagConstraints();
		gbc_btnGestionClient.anchor = GridBagConstraints.NORTH;
		gbc_btnGestionClient.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGestionClient.insets = new Insets(0, 0, 5, 0);
		gbc_btnGestionClient.gridx = 1;
		gbc_btnGestionClient.gridy = 3;
		add(btnGestionClient, gbc_btnGestionClient);
		btnGestionClient.addActionListener(this);
		
		btnQuitter = new JButton("Quitter");
		GridBagConstraints gbc_btnQuitter = new GridBagConstraints();
		gbc_btnQuitter.anchor = GridBagConstraints.NORTH;
		gbc_btnQuitter.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuitter.gridx = 1;
		gbc_btnQuitter.gridy = 5;
		add(btnQuitter, gbc_btnQuitter);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnGestionPersonnel){
			JFInterface.setContentPane(JFInterface.getJPgePerso());
			JFInterface.getJPgePerso().repaint();
			JFInterface.getJPgePerso().revalidate();
		}else if(e.getSource() == btnOperationSurHotel){
			JFInterface.setContentPane(JFInterface.getJPgeHotel());
			JFInterface.getJPgeHotel().repaint();
			JFInterface.getJPgeHotel().revalidate();
		}
	}

}
