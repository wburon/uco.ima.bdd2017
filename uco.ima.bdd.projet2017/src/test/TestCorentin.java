package test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

		FonctionDAO fDAO = new FonctionDAO();
		Fonction f=fDAO.find(3);
		System.out.println(f);
	}

}
