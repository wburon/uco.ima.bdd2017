package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Table_Hotel;

public class JPgeHotel extends JPanel implements ActionListener{

	private JButton btnAjout;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JTable table;
	private Table_Hotel tHotel = new Table_Hotel();

	/**
	 * Create the panel.
	 */
	public JPgeHotel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		btnAjout = new JButton("Ajouter");
		panel_2.add(btnAjout);
		btnAjout.addActionListener(this);
		
		btnModif = new JButton("Modifier");
		panel_2.add(btnModif);
		btnModif.addActionListener(this);
		
		btnSupprimer = new JButton("Supprimer");
		panel_2.add(btnSupprimer);
		btnSupprimer.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		
		table = new JTable(tHotel);
		panel_4.add(new JScrollPane(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnModif){
			JFModifPerso f1 = new JFModifPerso();
			int s = table.getSelectedRow();
			f1.preAffichage(tPerso.getPersonnel(s));
			f1.setVisible(true);
		}
		
	}

}
