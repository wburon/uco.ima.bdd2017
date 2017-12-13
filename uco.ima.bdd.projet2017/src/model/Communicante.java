package model;

public class Communicante {
	
	private int id_communicante;
	private Chambre c1;
	private Chambre c2;
	
	public int getId_communicante() {
		return id_communicante;
	}
	public void setId_communicante(int id_communicante) {
		this.id_communicante = id_communicante;
	}	
	public Chambre getC1() {
		return c1;
	}
	public void setC1(Chambre c1) {
		this.c1 = c1;
	}
	public Chambre getC2() {
		return c2;
	}
	public void setC2(Chambre c2) {
		this.c2 = c2;
	}

}
