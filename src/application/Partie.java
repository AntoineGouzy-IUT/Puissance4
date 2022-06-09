/*
 * Partie.java                                   05/2022
 * BUT INFO 1 2021/2022
 * pas de droits d'auteur ni de copyright
 */

package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Partie de puissance 4 sur une grille de 7 x 6
 * @author vincent.faure
 * @author romain.courbaize
 * @author antoine.gouzy
 */
public class Partie implements Serializable {
	
	/** Grille de cette partie */
	private static int[][] grille;
	
	/** Nom du premier joueur */
	private String nomJoueur1;
	
	/** Nom du deuxi�me joueur */
	private String nomJoueur2;
	
	/**
	 * Definition de cette partie
	 * throws NullPointerException Si le nom des joueurs est null 
	 *                             Si le nom des joueurs est vide
	 */
	public Partie(String nomJoueur1, String nomJoueur2) {
		
		if(nomJoueur1.isBlank() || nomJoueur2.isBlank()) {
			throw new NullPointerException("Nom du joueur null ou vide !");
		}
		
		this.nomJoueur1 = nomJoueur1;
		this.nomJoueur2 = nomJoueur2;
		
		grille = new int[7][6];
	}
	
	/**
	 * Positionne le pion pour l'ordinateur
	 * Défend  - si le joueur humain a 3 pions alignés
	 * Attaque - si l'IA a 3 pions alignés, elle gagne
	 *           si non, elle complète la ligne, la colonne ou la diagonale
	 */
	public void ordinateur() {
		
		int resultatDefense;
		
		resultatDefense = defense();
		
		if (resultatDefense > -1) {
			ajouterPion(2, resultatDefense);
		}
		ajouterPion(2, attaque());
	}
	
	/**
	 * Calcul la position d'un pion pour défendre
	 * Si l'adversaire a 3 pions alignés alors elle va indiquer la position
	 * du jeton pour défendre
	 * @return <ul>
	 * 			<li>-1 s'il n'y a aucune ligne, diagonale ou colonne de 3 pions</li>
	 * 			<li>le numéro de la colonne s'il faut défendre</li>
	 * 		   </ul>
	 */
	public int defense() {
		
		int[][] grilleTmp;
		
		for (int noColonneATester = 0 ; noColonneATester < 7 ; noColonneATester++) {

			grilleTmp = copie(grille);
			if (ajouterPionIA(1, noColonneATester, grilleTmp, 4)) {
				
				return noColonneATester;
			};
		}
		return -1;
	}
	
	/**
	 * Permet de copier un tableau en 2 dimensions avec la méthode clone() 
	 * 
	 * @param aCopier Tableau que l'on veut copier
	 * @return La copie dans un tableau 
	 */
	private int[][] copie(int[][] aCopier) {
		
        if (aCopier == null) {
            return null;
        }
 
        int[][] copie = new int[aCopier.length][];
        for (int i = 0; i < aCopier.length; i++) {
        	
            copie[i] = aCopier[i].clone();
        }
 
        return copie;
    }
	
	/**
	 * Calcul la position d'un pion pour attaquer
	 * Attaque - si l'IA a 3 pions alignés, elle gagne
	 *           si non, elle complète la ligne, la colonne ou la diagonale
	 * @return <ul>
	 * 			<li>-1 s'il n'y a aucune ligne, diagonale ou colonne de 3 pions</li>
	 * 			<li>le numéro de la colonne s'il faut défendre</li>
	 * 		   </ul>
	 */
	public int attaque() {
		
		int[][] grilleTmp;
		
		/* Alignement de 4 Jetons */
		for (int noColonneATester = 0 ; noColonneATester < 7 ; noColonneATester++) {

			grilleTmp = copie(grille);
			if (ajouterPionIA(2, noColonneATester, grilleTmp, 4)) {
				
				return noColonneATester;
			};
		}

		/* Alignement de 3 Jetons */
		for (int noColonneATester = 0 ; noColonneATester < 7 ; noColonneATester++) {

			grilleTmp = copie(grille);
			if (ajouterPionIA(2, noColonneATester, grilleTmp, 3)) {
				
				return noColonneATester;
			};
		}

		/* Alignement de 2 Jetons */
		for (int noColonneATester = 0 ; noColonneATester < 7 ; noColonneATester++) {

			grilleTmp = copie(grille);
			if (ajouterPionIA(2, noColonneATester, grilleTmp, 3)) {
				
				return noColonneATester;
			};
		}
		Random rand = new Random();
		return rand.nextInt(7);
	}
	
	/**
	 * Ajout d'un pion factice dans cette partie sur une grille tampon
	 * et vérifie s'il y a possibilité pour le joueur de gagner
	 * @param joueur  numéro du joueur qui ajoute ce pion
	 * @param colonne axe horizontal du jeton 
	 * @param ligne   axe vertical du jeton
	 * @param grille  à vérifier
	 * 
	 * @return true si cette colonne est gagnante, false sinon
	 */
	private boolean ajouterPionIA(int joueur, int colonne, int[][] grille, 
			                      int nbJetons) {

		int noVide;
		
		noVide = 0;
		
		while (grille[colonne][noVide] != 0) {
			noVide++;
			
		}
		grille[colonne][noVide] = joueur;
		
		return verifierAlignement(colonne, noVide, grille, nbJetons);
	}
	
