package model;

public class Personnel {
	
	private int id_personnel;
	private Personne personne;
	private double salaire;
	private Fonction fonction;
	private int annee_arrivee;
	private String password;
	private String login;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId_personnel() {
		return id_personnel;
	}
	public void setId_personnel(int id_personnel) {
		this.id_personnel = id_personnel;
	}
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public double getSalaire() {
		return salaire;
	}
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	public Fonction getFonction() {
		return fonction;
	}
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	public int getAnnee_arrivee() {
		return annee_arrivee;
	}
	public void setAnnee_arrivee(int annee_arrivee) {
		this.annee_arrivee = annee_arrivee;
	}

}
