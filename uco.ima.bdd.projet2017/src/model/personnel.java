package model;

public class personnel {
	
	private int id_personnel;
	private personne personne;
	private int salaire;
	private fonction fonction;
	private int annee_arrivee;
	
	public int getId_personnel() {
		return id_personnel;
	}
	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}
	public personne getPersonne() {
		return personne;
	}
	public void setPersonne(personne personne) {
		this.personne = personne;
	}
	public int getSalaire() {
		return salaire;
	}
	public void setSalaire(int salaire) {
		this.salaire = salaire;
	}
	public fonction getFonction() {
		return fonction;
	}
	public void setFonction(fonction fonction) {
		this.fonction = fonction;
	}
	public int getAnnee_arrivee() {
		return annee_arrivee;
	}
	public void setAnnee_arrivee(int annee_arrivee) {
		this.annee_arrivee = annee_arrivee;
	}
	
	

}
