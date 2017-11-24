package model;

import java.sql.Date;

public class reservation {
	
	private int id_resa;
	private chambre chambre;
	private hotel hotel;
	private client client;
	private Date date_debut;
	private Date date_fin;
	
	public int getId_resa() {
		return id_resa;
	}
	public void setId_resa(int id_resa) {
		this.id_resa = id_resa;
	}
	public chambre getChambre() {
		return chambre;
	}
	public void setChambre(chambre chambre) {
		this.chambre = chambre;
	}
	public hotel getHotel() {
		return hotel;
	}
	public void setHotel(hotel hotel) {
		this.hotel = hotel;
	}
	public client getClient() {
		return client;
	}
	public void setClient(client client) {
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
