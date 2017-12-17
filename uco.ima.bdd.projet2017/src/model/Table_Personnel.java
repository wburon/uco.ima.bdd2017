package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO.PersonnelDAO;

public class Table_Personnel extends AbstractTableModel {

	private ArrayList<Personnel> listPerso = new ArrayList<Personnel>();
	
	private final String[] entetes = {"Nom", "Prénom", "Salaire", "Année d'arrivée", "Fonction", "Login"};
	
	public Table_Personnel() {
		super();
		PersonnelDAO pDAO=new PersonnelDAO();
		listPerso = pDAO.ListPersonnel();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listPerso.size();
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
		switch(columnIndex){
		case 0 :
			return listPerso.get(rowIndex).getPersonne().getNom();
		case 1 : 
			return listPerso.get(rowIndex).getPersonne().getPrenom();
		case 2 :
			return listPerso.get(rowIndex).getSalaire();
		case 3 : 
			return listPerso.get(rowIndex).getAnnee_arrivee();
		case 4 :
			return listPerso.get(rowIndex).getFonction().getNom();
		case 5 :
			return listPerso.get(rowIndex).getLogin();
		default : 
			return null;
		}
	}
	public void addPersonnel(Personnel p){
		listPerso.add(p);
		
		fireTableRowsInserted(listPerso.size()-1, listPerso.size()-1);
		
	}
	public void removePersonnel(int rowIndex){
		listPerso.remove(rowIndex);
		
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public Personnel getPersonnel(int i){
		return listPerso.get(i);
	}
	

}
