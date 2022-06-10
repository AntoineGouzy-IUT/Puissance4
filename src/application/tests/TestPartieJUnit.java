/**
 * TestPartieJUnit.java
 * BUT INFO 1 2021/2022
 * pas de droits d'auteur ni de copyright
 */
package application.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Partie;

/**
 * @author romain.courbaize
 * @author antoine.gouzy
 * @author vincent.faure
 */
class TestPartieJUnit {

	/**
	 * Test method for {@link application.Partie#defense()}.
	 */
	@Test
	void testDefense() {
		
		Partie testPartie;
		
		/* Ligne */
		testPartie = new Partie("Jean", "Ordinateur");
		
		testPartie.ajouterPion(1, 1);
		assertEquals(testPartie.defense(), -1);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(1, 3);
		assertEquals(testPartie.defense(), 0);
		testPartie.ajouterPion(2, 0);
		assertEquals(testPartie.defense(), 4);
		
		/* Colonne */
		testPartie = new Partie("Jean", "Ordinateur");
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(1, 3);
		assertEquals(testPartie.defense(), 3);
		
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		assertEquals(testPartie.defense(), 1);

		/* Diagonale */
		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut droite
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		assertEquals(testPartie.defense(), 4);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas gauche
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(1, 4);
		assertEquals(testPartie.defense(), 1);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut gauche
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		assertEquals(testPartie.defense(), 1);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas droite
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(1, 1);
		assertEquals(testPartie.defense(), 4);
	}
	
	/**
	 * Test method for {@link application.Partie#attaque()}.
	 */
	@Test
	void testAttaque() {
		
		Partie testPartie;
		
		/* Test 3 pions déjà alignés */
		
		/* Ligne */
		testPartie = new Partie("Jean", "Paul");
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(2, 3);
		assertEquals(testPartie.attaque(), 0);
		testPartie.ajouterPion(2, 0);
		assertEquals(testPartie.attaque(), 4);
		
		/* Colonne */
		testPartie = new Partie("Jean", "Paul");
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		assertEquals(testPartie.attaque(), 4);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		assertEquals(testPartie.attaque(), 1);
		
		/* Diagonale */
		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut droite
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(1, 4);
		assertEquals(testPartie.attaque(), 4);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas gauche
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(1, 4);
		testPartie.ajouterPion(2, 4);
		assertEquals(testPartie.attaque(), 1);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut gauche
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		assertEquals(testPartie.attaque(), 1);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas droite
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(1, 1);
		testPartie.ajouterPion(2, 1);
		assertEquals(testPartie.attaque(), 4);
		
		/* Test 2 pions déjà alignés */
		
		/* Ligne */
		testPartie = new Partie("Jean", "Paul");
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 2);
		assertEquals(testPartie.attaque(), 0);
		testPartie.ajouterPion(2, 0);
		assertEquals(testPartie.attaque(), 3);
		
		/* Colonne */
		testPartie = new Partie("Jean", "Paul");
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(2, 4);
		assertEquals(testPartie.attaque(), 4);
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(2, 1);
		assertEquals(testPartie.attaque(), 1);
		
		/* Diagonale */
		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut droite
		testPartie.ajouterPion(2, 1);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(1, 3);
		assertEquals(testPartie.attaque(), 3);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas gauche
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(2, 3);
		assertEquals(testPartie.attaque(), 1);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale haut gauche
		testPartie.ajouterPion(2, 4);
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(1, 2);
		assertEquals(testPartie.attaque(), 2);

