package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.PersonnelDAO;
import model.Personnel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class JPlog extends JPanel implements ActionListener{
	
	private JTextField tfLog;
	private JPasswordField pfPasswd;
	private JButton btnConnexion;
	private JButton btnCrerUnCompte;
	private JPanel JPBlock;
	private JPanel panel_4;
	private PersonnelDAO nDAO;
	private Personnel nel;
	
	private JFInterface JFInterface;

	/**
	 * Create the panel.
	 */
	public JPlog(JFInterface JFInterface) {
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
		
		panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{114, 97, 113, 0};
		gbl_panel_4.rowHeights = new int[]{111, 0, 20, 20, 23, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JLabel lblNewLabel = new JLabel("Login : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_4.add(lblNewLabel, gbc_lblNewLabel);
		
		tfLog = new JTextField();
		GridBagConstraints gbc_tfLog = new GridBagConstraints();
		gbc_tfLog.anchor = GridBagConstraints.NORTH;
		gbc_tfLog.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfLog.insets = new Insets(0, 0, 5, 0);
		gbc_tfLog.gridx = 2;
		gbc_tfLog.gridy = 1;
		panel_4.add(tfLog, gbc_tfLog);
		tfLog.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password : ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		panel_4.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		pfPasswd = new JPasswordField();
		pfPasswd.setColumns(10);
		GridBagConstraints gbc_pfPasswd = new GridBagConstraints();
		gbc_pfPasswd.anchor = GridBagConstraints.NORTH;
		gbc_pfPasswd.fill = GridBagConstraints.HORIZONTAL;
		gbc_pfPasswd.insets = new Insets(0, 0, 5, 0);
		gbc_pfPasswd.gridx = 2;
		gbc_pfPasswd.gridy = 2;
		panel_4.add(pfPasswd, gbc_pfPasswd);
		
		btnConnexion = new JButton("Se Connecter");
		GridBagConstraints gbc_btnConnexion = new GridBagConstraints();
		gbc_btnConnexion.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnConnexion.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnexion.gridx = 1;
		gbc_btnConnexion.gridy = 3;
		panel_4.add(btnConnexion, gbc_btnConnexion);
		btnConnexion.addActionListener(this);
		
		btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		GridBagConstraints gbc_btnCrerUnCompte = new GridBagConstraints();
		gbc_btnCrerUnCompte.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrerUnCompte.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCrerUnCompte.gridx = 2;
		gbc_btnCrerUnCompte.gridy = 3;
		panel_4.add(btnCrerUnCompte, gbc_btnCrerUnCompte);
		btnCrerUnCompte.addActionListener(this);
		
		JPBlock = new JPanel();
		JPBlock.setLayout(new BorderLayout(0,0));
		
		JLabel labelBlock=new JLabel("Vous n'êtes pas autoriser à accéder au logiciel");
		JPBlock.add(labelBlock, BorderLayout.CENTER);
		
		
		
		nDAO=new PersonnelDAO();
		nel = new Personnel();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnConnexion){
			
			String login=tfLog.getText();
			
			String passwordNotHash=pfPasswd.getText();
			String password = nDAO.HashPassword(passwordNotHash);
			
			
			nel = nDAO.find(nDAO.findUser(login, password));
			
			if (nDAO.findUser(login, password)==-1){
				JOptionPane.showMessageDialog(btnConnexion, "Le login ou le mot de passe sont faux !", "Autorisation", JOptionPane.ERROR_MESSAGE);
			}
			
			if(nel.getFonction().getNiveau_contrainte()==0){
				JOptionPane.showMessageDialog(panel_4, "Vous n'êtes pas autorisé à accéder au logiciel", "Autorisation", JOptionPane.INFORMATION_MESSAGE);
				
			}
			if(nel.getFonction().getNiveau_contrainte()==1){
				JFInterface.setContentPane(JFInterface.getJPEmployer());
				JFInterface.getJPEmployer().repaint();
				JFInterface.getJPEmployer().revalidate();
				JFInterface.setPersoConn(nel);
				JFInterface.LancementPanelGestion();
			}
			if(nel.getFonction().getNiveau_contrainte()==2){

				JFInterface.setContentPane(JFInterface.getJPgerant());
				JFInterface.getJPgerant().repaint();
				JFInterface.getJPgerant().revalidate();
				JFInterface.setPersoConn(nel);
				JFInterface.LancementPanelGestion();
			}
		}
		if(e.getSource()==btnCrerUnCompte){
			JFCréationCompte jfcc = new JFCréationCompte();
			jfcc.setVisible(true);
		}
		
	}

}
