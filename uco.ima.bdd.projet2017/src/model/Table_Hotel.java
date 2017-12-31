package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import DAO.HotelDAO;

@SuppressWarnings("serial")
public class Table_Hotel extends AbstractTableModel{
	
	private ArrayList<Hotel> listHotel = new ArrayList<Hotel>();
	
	private final String[] entetes = {"Nom", "Ville", "Pays", "Propriétaire", "Standing", "Nombre de chambre"};
	
	public Table_Hotel(Hotel hotel) {
		super();
		HotelDAO hDAO=new HotelDAO();
		listHotel = hDAO.ListHotel(hotel);
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listHotel.size();
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
			return listHotel.get(rowIndex).getName();
		case 1 : 
			return listHotel.get(rowIndex).getVille();
		case 2 :
			return listHotel.get(rowIndex).getPays();
		case 3 : 
			return listHotel.get(rowIndex).getProprietaire();
		case 4 :
			return listHotel.get(rowIndex).getStanding();
		case 5 :
			return listHotel.get(rowIndex).getNb_chambre_total();
		default : 
			return null;
		}
	}
	public void addHotel(Hotel h){
		listHotel.add(h);
		
		fireTableRowsInserted(listHotel.size()-1, listHotel.size()-1);
		
	}
	public void removeHotel(int rowIndex){
		listHotel.remove(rowIndex);
		
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	public Hotel getHotel(int i){
		return listHotel.get(i);
	}
	public void setHotel(int rowIndex){
		Hotel h = getHotel(rowIndex);
		listHotel.set(rowIndex, h);
		
		fireTableRowsUpdated(rowIndex, rowIndex);
	}
	public void actualiser(){
		fireTableDataChanged();
	}

	public ArrayList<Hotel> getListHotel() {
		return listHotel;
	}
	

}
