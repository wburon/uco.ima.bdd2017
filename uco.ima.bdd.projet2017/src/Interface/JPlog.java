package Interface;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import DAO.PersonnelDAO;
import model.Personnel;

import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class JPlog extends JPanel implements ActionListener{
	private JTextField tfLog;
	private JPasswordField pfPasswd;
	private JButton btnConnexion;
	private JButton btnCrerUnCompte;
	
	private PersonnelDAO nDAO;
	private Personnel nel;

	/**
	 * Create the panel.
	 */
	public JPlog() {
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
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Login : ");
		panel_4.add(lblNewLabel, "12, 8, right, default");
		
		tfLog = new JTextField();
		panel_4.add(tfLog, "14, 8, fill, default");
		tfLog.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password : ");
		panel_4.add(lblNewLabel_1, "12, 10, right, default");
		
		pfPasswd = new JPasswordField();
		pfPasswd.setColumns(10);
		panel_4.add(pfPasswd, "14, 10, fill, default");
		
		btnConnexion = new JButton("Se Connecter");
		panel_4.add(btnConnexion, "12, 12");
		
		btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		panel_4.add(btnCrerUnCompte, "14, 12");

		nDAO=new PersonnelDAO();
		nel = new Personnel();
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnConnexion){
			String login=tfLog.getText();
			String password=pfPasswd.getText();
			
			
			nel = nDAO.find(nDAO.findUser(login, password));
			
			if(nel.getFonction().getNiveau_contrainte()==0){
				
			}
			if(nel.getFonction().getNiveau_contrainte()==1){
				
			}
			if(nel.getFonction().getNiveau_contrainte()==2){
				JPgerant jpg=new JPgerant();
				
			}
		}
		
	}

}
