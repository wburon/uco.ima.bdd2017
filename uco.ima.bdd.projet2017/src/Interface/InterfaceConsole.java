package Interface;

import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import DAO.FonctionDAO;
import DAO.PersonnelDAO;
import DAO.PersonneDAO;
import model.Fonction;
import model.Personne;
import model.Personnel;

public class InterfaceConsole {
	
	static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args) {
		boolean connexion = false;
		String mdp = null;
		String login;
		PersonnelDAO userDAO = new PersonnelDAO();
		int id;
		
		// verification pour la connexion au logiciel
		do{
			System.out.print("LOGIN :");
			login = clavier.next();
			System.out.print("\nPASSWORD : ");
			try {
				mdp = md5(clavier.next());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id = userDAO.findUser(login,mdp);
			if( id >= 0)
				connexion = true;
		}while(connexion);
		run(userDAO.find(id));
		clavier.close();
	}
	
	/**
	 * Definition du niveau d'accreditation de l'utilisateur
	 * @param user : utilisateur
	 */
	private static void run(Personnel user) {
		Fonction fonction = new Fonction();
		fonction = user.getFonction();
		switch(fonction.getNiveau_contrainte()){
		case 1: // big boss
			runBIGBOSS(user);
			break;
		case 2: // reception
			//runReception(user);
			break;
		case 3: // personnel de menage
			//runMenage(user);
			break;
		default : // reste de la populace
			
		}
		
	}

	/**
	 * Les options du big boss
	 * @param user
	 */
	private static void runBIGBOSS(Personnel user) {
		boolean Quitter = false;
		do {
			System.out.println("1- Opération sur le personnel");
			System.out.println("2- Opération sur la clientelle");
			System.out.println("3- Opération sur les hotels");
			System.out.println("4- Quitter");
			System.out.print("Que faisons-nous Monsieur ? ");
			int choix = clavier.nextInt();
			switch(choix){
			case 1:
				System.out.println("1- Ajouter du personnel");
				System.out.println("2- Modifier du personnel");
				System.out.println("3- Trouver un membre du personnel");
				System.out.println("4- Supprimer du personnel");
				System.out.print("Que faisons-nous Monsieur ? ");
				choix = clavier.nextInt();
				switch(choix){
				case 1:
					addPersonnel();
					break;
				case 2:
					modifiePersonnel();
					break;
				case 3:
					findPersonnel();
					break;
				case 4:
					removePersonnel();
					break;
				default:
					System.out.println("Il n'y a pas de choix superieure à 4 !");
				}
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			default:
				Quitter = true;
			}
			
		}while(Quitter == false);
		
	}

	/**
	 * On supprime du personnel à partir de son ID
	 */
	private static void removePersonnel() {
		PersonnelDAO personnelDao = new PersonnelDAO();
		Personnel personnel = new Personnel();
		System.out.println("Qui ne fait plus partie de l'hotel ?");
		System.out.print("ID :");
		personnel.setId_personnel(clavier.nextInt());
		if(personnelDao.delete(personnel))
			System.out.println("La personne à bien été supprimé !");
	}

	/**
	 * On cherche un membre du personnel par son ID
	 */
	private static void findPersonnel() {
		PersonnelDAO personnelDao = new PersonnelDAO();
		Personnel personnel = new Personnel();
		System.out.println("Qui cherchons-nous ?");
		System.out.print("ID :");
		personnel = personnelDao.find(clavier.nextInt());
		System.out.println(personnel.toString());
	}

	private static void modifiePersonnel() {
		PersonnelDAO personnelDao = new PersonnelDAO();
		Personnel personnel = new Personnel();
		System.out.println("Qui allons-nous modifier ?");
		System.out.println("ID : ");
		personnel = personnelDao.find(clavier.nextInt());
		System.out.println(personnel.toString());
		// definir ce qu'on doit motifier
		
		
	}

	/**
	 * On ajoute un membre du personnel et par l'a même occasion une personne
	 */
	private static void addPersonnel() {
		PersonneDAO personneDao = new PersonneDAO();
		PersonnelDAO personnelDao = new PersonnelDAO();
		FonctionDAO fonctionDao = new FonctionDAO();
//		String dateFormat = "dd/MM/yyyy";
		
		// Création de la personne
		Personne personne = new Personne();
		personne.setId_personne(personneDao.maxId());
		System.out.print("Nom : ");
		personne.setNom(clavier.next());
		System.out.print("\nPrenom : ");
		personne.setPrenom(clavier.next());
		System.out.print("\nVille : ");
		personne.setVille(clavier.next());
		System.out.print("\nCode Postal : ");
		personne.setCode_postal(clavier.nextInt());
		System.out.print("\nAdresse : ");
		personne.setAdresse(clavier.next());
//		System.out.print("\nDate de naissance :");
//		personne.setDate_de_naissance();
		personneDao.create(personne);
		
		
		// Création du personnel
		Personnel personnel = new Personnel();
		personnel.setId_personnel(personnelDao.maxId());
		personnel.setPersonne(personne);
		System.out.print("\nSalaire : ");
		personnel.setSalaire(clavier.nextInt());
		System.out.print("\nFonction(1.,2.,3.) : "); // ajouter les fonctions possibles
		personnel.setFonction(fonctionDao.find(clavier.nextInt()));
		System.out.print("\nAnnee d'arrivée : ");
		personnel.setAnnee_arrivee(clavier.nextInt());
		System.out.print("\nMot de Passe : ");
		personnel.setPassword(clavier.next());
		personnelDao.create(personnel);
	}

	

	/*
	 * hachage du mot de passe
	 */
	public static String md5(String password) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

}
