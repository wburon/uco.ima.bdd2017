package model;

public class Client {
	
	private int id_client;
	private Personne personne;
	private int nb_resa_en_cours;
	private int fidelite;
	
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public int getNb_resa_en_cours() {
		return nb_resa_en_cours;
	}
	public void setNb_resa_en_cours(int nb_resa_en_cours) {
		this.nb_resa_en_cours = nb_resa_en_cours;
	}
	public int getFidelite() {
		return fidelite;
	}
	public void setFidelite(int fidelite) {
		this.fidelite = fidelite;
	}

}