		testPartie = new Partie("Jean", "Ordinateur"); //Diagonale bas droite
		testPartie.ajouterPion(1, 3);
		testPartie.ajouterPion(2, 3);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(1, 2);
		testPartie.ajouterPion(2, 2);
		assertEquals(testPartie.attaque(), 4);
	}

	/**
	 * Test method for {@link application.Partie#getNomJoueur1()}.
	 */
	@Test
	void testGetNomJoueur1() {
		
		Partie testPartie = new Partie("Jean", "Paul");
		assertEquals(testPartie.getNomJoueur1(), "Jean");
		
		testPartie = new Partie("u", "Paul");
		assertEquals(testPartie.getNomJoueur1(), "u");
	}

	/**
	 * Test method for {@link application.Partie#getNomJoueur2()}.
	 */
	@Test
	void testGetNomJoueur2() {
		
		Partie testPartie = new Partie("Jean", "Paul");
		assertEquals(testPartie.getNomJoueur2(), "Paul");
		
		testPartie = new Partie("Jean", "u");
		assertEquals(testPartie.getNomJoueur2(), "u");
	}

	/**
	 * Test method for {@link application.Partie#save()}.
	 * Test method for {@link application.Partie#load()}.
	 */
	@Test
	void testSaveLoad() {

		//Aucun jeton
		Partie partieASave = new Partie("Jean", "Paul");
		
		partieASave.save();
		
		Partie partieATester = new Partie("j", "j");
		partieATester.load();
		
		assertEquals(partieASave.getGrille(), partieATester.getGrille());
		assertEquals(partieASave.getNomJoueur1(), partieATester.getNomJoueur1());
		assertEquals(partieASave.getNomJoueur2(), partieATester.getNomJoueur2());
		
		//Avec jetons
		partieASave = new Partie("Jean", "Paul");
		partieASave.ajouterPion(1, 0);  
		
		partieASave.save();
		
		partieATester = new Partie("j", "j");
		partieATester.load();
		
		assertEquals(partieASave.getGrille(), partieATester.getGrille());
		assertEquals(partieASave.getNomJoueur1(), partieATester.getNomJoueur1());
		assertEquals(partieASave.getNomJoueur2(), partieATester.getNomJoueur2());
		
	}
	
	/**
	 * Test method for {@link application.Partie#getGrille()}.
	 */
	@Test
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
			assertArrayEquals(attendu[aTester], partieTest.getGrille()[aTester]);
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
			
			assertArrayEquals(attendu2[aTester], partieTest.getGrille()[aTester]);
		}
	}
	
	/**
	 * Test method for {@link application.Partie#Partie(String, String)}.
	 */
	@Test
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

	/**
	 * Test method for {@link application.Partie#ajouterPion(int, int)}.
	 */
	@Test
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
		assertArrayEquals(attendu2[1], partieTest.getGrille()[1]);
		
		int[][] attendu3 = {
				{0,0,0,0,0,0},
				{1,2,1,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(1, 1);
		assertArrayEquals(attendu3[1], partieTest.getGrille()[1]);

		int[][] attendu4 = {
				{0,0,0,0,0,0},
				{1,2,1,2,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(2, 1);
		assertArrayEquals(attendu4[1], partieTest.getGrille()[1]);

		int[][] attendu5 = {
				{0,0,0,0,0,0},
				{1,2,1,2,1,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(1, 1);
		assertArrayEquals(attendu5[1], partieTest.getGrille()[1]);

		int[][] attendu6 = {
				{0,0,0,0,0,0},
				{1,2,1,2,1,2},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}
		};
		
		partieTest.ajouterPion(2, 1);
		assertArrayEquals(attendu6[1], partieTest.getGrille()[1]);
		
		//Colonne pleine
		try {
			partieTest.ajouterPion(1, 1);
			fail("Erreur - Colonne pleine");
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		
	}

	/**
	 * Test method for {@link application.Partie#verifierAlignement(int, int)}.
	 */
	@Test
	public void testVerifierAlignement() {
		
		Partie partieTest = new Partie("Michel", "Vincent");
		
		/* Tests Diagonales */
		
		partieTest = new Partie("Michel", "Vincent");
		
		partieTest.ajouterPion(1, 0);
		assertEquals(partieTest.verifierAlignement(0, 0), 0);
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

		partieTest = new Partie("Michel", "Vincent");
		partieTest.ajouterPion(1, 3);
		assertEquals(partieTest.verifierAlignement(3, 0), 0);
		partieTest.ajouterPion(2, 2);
		partieTest.ajouterPion(1, 2);
		assertEquals(partieTest.verifierAlignement(2, 1), 0);
		partieTest.ajouterPion(2, 1);
		partieTest.ajouterPion(2, 1);
		partieTest.ajouterPion(1, 1);
		assertEquals(partieTest.verifierAlignement(1, 2), 0);
		partieTest.ajouterPion(2, 0);
		partieTest.ajouterPion(2, 0);
		partieTest.ajouterPion(2, 0);
		partieTest.ajouterPion(1, 0);
		System.out.println(partieTest);
		assertEquals(partieTest.verifierAlignement(0, 3), 1);
		
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
	

	/**
	 * Test method for {@link application.Partie#estComplet(int)}.
	 */
	@Test
	public void testEstComplet() {
		
		Partie partieTest = new Partie("Michel", "Vincent");

		assertFalse(partieTest.estComplet(1));
		partieTest.ajouterPion(1, 1);
		partieTest.ajouterPion(1, 1);
		partieTest.ajouterPion(1, 1);
		assertFalse(partieTest.estComplet(1));
		partieTest.ajouterPion(1, 1);
		partieTest.ajouterPion(1, 1);
		partieTest.ajouterPion(1, 1);
		assertTrue(partieTest.estComplet(1));
	}
}
