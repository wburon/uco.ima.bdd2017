package model;

public class Chambre {
	
	private int id_chambre;
	private Hotel hotel;
	private int numero_chambre;
	private boolean tele;
	private boolean animaux;
	private boolean handicap;
	private boolean libre;
	private boolean communicante;
	private double tarif;
	private Type_Chambre type_chambre;
	
	public Type_Chambre getType_chambre() {
		return type_chambre;
	}
	public void setType_chambre(Type_Chambre type_chambre) {
		this.type_chambre = type_chambre;
	}
	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
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
