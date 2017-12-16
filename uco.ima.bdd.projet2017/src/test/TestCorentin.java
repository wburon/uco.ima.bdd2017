package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import DAO.FonctionDAO;
import Singleton.SingletonConnection;
import model.Fonction;

public class TestCorentin {

	public static void main(String[] args) {
		Fonction f = new Fonction();
		FonctionDAO fDAO = new FonctionDAO();
		
		ArrayList<Fonction> list = new ArrayList<Fonction>();
		
		Connection conn = SingletonConnection.getConnection();
		try {
			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM fonction");
			
			while(result.next()){
				f.setId_fonction(result.getInt("id_fonction"));
				f.setNom(result.getString("nom"));
				f.setNiveau_contrainte(result.getInt("niveau_contrainte"));
				
				list.add(f);
				f=new Fonction();
			}
			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i).getNom());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
