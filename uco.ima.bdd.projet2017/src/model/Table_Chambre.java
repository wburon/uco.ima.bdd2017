package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import DAO.ChambreDAO;

@SuppressWarnings("serial")
public class Table_Chambre extends AbstractTableModel{

private ArrayList<Chambre> listChambre = new ArrayList<Chambre>();
	
	private final String[] entetes = {"Numero", "Tele", "Handicap", "Communicante", "Animaux", "Type", "Tarif"};

	
	public Table_Chambre(int id_hotel) {
		super();
		ChambreDAO cDAO=new ChambreDAO();
		listChambre = cDAO.ListChambre(id_hotel);
	}
	
	public ArrayList<Chambre> getListChambre() {
		return listChambre;
	}

	public void setListChambre(ArrayList<Chambre> listChambre) {
		this.listChambre = listChambre;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listChambre.size();
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
			return listChambre.get(rowIndex).getNumero_chambre();
		case 1 : 
			return listChambre.get(rowIndex).isTele();
		case 2 :
			return listChambre.get(rowIndex).isHandicap();
		case 3 : 
			return listChambre.get(rowIndex).isCommunicante();
		case 4 :
			return listChambre.get(rowIndex).isAnimaux();
		case 5 :
			Object obj =  listChambre.get(rowIndex).getType_chambre().getNom();
			return obj;
		case 6 :
			return listChambre.get(rowIndex).getTarif();
		default : 
			return null;
		}
	}
	public void addChambre(Chambre c){
		listChambre.add(c);
		
		this.fireTableRowsInserted(listChambre.size()-1, listChambre.size()-1);
		
	}
	public void removeChambre(int rowIndex){
		listChambre.remove(rowIndex);
		
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public Chambre getChambre(int i){
		return listChambre.get(i);
	}
	public void setChambre(int rowIndex, Chambre chambre){

		listChambre.set(rowIndex, chambre);
		
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}
	public void actualiser(){
		this.fireTableDataChanged();
	}
	
}
