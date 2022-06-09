package tests;

import java.util.Arrays;

import application.Partie;
import junit.framework.*;

public class TestPartieJUn extends TestCase {

	public void testPartie() {

		/*Test pour des noms de joueur correct*/
		try {
			Partie partieTest = new Partie("Jean", "Hercule");
		} catch (Exception errNomJoueur) {
			fail();
		}
		
		/*Test pour des noms de joueur null*/
		try {
			Partie partieTest = new Partie(null, "Hercule");
			fail("Nom - Null");
		} catch (NullPointerException errNomJoueur) {
		}
		
		try {
			Partie partieTest = new Partie("Jean", null);
			fail("Nom - Null");
		} catch (NullPointerException errNomJoueur) {
		}
		
		try {
			Partie partieTest = new Partie(null, null);
			fail("Nom - Null");
		} catch (NullPointerException errNomJoueur) {
		}
		
		/*Test pour des noms de joueur vide*/
		
		try {
			Partie partieTest = new Partie("", "Jean");
			fail("Nom - Vide");
		} catch (NullPointerException errNomJoueur) {
		}
		
		try {
			Partie partieTest = new Partie("Hercule", "");
			fail("Nom - Null");
		} catch (NullPointerException errNomJoueur) {
		}
		
		try {
			Partie partieTest = new Partie("", "");
			fail("Nom - Null");
		} catch (NullPointerException errNomJoueur) {
		}
	
	}

	public void testAjouterPion() {
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		/* Test si les paramètres sont incorrects */
		
		// Joueur invalide
		try {
			partieTest.ajouterPion(3, 2);
			fail("Joueur - Invalide");
		} catch (IllegalArgumentException errJoueurInvalide) {
		}
		
		try {
			partieTest.ajouterPion(0, 5);
			fail("Joueur - Invalide");
		} catch (IllegalArgumentException errJoueurInvalide) {
		}
		
		//Colonne invalide
		try {
			partieTest.ajouterPion(1, 7);
			fail("Colonne - Invalide");
		} catch (IllegalArgumentException errColonneInvalide) {
		}
		
		try {
			partieTest.ajouterPion(2, -1);
			fail("Colonne - Invalide");
		} catch (IllegalArgumentException errColonneInvalide) {
		}
		
		//Colonne et joueur invalide
		try {
			partieTest.ajouterPion(6, -5);
			fail("Colonne & Joueur - Invalide");
		} catch (IllegalArgumentException errColonneInvalide) {
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
		System.out.println(partieTest);
		assertEquals(attendu[1][0], partieTest.getGrille()[1][0]);
		
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
		assertEquals(attendu2[1], partieTest.getGrille()[1]);
	}

	public void testVerifierAlignement() {
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		/* Tests Diagonales */
		
		partieTest = new Partie("Michel", "Vincent");
		
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 0), 0);
		System.out.println(partieTest.toString());
		partieTest.ajouterPion(2, 1);
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 1), 0);
		partieTest.ajouterPion(2, 2);
		partieTest.ajouterPion(2, 2);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 2), 0);
		partieTest.ajouterPion(2, 3);
		partieTest.ajouterPion(2, 3);
		partieTest.ajouterPion(2, 3);
		partieTest.ajouterPion(1, 3);
		assertEquals(partieTest.verifierAlignement(3, 3), 1);
		
		/* Tests Lignes */
		
		partieTest = new Partie("Paul", "Pierre");
		
		// Test Ligne no1
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 0), 0);
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 0), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 0), 0);
		partieTest.ajouterPion(1, 3);
		assertEquals(partieTest.verifierAlignement(3, 0), 1);
		
		//TestLigne no2
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 1), 0);
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 1), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 1), 0);
		partieTest.ajouterPion(1, 3);
		assertEquals(partieTest.verifierAlignement(3, 1), 1);
		
		//TestLigne no3
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 2), 0);
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 2), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 2), 0);
		partieTest.ajouterPion(1, 3);
		assertEquals(partieTest.verifierAlignement(3, 2), 1);
		
		/* Tests Colonnes */
		
		partieTest = new Partie("Michel", "Vincent");
		
		// Test Colonne no1
		
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 0), 0);
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 1), 0);
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 2), 0);
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 3), 1);
		 
		// Test Colonne no3
		
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 0), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 1), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 2), 0);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 3), 1);
		
		// Test Colonne no2
		
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 0), 0);
		partieTest.ajouterPion(2, 1);
		assertEquals(partieTest.verifierAlignement(1, 1), 0);
		partieTest.ajouterPion(2, 1);
		assertEquals(partieTest.verifierAlignement(1, 2), 0);
		partieTest.ajouterPion(2, 1);
		assertEquals(partieTest.verifierAlignement(1, 3), 0);
		partieTest.ajouterPion(2, 1);
		assertEquals(partieTest.verifierAlignement(1, 4), 2);


	}

	public void testVerifierDiagonale() {
		/* Tester dans testVerifierAlignement*/
	}

	public void testVerifierColonne() {
		/* Tester dans testVerifierAlignement*/
	}

	public void testVerifierLigne() {
		/* Tester dans testVerifierAlignement*/
	}

	public void testEstComplet() {
		fail("Not yet implemented");
	}

	public void testGetGrille() {
		
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
			assertEquals(attendu[aTester], partieTest.getGrille()[aTester]);
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
		
		for (int aTester = 0; 
			 aTester < partieTest.getGrille().length ; 
			 aTester++) {
			
			assertEquals(attendu2[aTester], partieTest.getGrille()[aTester]);
		}
	}

	public void testToString() {
		fail("Not yet implemented");
	}

}
