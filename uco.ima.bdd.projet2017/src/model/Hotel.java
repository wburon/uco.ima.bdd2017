package model;

public class Hotel {
	
	private int id_hotel;
	private String adresse;
	private String ville;
	private int code_postal;
	private String pays;
	private double nb_etoile_ta;
	private int standing;
	private String proprietaire;
	private boolean wifi;
	private int nb_chambre_libre;
	
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public double getNb_etoile_ta() {
		return nb_etoile_ta;
	}
	public void setNb_etoile_ta(double nb_etoile_ta) {
		this.nb_etoile_ta = nb_etoile_ta;
	}
	public int getStanding() {
		return standing;
	}
	public void setStanding(int standing) {
		this.standing = standing;
	}
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	public boolean isWifi() {
		return wifi;
	}
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}
	public int getNb_chambre_libre() {
		return nb_chambre_libre;
	}
	public void setNb_chambre_libre(int nb_chambre_libre) {
		this.nb_chambre_libre = nb_chambre_libre;
	}

}