	/**
	 * Ajout d'un pion dans cette partie
	 * @param joueur   numéro du joueur qui ajoute ce pion
	 * @param colonne  axe horizontal du jeton 
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
	 * Vérifie si il y a un alignement de 4 pions identiques
	 * horizontal, vertical ou diagonal dans cette partie
	 * @param colonne axe horizontal du jeton 
	 * @param ligne   axe vertical du jeton
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la colonne</li>
	 *         </ul>
	 */
	public int verifierAlignement(int colonne, int ligne) {
		
		int resultatColonne,
		    resultatLigne,
		    resultatDiagonale;
		
		resultatColonne = verifierColonne(colonne);
		resultatLigne = verifierLigne(ligne);
		resultatDiagonale = verifierDiagonal(colonne, ligne);
		
		if (resultatColonne != 0) {
			return resultatColonne;
		}
		if (resultatLigne != 0) {
			return resultatLigne;
		}
		if (resultatDiagonale != 0) {
			return resultatDiagonale;
		}
		return 0;
	}
	
	/**
	 * Vérifie si il y a un alignement de 4 pions identiques
	 * horizontal, vertical ou diagonal dans une partie factive
	 * @param colonne axe horizontal du jeton 
	 * @param ligne   axe vertical du jeton
	 * @param grille  à vérifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la colonne</li>
	 *         </ul>
	 */
	private boolean verifierAlignement(int colonne, int ligne, int[][] grille, 
			                           int nbJetons) {
		
		if (nbJetons == 4) {
			return verifierColonne(colonne, grille) != 0
				   || verifierLigne(ligne, grille) != 0
				   || verifierDiagonal(colonne, ligne, grille, nbJetons) != 0 ;
		}
		
		if (nbJetons == 3) {
			return verifierColonne3Jetons(colonne, grille) != 0
				   || verifierLigne3Jetons(ligne, grille) != 0
				   || verifierDiagonal(colonne, ligne, grille, nbJetons) != 0 ;
		}
		
		if (nbJetons == 2) {
			return verifierColonne(colonne, grille) != 0
				   || verifierLigne(ligne, grille) != 0
				   || verifierDiagonal(colonne, ligne, grille, nbJetons) != 0 ;
		}
		return false;
	}
	
