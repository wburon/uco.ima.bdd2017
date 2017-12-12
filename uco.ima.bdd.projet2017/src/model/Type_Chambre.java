package model;

public class Type_Chambre {
	
	private int id_type_chambre;
	private String nom;
	private int nb_piece;
	private int nb_lit_double;
	private int nb_lit_simple;
	
	public int getId_type_chambre() {
		return id_type_chambre;
	}
	public void setId_type_chambre(int id_type_chambre) {
		this.id_type_chambre = id_type_chambre;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getNb_piece() {
		return nb_piece;
	}
	public void setNb_piece(int nb_piece) {
		this.nb_piece = nb_piece;
	}
	public int getNb_lit_double() {
		return nb_lit_double;
	}
	public void setNb_lit_double(int nb_lit_double) {
		this.nb_lit_double = nb_lit_double;
	}
	public int getNb_lit_simple() {
		return nb_lit_simple;
	}
	public void setNb_lit_simple(int nb_lit_simple) {
		this.nb_lit_simple = nb_lit_simple;
	}

}
