package model;

import java.sql.Date;

public class ReservationReunion {

	private int id_reservReunion;
	private Salle_Reunion salle;
	private Client client;
	private Date date_debut;
	private Date date_fin;
	
	public int getId_reservReunion() {
		return id_reservReunion;
	}
	public void setId_reservReunion(int id_reservReunion) {
		this.id_reservReunion = id_reservReunion;
	}
	public Salle_Reunion getSalle() {
		return salle;
	}
	public void setSalle(Salle_Reunion salle) {
		this.salle = salle;
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
