package Interface;

import java.security.MessageDigest;
import java.util.Scanner;

import DAO.PersonnelDAO;
import DAO.personneDAO;
import model.Fonction;
import model.Personnel;

public class InterfaceConsole {
	
	static Scanner clavier = new Scanner(System.in);

	public static void main(String[] args) {
		
		
		boolean connexion = false;
		String mdp = null;
		int login;
		PersonnelDAO userDAO = new PersonnelDAO();
		Personnel user = new Personnel();
		
		
		// verification pour la connexion au logiciel
		do{
			System.out.print("LOGIN :");
			login = clavier.nextInt();
			System.out.print("\nPASSWORD : ");
			try {
				mdp = md5(clavier.next());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user = userDAO.find(login);
			if(user != null && user.getPassword().equals(mdp)){
				connexion = true;
			}	
		}while(connexion);
		run(user);
		
		
		clavier.close();
	}
	
	/**
	 * Definition du niveau d'accreditation de l'utilisateur
	 * @param user : utilisateur
	 */
	private static void run(Personnel user) {
		Fonction fonction = new Fonction();
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

	private static void removePersonnel() {
		// TODO Auto-generated method stub
		
	}

	private static void findPersonnel() {
		// TODO Auto-generated method stub
		
	}

	private static void modifiePersonnel() {
		// TODO Auto-generated method stub
		
	}

	private static void addPersonnel() {
		personneDAO personne = new personneDAO();
		PersonnelDAO personnel = new PersonnelDAO();
		System.out.println("");
		//int id_personne = maxId(table);
		
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
