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

public class JPgeClient extends JPanel implements ActionListener {

	private JButton btnActualiser;
	private JButton btnAjout;
	private JButton btnModif;
	private JButton btnSupprimer;
	private JButton btnPlusDinfo;
	private JTable table;
	private Table_Client tClient = new Table_Client();

	/**
	 * Create the panel.
	 */
	public JPgeClient() {
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

		table = new JTable(tClient);
		panel_4.add(new JScrollPane(table));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
