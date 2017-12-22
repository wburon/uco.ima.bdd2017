package Interface;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.Personnel;
import model.Table_Personnel;

import javax.swing.JTable;
import javax.swing.AbstractAction;
import javax.swing.JButton;

public class JPgePerso extends JPanel implements ActionListener{
	private Table_Personnel tPerso = new Table_Personnel();
	private JTable table;

	private JButton btnAjout;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JButton btnRetourMenu;
	private JButton btnActualiser;
	
	private Personnel nel;
	private boolean addP;
	private boolean setP;
	private boolean delP;
	
	private JFAddPerso f2;
	
	private JFInterface JFInterface;
	
	
	/**
	 * Create the panel.
	 */
	public JPgePerso(JFInterface JFInterface) {
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
		
		btnActualiser = new JButton("Actualiser");
		panel_2.add(btnActualiser);
		btnActualiser.addActionListener(this);
		
		btnAjout = new JButton("Ajouter"/*new AddAction()*/);
		panel_2.add(btnAjout);
		btnAjout.addActionListener(this);
		
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
		
		table = new JTable(tPerso);
		panel_4.add(new JScrollPane(table));
		
		f2 = new JFAddPerso();
		
		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int s=-1;
		int indexSet=-1;
		int indexDel=-1;
		if (e.getSource()==btnModif){

			s = table.getSelectedRow();
			
			if(s==-1){
				JOptionPane.showMessageDialog(btnModif, "Vous devez sélectionné une ligne dans le tableau !","Selection",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JFModifPerso f1 = new JFModifPerso(tPerso.getPersonnel(s));
				f1.preAffichage(tPerso.getPersonnel(s));
				f1.setVisible(true);
				nel=f1.getPersonnel();
				indexSet=s;
				setP=true;
				
			}
		}
		if (e.getSource()==btnAjout){ 
			f2.setVisible(true);
			nel = f2.getPersonnel();
			addP = true;
			
		}
		if (e.getSource()==btnRetourMenu){
			JFInterface.setContentPane(JFInterface.getJPgerant());
			JFInterface.repaint();
			JFInterface.revalidate();
		}
		if (e.getSource()==btnActualiser){
			if(addP)
				tPerso.addPersonnel(nel);
			if(setP)
				tPerso.setPersonnel(indexSet,nel);
			if(delP){
				tPerso.removePersonnel(indexDel);
			}
		}
		if (e.getSource()==btnSupprimer){
			s = table.getSelectedRow();
			if (s==-1){
				JOptionPane.showMessageDialog(btnSupprimer, "Vous devez sélectionné une ligne dans le tableau !","Selection",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				String nom=tPerso.getPersonnel(s).getPersonne().getNom();
				String prenom=tPerso.getPersonnel(s).getPersonne().getPrenom();
				String message="Le membre du personnel "+prenom+" "+nom+" a été supprimé !";
				JOptionPane.showMessageDialog(btnSupprimer, message,"Suppression",JOptionPane.INFORMATION_MESSAGE);
				indexDel=s;
				delP=true;
			}
		}
		
	}
	
	public Table_Personnel getTPerso(){
		return tPerso;
	}
}
