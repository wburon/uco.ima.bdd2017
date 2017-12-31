package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DAO.ChambreDAO;
import DAO.HotelDAO;
import model.Chambre;
import model.Hotel;
import model.Table_Chambre;
import model.Table_Hotel;

@SuppressWarnings("serial")
public class JPgeHotel extends JPanel implements ActionListener {

	private JButton btnAjout;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JTable table;
	private Table_Hotel tHotel;
	private JButton btnPlusDinfo;
	private int s = -1;
	private JButton btnActualiser;
	private Hotel h;
	private Chambre c;
	private Table_Chambre tChambre;
	private JFModifChambre mC;
	private JFAddChambre c1;
	private JFAddHotel h1;
	private HotelDAO hDAO = new HotelDAO();
	private ChambreDAO cDAO = new ChambreDAO();
	private JButton btnRetourMenu;
	private JFInterface JFInterface;
	private JFModifHotel mH;

	/**
	 * Create the panel.
	 */
	public JPgeHotel(JFInterface JFInterface) {
		this.JFInterface = JFInterface;
		this.tHotel = new Table_Hotel(JFInterface.getPersoConn().getHotel());

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

		btnRetourMenu = new JButton("Retour Menu");
		panel_2.add(btnRetourMenu);
		btnRetourMenu.addActionListener(this);

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
					btnPlusDinfo.setText("Retour au hotel");
					tChambre = new Table_Chambre(tHotel.getHotel(s).getId_hotel());
					table.setModel(tChambre);
					}
					else
						JOptionPane.showMessageDialog(btnPlusDinfo, "Selectionner un hotel !", "Warning",JOptionPane.INFORMATION_MESSAGE);
				}else{
					table.setModel(tHotel);
					btnPlusDinfo.setText("Plus d'info sur l'hotel");
				}
			

		}else if(e.getSource() == btnAjout){
			if(table.getModel() == tHotel){
				if(tHotel.getListHotel().isEmpty()){
					h1 = new JFAddHotel();
					h1.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(btnAjout, "Vous avez déjà un hotel !", "Warning",JOptionPane.INFORMATION_MESSAGE);
			}else{
				c1 = new JFAddChambre(tHotel.getHotel(s).getId_hotel());
				c1.setVisible(true);
			}
		}else if(e.getSource() == btnActualiser){
			table.setVisible(false);
			table.setVisible(true);
			
		}else if(e.getSource() == btnModif){
			if(table.getModel() == tHotel){
				s = table.getSelectedRow();
				if (s==-1){
					JOptionPane.showMessageDialog(btnModif, "Vous devez sélectionné une ligne dans le tableau !","Selection",JOptionPane.INFORMATION_MESSAGE);
				}else{
					mH = new JFModifHotel(tHotel.getHotel(s));
					mH.preAffichage(tHotel.getHotel(s));
					mH.setVisible(true);
				}
			}else{
				s = table.getSelectedRow();
				if (s==-1){
					JOptionPane.showMessageDialog(btnModif, "Vous devez sélectionné une ligne dans le tableau !","Selection",JOptionPane.INFORMATION_MESSAGE);
				}else{
					mC = new JFModifChambre(tChambre.getChambre(s));
					mC.preAffichage(tChambre.getChambre(s));
					mC.setVisible(true);
				}
			}
		}else if(e.getSource() == btnSupprimer){
			s = table.getSelectedRow();
			if (s==-1){
				JOptionPane.showMessageDialog(btnModif, "Vous devez sélectionné une ligne dans le tableau !","Selection",JOptionPane.INFORMATION_MESSAGE);
			}else{
				if(table.getModel() == tHotel){
					if(hDAO.delete(tHotel.getHotel(s)))
						JOptionPane.showMessageDialog(btnSupprimer, "Votre suppression a bien été effectué", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
				
				}else{
					if(cDAO.delete(tChambre.getChambre(s)))
						JOptionPane.showMessageDialog(btnSupprimer, "Votre suppression a bien été effectué", "Validation",
								JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		}else if(e.getSource()==btnRetourMenu){
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
		}
	}
}
