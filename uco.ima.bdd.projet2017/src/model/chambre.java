package model;

public class chambre {

	private int id_chambre;
	private hotel hotel;
	private int numero_chambre;
	private boolean tele;
	private boolean animaux;
	private boolean handicap;
	private boolean libre;
	private boolean communicante;
	private double tarif;
	
	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public hotel getHotel() {
		return hotel;
	}
	public void setHotel(hotel hotel) {
		this.hotel = hotel;
	}
	public int getNumero_chambre() {
		return numero_chambre;
	}
	public void setNumero_chambre(int numero_chambre) {
		this.numero_chambre = numero_chambre;
	}
	public boolean isTele() {
		return tele;
	}
	public void setTele(boolean tele) {
		this.tele = tele;
	}
	public boolean isAnimaux() {
		return animaux;
	}
	public void setAnimaux(boolean animaux) {
		this.animaux = animaux;
	}
	public boolean isHandicap() {
		return handicap;
	}
	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	public boolean isLibre() {
		return libre;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public boolean isCommunicante() {
		return communicante;
	}
	public void setCommunicante(boolean communicante) {
		this.communicante = communicante;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}
	
	
}
