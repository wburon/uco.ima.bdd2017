package model;

public class Parking {
	
	private int id_parking;
	private Hotel hotel;
	private int nb_place;
	private boolean gratuit;
	private double distance_hotel;
	private boolean veilleur_de_nuit;
	
	public int getId_parking() {
		return id_parking;
	}
	public void setId_parking(int id_parking) {
		this.id_parking = id_parking;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public int getNb_place() {
		return nb_place;
	}
	public void setNb_place(int nb_place) {
		this.nb_place = nb_place;
	}
	public boolean isGratuit() {
		return gratuit;
	}
	public void setGratuit(boolean gratuit) {
		this.gratuit = gratuit;
	}
	public double getDistance_hotel() {
		return distance_hotel;
	}
	public void setDistance_hotel(double distance_hotel) {
		this.distance_hotel = distance_hotel;
	}
	public boolean isVeilleur_de_nuit() {
		return veilleur_de_nuit;
	}
	public void setVeilleur_de_nuit(boolean veilleur_de_nuit) {
		this.veilleur_de_nuit = veilleur_de_nuit;
	}

}
