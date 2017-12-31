package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO.ClientDAO;



@SuppressWarnings("serial")
public class Table_Client extends AbstractTableModel {
	private ArrayList<Client> listClient = new ArrayList<Client>();

	private final String[] entetes = { "Nom", "Nombre Resa en cours", "Fidelité", "Ville", "CP"};

	public Table_Client() {
		super();
		ClientDAO cDAO = new ClientDAO();
		listClient = cDAO.ListClient();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listClient.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return listClient.get(rowIndex).getPersonne().getNom();
		case 1:
			return listClient.get(rowIndex).getNb_resa_en_cours();
		case 2:
			return listClient.get(rowIndex).getFidelite();
		case 3:
			return listClient.get(rowIndex).getPersonne().getVille();
		case 4:
			return listClient.get(rowIndex).getPersonne().getCode_postal();
		default:
			return null;
		}
	}

	public void addClient(Client c) {
		listClient.add(c);

		fireTableRowsInserted(listClient.size() - 1, listClient.size() - 1);

	}

	public void removeClient(int rowIndex) {
		listClient.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public Client getClient(int i) {
		return listClient.get(i);
	}

}
