package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Singleton.SingletonConnection;
import model.Personne;

public class PersonneDAO extends DAO<Personne> {

	Connection SC = SingletonConnection.getConnection();
	
	@Override
	public boolean create(Personne obj) {
		try {

			PreparedStatement prepare = SC
					.prepareStatement("Insert into personne values (?,?,?,?,?,?,?);");

			prepare.setInt(1, obj.getId_personne());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getPrenom());
			prepare.setString(4, obj.getVille());
			prepare.setInt(5, obj.getCode_postal());
			prepare.setString(6, obj.getAdresse());
			prepare.setDate(7, obj.getDate_de_naissance());

			prepare.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Personne obj) {
		
		try{
			PreparedStatement prepare =SC.prepareStatement("Delete from personne where id_personne=?");

			prepare.setInt(1, obj.getId_personne());
			
			prepare.executeUpdate();
			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Personne obj) {
		try{
			PreparedStatement prepare=SC.prepareStatement("Update personne set nom=?, prenom=?, ville=?, code_postal=?, adresse=?, date_de_naissance=? where id_personne=?");
			
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getPrenom());
			prepare.setString(3, obj.getVille());
			prepare.setInt(4, obj.getCode_postal());
			prepare.setString(5, obj.getAdresse());
			prepare.setDate(6, obj.getDate_de_naissance());
			prepare.setInt(7, obj.getId_personne());
			
			prepare.executeUpdate();
			return true;	
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Personne find(int id) {
		Personne personne = new Personne();
		try {
			PreparedStatement prepare = SC.prepareStatement("SELECT * FROM personne where id_personne = ?",  ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			prepare.setInt(1, id);
			ResultSet result = prepare.executeQuery();
			
			if(result.first()){
				personne.setId_personne(result.getInt("id_personne"));
				personne.setNom(result.getString("nom"));
				personne.setPrenom(result.getString("prenom"));
				personne.setVille(result.getString("ville"));
				personne.setCode_postal(result.getInt("code_postal"));
				personne.setAdresse(result.getString("adresse"));
				personne.setDate_de_naissance(result.getDate("date_de_naissance"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personne;
	}

	public int maxId() {
		Statement state;
		int nbRow=0;
		try {
			state = SC.createStatement();
			ResultSet nbLigne = state.executeQuery("SELECT MAX(id_personne) FROM personne");
			nbLigne.next();
			nbRow = nbLigne.getInt(1) + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nbRow;
	}

}