	/**
	 * Permet la vérification de la colonne no colonne dans cette partie
	 * @param colonne numéro de la colonne à verifier
	 * @param colonne axe horizontal du jeton 
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la colonne</li>
	 *         </ul>
	 */
	private int verifierColonne(int colonne) {

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
	 * Permet la vérification de la colonne no colonne
	 * @param colonne axe horizontal du jeton 
	 * @param grille  à vérifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la colonne</li>
	 *         </ul>
	 */
	private int verifierColonne(int colonne, int[][] grille) {

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
	 * Permet la vérification de la colonne no colonne
	 * @param colonne numéro de la colonne à verifier
	 * @param colonne axe horizontal du jeton 
	 * @param grille  à vérifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 3 est présent dans la colonne</li>
	 *         </ul>
	 */
	private int verifierColonne3Jetons(int colonne, int[][] grille) {

		for (int noLigne = 0 ; noLigne <= 2 ; noLigne++) {
			if (grille[colonne][noLigne] == grille[colonne][noLigne + 1]
			    && grille[colonne][noLigne] == grille[colonne][noLigne + 2]) {

				return grille[colonne][noLigne];
			}
		}
		
		return 0;
	} 
	
	/**
	 * Permet la vérification de la colonne no colonne
	 * @param colonne numéro de la colonne à verifier
	 * @param colonne axe horizontal du jeton 
	 * @param grille  à vérifier
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 2 est présent dans la colonne</li>
	 *         </ul>
	 */
	private int verifierColonne2Jetons(int colonne, int[][] grille) {

		for (int noLigne = 0 ; noLigne <= 2 ; noLigne++) {
			if (grille[colonne][noLigne] == grille[colonne][noLigne + 1]
			    && grille[colonne][noLigne] == grille[colonne][noLigne + 2]) {

				return grille[colonne][noLigne];
			}
		}
		
		return 0;
	} 
	
	/**
	 * Permet la vérification de la ligne no ligne
	 * @param ligne   axe vertical du jeton
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la ligne</li>
	 *         </ul>
	 */
	private int verifierLigne(int ligne) {

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
	  * Permet la vérification de la ligne no ligne
	  * @param ligne   axe vertical du jeton
	  * @param grille  à vérifier
	  * @return <ul>
	  *		 <li>Le numéro du joueur gagnant</li>
	  *		 <li>0 si aucun alignement de 4 est présent dans la ligne</li>
	  *         </ul>
	  */
	 private int verifierLigne(int ligne, int[][] grille) {

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
	  * Permet la vérification de la ligne no ligne
	  * @param ligne   axe vertical du jeton
	  * @param grille  à vérifier
	  * @return <ul>
	  *		 <li>Le numéro du joueur gagnant</li>
	  *		 <li>0 si aucun alignement de 3 est présent dans la ligne</li>
	  *         </ul>
	  */
	 private int verifierLigne3Jetons(int ligne, int[][] grille) {

		 for (int noColonne = 0 ; noColonne <= 3 ; noColonne++) {
			 if (grille[noColonne][ligne] == grille[noColonne + 1][ligne]
				 && grille[noColonne][ligne] == grille[noColonne + 2][ligne]) {

				 return grille[noColonne][ligne];
			 }
		 }

		 return 0;
	 }
		
	 /**
	  * Permet la vérification de la ligne no ligne
	  * @param ligne   axe vertical du jeton
	  * @param grille  à vérifier
	  * @return <ul>
	  *		 <li>Le numéro du joueur gagnant</li>
	  *		 <li>0 si aucun alignement de 2 est présent dans la ligne</li>
	  *         </ul>
	  */
	 private int verifierLigne2Jetons(int ligne, int[][] grille) {

		 for (int noColonne = 0 ; noColonne <= 3 ; noColonne++) {
			 if (grille[noColonne][ligne] == grille[noColonne + 1][ligne]
				 && grille[noColonne][ligne] == grille[noColonne + 2][ligne]) {

				 return grille[noColonne][ligne];
			 }
		 }

		 return 0;
	 }
	 
	/**
	 * Permet la vérification des deux diagonales
	 * @param colonne axe horizontal du jeton 
	 * @param ligne   axe vertical du jeton
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la ligne</li>
	 *         </ul>
	 */
	private int verifierDiagonal(int colonne, int ligne) {
		
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
		
		while (colonne + index < grille.length
			   && ligne + index < grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne + index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		if (cptPion > 3) {
			return grille[colonne][ligne];
		}
		
		/* Else : Diagonale Haut Gauche - Bas Droite*/
		
		index = 0;
		cptPion = 0;
		
		while (colonne - index >= 0
			   && ligne + index < grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne - index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		index = 1;
		
		while (colonne + index < grille.length
				   && ligne - index >= 0
				   && grille[colonne][ligne] 
					  == grille[colonne + index][ligne - index]) {
				
			    index++;
				cptPion++;
			}
		
		if (cptPion > 3) {
			return grille[colonne][ligne];
		}
		
		return 0;
	}
	
	/**
	 * Permet la vérification des deux diagonales
	 * @param colonne  axe horizontal du jeton 
	 * @param ligne    axe vertical du jeton
	 * @param grille   à vérifier
	 * @param nbJetons nombre de jetons qui doivent être alignés
	 * @return <ul>
	 *		 <li>Le numéro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est présent dans la ligne</li>
	 *         </ul>
	 */
	private int verifierDiagonal(int colonne, int ligne, int[][] grille,
			                     int nbJetons) {
		
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
		
		while (colonne + index < grille.length
			   && ligne + index < grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne + index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		if (cptPion > nbJetons - 1) {
			return grille[colonne][ligne];
		}
		
		/* Else : Diagonale Haut Gauche - Bas Droite*/
		
		index = 0;
		cptPion = 0;
		
		while (colonne - index >= 0
			   && ligne + index < grille[colonne].length
			   && grille[colonne][ligne] 
				  == grille[colonne - index][ligne + index]) {
			
			index++;
			cptPion++;
		}
		
		index = 1;
		
		while (colonne + index < grille.length
				   && ligne - index >= 0
				   && grille[colonne][ligne] 
					  == grille[colonne + index][ligne - index]) {
				
			    index++;
				cptPion++;
			}
		
		if (cptPion > nbJetons - 1) {
			return grille[colonne][ligne];
		}
		
		return 0;
	}
	 
	/**
	 * @return la grille de cette partie
	 */
	public int[][] getGrille() {
		return grille;
	}
	

	public String getNomJoueur1() {
		return nomJoueur1;
	}

	public String getNomJoueur2() {
		return nomJoueur2;
	}

	/**
	 * Sauvegarde d'une partie dans le fichier p4.ser
	 */
	public void save() {
		
		try (FileOutputStream fos = new FileOutputStream("p4.ser");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(this);
		} catch (Exception erreurFichier) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Chargement d'une partie
	 */
	public void load() {
		
		try (FileInputStream fis = new FileInputStream("p4.ser");
			 ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			Partie partieLoad = (Partie)ois.readObject();
			
			grille = partieLoad.getGrille();
			nomJoueur1 = partieLoad.getNomJoueur1();
			nomJoueur2 = partieLoad.getNomJoueur2();
		} catch (Exception erreurFichier) {
			// TODO: handle exception
		}
	}
	
	@Override
	/**
	 * Usage UNIQUEMENT pour le développement
	 */
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