package model;

public class salle_reunion {
	
	private int id_salle;
	private hotel hotel;
	private int numero_salle;
	private boolean materiel_informatique;
	private int capacite;
	
	public int getId_salle() {
		return id_salle;
	}
	public void setId_salle(int id_salle) {
		this.id_salle = id_salle;
	}
	public hotel getHotel() {
		return hotel;
	}
	public void setHotel(hotel hotel) {
		this.hotel = hotel;
	}
	public int getNumero_salle() {
		return numero_salle;
	}
	public void setNumero_salle(int numero_salle) {
		this.numero_salle = numero_salle;
	}
	public boolean isMateriel_informatique() {
		return materiel_informatique;
	}
	public void setMateriel_informatique(boolean materiel_informatique) {
		this.materiel_informatique = materiel_informatique;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	

}
