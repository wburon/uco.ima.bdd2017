package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import DAO.FonctionDAO;
import DAO.PersonneDAO;
import DAO.PersonnelDAO;
import Singleton.SingletonConnection;
import model.Fonction;
import model.Personnel;

public class TestCorentin {

	public static void main(String[] args) {
		
		FonctionDAO fDAO=new FonctionDAO();
		PersonnelDAO pDAO=new PersonnelDAO();
		PersonneDAO eDAO=new PersonneDAO();
		ArrayList<Personnel> listPerso = new ArrayList<Personnel>();
		
		listPerso=pDAO.ListPersonnel();
		
		
		
		Personnel p = new Personnel();
		
		p.setAnnee_arrivee(2015);
		p.setFonction(fDAO.find(1));
		p.setId_personnel(1);
		p.setPersonne(eDAO.find(11));
		p.setPassword("cc");
		p.setSalaire(3000);
		p.setLogin("azerty");
		
		
		listPerso.set(1, p);
		
		Personnel p2=listPerso.get(1);
		
		System.out.println(p2.getPassword());
		System.out.println(p2.getLogin());
	}

}
