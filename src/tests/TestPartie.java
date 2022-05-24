/*
 * 
 */

package tests;

import java.util.Arrays;

import application.Partie;

/**
 * Test unitaire de {@link logiciel.Partie}
 * @author vincent.faure
 * @author romain.courbaize
 */
public class TestPartie {

	/**
	 * Lancement des tests unitaires
	 * @param args
	 */
	public static void main(String[] args) {
		
//		System.out.println(testPartieStringString() ? "Test ok " : "Echec test");
//		
//		System.out.println(testAjouterPion() ? "Test ok " : "Echec test");
//		
//		System.out.println(testGetGrille() ? "Test ok " : "Echec test");
		
		System.out.println(testVerifierAlignement() ? "Test ok " : "Echec test");
		
	}

	/**
	 * Test unitaire de {@link application.ModeJeu#ModeJeu(String, String)}
	 */
	private static boolean testPartieStringString() {
		
		boolean ok;
		
		/*Test pour des noms de joueur correct*/
		try {
			Partie partieTest = new Partie("Jean", "Hercule");
			ok = true;
		} catch (NullPointerException errNomJoueur) {
			ok = false;
		}
		
		/*Test pour des noms de joueur null*/
		try {
			Partie partieTest = new Partie(null, "Hercule");
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
		
		try {
			Partie partieTest = new Partie("Jean", null);
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
		
		try {
			Partie partieTest = new Partie(null, null);
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
		
		/*Test pour des noms de joueur vide*/
		
		try {
			Partie partieTest = new Partie("", "Jean");
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
		
		try {
			Partie partieTest = new Partie("Hercule", "");
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
		
		try {
			Partie partieTest = new Partie("", "");
			ok &= false;
		} catch (NullPointerException errNomJoueur) {
			ok &= true;
		}
	
		return ok;	
	}
	
	/**
	 * Test unitaires de {@link application.Partie#ajouterPion(int, int)}
	 * @return true si le test est bon sinon false
 	 */
	private static boolean testAjouterPion() {
		
		boolean ok;
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		/*Test si les paramètres sont incorrect*/
		
		//Joueur invalide
		try {
			partieTest.ajouterPion(3, 2);
			ok = false;
		} catch (IllegalArgumentException errJoueurInvalide) {
			ok = true;
		}
		
		try {
			partieTest.ajouterPion(0, 5);
			ok &= false;
		} catch (IllegalArgumentException errJoueurInvalide) {
			ok &= true;
		}
		
		//Colonne invalide
		try {
			partieTest.ajouterPion(1, 7);
			ok &= false;
		} catch (IllegalArgumentException errColonneInvalide) {
			ok &= true;
		}
		
		try {
			partieTest.ajouterPion(2, -1);
			ok &= false;
		} catch (IllegalArgumentException errColonneInvalide) {
			ok &= true;
		}
		
		//Colonne et joueur invalide
		try {
			partieTest.ajouterPion(6, -5);
			ok &= false;
		} catch (IllegalArgumentException errColonneInvalide) {
			ok &= true;
		}
		
		/*Test de l'ajout d'un pion*/
		
		//Ajout si il n'y a pas de pion dans la colonne choisie
		int[][] attendu = {
				{0,0,0,0,0,0},
				{1,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(1, 1);
		ok &= Arrays.equals(attendu[1], partieTest.getGrille()[1]);
		
		//Ajout si il y a déjà un pion dans la colonne
		int[][] attendu2 = {
				{0,0,0,0,0,0},
				{1,2,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(2, 1);
		ok &= Arrays.equals(attendu2[1], partieTest.getGrille()[1]);
		
		return ok;
	}
	
	/**
	 * Tests unitaires de {@link application.Partie#getGrille()}
	 * @return true si les tests sont bon sinon false
	 */
	private static boolean testGetGrille() {
		
		boolean ok = true;
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		//Test avec une grille vide
		int[][] attendu = {
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		for (int aTester = 0; aTester < partieTest.getGrille().length; aTester++) {
			ok &= Arrays.equals(attendu[aTester], partieTest.getGrille()[aTester]);
		}
		
		//Test avec une grille contenant des pion
		
		int[][] attendu2 = {
				{0,0,0,0,0,0},
				{1,2,0,0,0,0},
				{2,1,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(1, 1);
		partieTest.ajouterPion(2, 2);
		partieTest.ajouterPion(1, 2);
		partieTest.ajouterPion(2, 1);
		
		for (int aTester = 0; aTester < partieTest.getGrille().length; aTester++) {
			ok &= Arrays.equals(attendu2[aTester], partieTest.getGrille()[aTester]);
		}
		
		return ok;	
	}
	
	/**
	 * Tests unitaires de {@link application.Partie#verifierAlignement(int, int)}
	 * @return
	 */
	private static boolean testVerifierAlignement() {
		
		boolean ok = true;
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		/* Tests Lignes */
		
		// Test Ligne no1
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 0) == 0;
		partieTest.ajouterPion(1, 1);
		ok &= partieTest.verifierAlignement(1, 0) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 0) == 0;
		partieTest.ajouterPion(1, 3);
		ok &= partieTest.verifierAlignement(3, 0) == 1;
		
		//TestLigne no2
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 1) == 0;
		partieTest.ajouterPion(1, 1);
		ok &= partieTest.verifierAlignement(1, 1) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 1) == 0;
		partieTest.ajouterPion(1, 3);
		ok &= partieTest.verifierAlignement(3, 1) == 1;
		
		//TestLigne no3
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 2) == 0;
		partieTest.ajouterPion(1, 1);
		ok &= partieTest.verifierAlignement(1, 2) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 2) == 0;
		partieTest.ajouterPion(1, 3);
		ok &= partieTest.verifierAlignement(3, 2) == 1;
		
		/* Tests Colonnes */
		
		partieTest = new Partie("Michel", "Vincent");
		
		// Test Colonne no1
		
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 0) == 0;
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 1) == 0;
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 2) == 0;
		partieTest.ajouterPion(1, 0);
		ok &= partieTest.verifierAlignement(0, 3) == 1;
		 
		// Test Colonne no3
		
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 0) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 1) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 2) == 0;
		partieTest.ajouterPion(1, 2);
		ok &= partieTest.verifierAlignement(2, 3) == 1;
		
		// Test Colonne no2
		
		partieTest.ajouterPion(1, 1);
		ok &= partieTest.verifierAlignement(1, 0) == 0;
		partieTest.ajouterPion(2, 1);
		ok &= partieTest.verifierAlignement(1, 1) == 0;
		partieTest.ajouterPion(2, 1);
		ok &= partieTest.verifierAlignement(1, 2) == 0;
		partieTest.ajouterPion(2, 1);
		ok &= partieTest.verifierAlignement(1, 3) == 0;
		partieTest.ajouterPion(2, 1);
		ok &= partieTest.verifierAlignement(1, 4) == 2;
		
		return ok;
	}
	
	
//	/**
//	 * Tests unitaires de {@link application.Partie#verifAlignement()}
//	 * @return true si les test sont bon sinon false
//	 */
//	private static boolean testVerifierAlignement() {
//		
//		boolean ok;
//		
//		
//		
//		return ok;
//	}
	
}
