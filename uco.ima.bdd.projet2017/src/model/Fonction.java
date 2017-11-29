package model;

public class Fonction {
	
	private int id_fonction;
	private String nom;
	private int niveau_contrainte;
	
	public int getId_fonction() {
		return id_fonction;
	}
	public void setId_fonction(int id_fonction) {
		this.id_fonction = id_fonction;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNiveau_contrainte() {
		return niveau_contrainte;
	}
	public void setNiveau_contrainte(int niveau_contrainte) {
		this.niveau_contrainte = niveau_contrainte;
	}

}
