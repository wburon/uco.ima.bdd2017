package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Chambre;
import model.Hotel;
import model.Table_Chambre;
import model.Table_Hotel;

public class JPgeHotel extends JPanel implements ActionListener{

	private JButton btnAjout;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JTable table;
	private Table_Hotel tHotel = new Table_Hotel();
	private JButton btnPlusDinfo;
	private int s =-1;
	private JButton btnActualiser;
	private Hotel h;
	private Chambre c;
	private Table_Chambre tChambre;

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
		
		btnActualiser = new JButton("Actualiser");
		panel_2.add(btnActualiser);
		btnActualiser.addActionListener(this);
		
		btnAjout = new JButton("Ajouter");
		panel_2.add(btnAjout);
		btnAjout.addActionListener(this);
		
		btnModif = new JButton("Modifier");
		panel_2.add(btnModif);
		btnModif.addActionListener(this);
		
		btnSupprimer = new JButton("Supprimer");
		panel_2.add(btnSupprimer);
		btnSupprimer.addActionListener(this);
		
		btnPlusDinfo = new JButton("Plus d'info sur l'hotel");
		panel_2.add(btnPlusDinfo);
		btnPlusDinfo.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		
		table = new JTable(tHotel);
		panel_4.add(new JScrollPane(table));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPlusDinfo){
			s = table.getSelectedRow();
			
				if(table.getModel() == tHotel){
					if(s != -1){
					table.setModel(new Table_Chambre(s+1));
					btnPlusDinfo.setText("Retour au hotel");
					}
					else
						JOptionPane.showMessageDialog(btnPlusDinfo, "Selectionner un hotel !", "Warning",JOptionPane.INFORMATION_MESSAGE);
				}else{
					table.setModel(tHotel);
					btnPlusDinfo.setText("Plus d'info sur l'hotel");
				}
			

		}else if(e.getSource() == btnAjout){
			if(table.getModel() == tHotel){
				JFAddHotel h1 = new JFAddHotel();
				h1.setVisible(true);
				h = h1.getHotel();
			}else{
				JFAddChambre c1 = new JFAddChambre(tHotel.getHotel(s).getId_hotel());
				c1.setVisible(true);
				c = c1.getChambre();
			}
		}else if(e.getSource() == btnActualiser){
			if(table.getModel() == tHotel)
				tHotel.addHotel(h);
			else
				tChambre.addChambre(c);
		}
		if(e.getSource() == btnModif){
			
		}
		
	}
	

}
