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
		
		return this;
	}
	
	/**
	 * Vérifie si il y a un alignement de 4 pion identique
	 * en diagonale, a l'horizontale et a la verticale
	 * @return true si il y a un alignement sinon false
	 */
	public boolean verifAlignement() {
		return false;
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
