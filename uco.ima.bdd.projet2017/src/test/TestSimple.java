package test;

import java.util.Date;

import DAO.FonctionDAO;
import model.Fonction;

public class TestSimple {

	public static void main(String[] args) {
		FonctionDAO fDAO = new FonctionDAO();
		int a=fDAO.renvoieId("Agent d entretien");
		System.out.println(a);
		
		Fonction f=fDAO.find(a);
		System.out.println(f.getNom());
		
	}

}
