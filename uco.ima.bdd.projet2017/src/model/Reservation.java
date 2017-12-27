package model;

import java.util.Date;

public class Reservation {
	
	private int id_resa;
	private Chambre chambre;
	private Hotel hotel;
	private Client client;
	private Date date_debut;
	private Date date_fin;
	
	public int getId_resa() {
		return id_resa;
	}
	public void setId_resa(int id_resa) {
		this.id_resa = id_resa;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

}
