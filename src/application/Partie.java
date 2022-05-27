/*
 * 
 */

package application;

/**
 * 
 * @author vincent.faure
 *
 */
public class Partie {
	
	/**Grille de cette partie*/
	private static int[][] grille;
	
	/**Pion du joueur 1*/
	private final int JOUEUR1 = 1;
	
	/**Pion du joueur 2*/
	private final int JOUEUR2 = 2;
	
	/**Nom du premier joueur*/
	private final String NOM_JOUEUR_1;
	
	/**Nom du deuxième joueur*/
	private final String NOM_JOUEUR_2;
	
	/**
	 * Definition de cette partie
	 * throws NullPointerException Si le nom des joueurs est null 
	 *                             Si le nom des joueurs est vide
	 */
	public Partie(String nomJoueur1, String nomJoueur2) {
		
		if(nomJoueur1.isBlank() || nomJoueur2.isBlank()) {
			throw new NullPointerException("Nom du joueur null ou vide !");
		}
		
		NOM_JOUEUR_1 = nomJoueur1;
		NOM_JOUEUR_2 = nomJoueur2;
		
		grille = new int[7][6];
	}
	
	/**
	 * Ajout d'un pion dans cette partie
	 * @param joueur   numéro du joueur qui ajoute ce pion
	 * @param colonne  numéro de la colonne dans laquelle il ajoute ce pion
	 * @return cette partie avec le pion ajouter 
	 * @throws IllegalArgumentException  Si le numéro de la colonne est incorrect 
	 *                                   Si le numéro du joueur est incorrect
	 */
	public Partie ajouterPion(int joueur, int colonne) {
		
		int noVide;
		
		if (joueur < 1 || joueur > 2) {
			throw new IllegalArgumentException("Joueur invalide !");
		}
		if (colonne < 0 || colonne > 6) {
			throw new IllegalArgumentException("Colonne invalide !");
		}
		
		noVide = 0;
		
		while (grille[colonne][noVide] != 0) {
			noVide++;
		}
		
		grille[colonne][noVide] = joueur;
		
		verifierAlignement(colonne, noVide);
		
		return this;
	}
	
	/**
	 * Vérifie si il y a un alignement de 4 pion identique
	 * en diagonale, a l'horizontale et a la verticale
	 * @return true si il y a un alignement sinon false
	 */
	public int verifierAlignement(int colonne, int ligne) {
		
		int resultatColonne,
		    resultatLigne,
		    resultatDiagonale;
		
		resultatColonne = verifierColonne(colonne);
		resultatLigne = verifierLigne(ligne);
		resultatDiagonale = verifierDiagonal(colonne, ligne);
		
		if (resultatColonne != 0) {
			System.out.println(resultatColonne + "Colonne");
			return resultatColonne;
		}
		if (resultatLigne != 0) {
			System.out.println(resultatLigne + "Ligne");
			return resultatLigne;
		}
		if (resultatDiagonale != 0) {
			System.out.println(resultatDiagonale + "Diagonale");
			return resultatDiagonale;
		}
		
		System.out.println("Rien");
		return 0;
		
		
		
	}
	
	/**
	 * Permet la vérification de la colonne no colonne
	 * @param colonne numéro de la colonne à verifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la colonne</li>
	 *         </ul>
	 */
	public int verifierColonne(int colonne) {

		for (int noLigne = 0 ; noLigne <= 2 ; noLigne++) {
			if (grille[colonne][noLigne] == grille[colonne][noLigne + 1]
			    && grille[colonne][noLigne] == grille[colonne][noLigne + 2]
			    && grille[colonne][noLigne] == grille[colonne][noLigne + 3]) {

				return grille[colonne][noLigne];
			}
		}
		
		return 0;

	} 
	
	/**
	 * Permet la vérification de la ligne no ligne
	 * @param ligne numéro de la ligne à verifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la ligne</li>
	 *         </ul>
	 */
	public int verifierLigne(int ligne) {

		for (int noColonne = 0 ; noColonne <= 3 ; noColonne++) {
			if (grille[noColonne][ligne] == grille[noColonne + 1][ligne]
			    && grille[noColonne][ligne] == grille[noColonne + 2][ligne]
			    && grille[noColonne][ligne] == grille[noColonne + 3][ligne]) {

				return grille[noColonne][ligne];
			}
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int verifierDiagonal(int colonne, int ligne) {
		
		/* Diagonale Haut Droite - Bas Gauche*/
		
		int cptPion = 0;
		int index = 0;
		
		while (colonne - index >= 0 
			   && ligne - index >= 0
			   && grille[colonne][ligne] 
				  == grille[colonne - index][ligne - index]) {
			
			index++;
			cptPion++;
		}
		
		index = 1;
		
		while (colonne + index <= grille.length
			   && ligne + index <= grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne + index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		if (cptPion > 3) {
			System.out.println("Haut Droite - Bas Gauche");
			return grille[colonne][ligne];
		}
		
		/* Else : Diagonale Haut Gauche - Bas Droite*/
		
		index = 0;
		cptPion = 0;
		
		while (colonne - index >= 0
			   && ligne + index <= grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne - index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		index = 1;
		
		while (colonne + index <= grille.length
				   && ligne - index >= 0
				   && grille[colonne][ligne] 
					  == grille[colonne + index][ligne - index]) {
				
			    index++;
				cptPion++;
			}
		
		if (cptPion > 3) {
			System.out.println("Haut Gauche - Bas Droite");
			return grille[colonne][ligne];
		}
		
		return 0;
	}

	
	/**
	 * Test si la grille de cette partie est pleine
	 * @return true si la grille est pleine sinon false
	 */
	public boolean estComplet() {
		//TODO a completer
		return false;
	}
	
	/**
	 * 
	 * @return la grille de cette partie
	 */
	public int[][] getGrille() {
		return grille;
	}
	
	
	//TODO Effectuer les tests
	@Override
	public String toString() {
		
		String affiche = "";	
		for (int i = 5; i >= 0; i--) {
			affiche += "\n";
			for (int j = 0 ; j <= 6 ; j++) {
				affiche += grille[j][i];
			}
			
		}
		return affiche;
	}

}
