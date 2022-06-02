/*
 * Partie.java                                   05/2022
 * BUT INFO 1 2021/2022
 * pas de droits d'auteur ni de copyright
 */

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Partie de puissance 4 sur une grille de 7 x 6
 * @author vincent.faure
 * @author romain.courbaize
 * @author antoine.gouzy
 */
public class Partie implements Serializable {
	
	/** Grille de cette partie */
	private static int[][] grille;
	
	/** Pion du joueur 1 */
	private final int JOUEUR1 = 1;
	
	/** Pion du joueur 2 */
	private final int JOUEUR2 = 2;
	
	/** Nom du premier joueur */
	private String nomJoueur1;
	
	/** Nom du deuxi�me joueur */
	private String nomJoueur2;
	
	/** Num�ro de la ligne jou� par le dernier joueur */
	private int ancienneLigne = 0; //TODO stub
	
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
	 * Calcul une position pour le prochain pion
	 */
	public void ordinateur() {
		
		
	}
	
	/**
	 * Calcul la position d'un pion pour d�fendre
	 * Si l'adversaire � 3 pions align�s alors elle va le bloquer
	 * @return <ul>
	 * 			<li>-1 s'il n'y a aucune ligne, diagonale ou colonne de 3 pions</li>
	 * 			<li>le num�ro de la colonne s'il faut d�fendre</li>
	 * 		   </ul>
	 */
	public int defense() {
		
		int[][] grilleTmp = grille;
		
		for (int noColonneATester = 0 ; noColonneATester < 7 ; noColonneATester++) {
			
			ajouterPionIA(1, noColonneATester, grilleTmp);
			verifierAlignement(noColonneATester, noColonneATester)
		}
	}
	
	/**
	 * Ajout d'un pion dans cette partie pour tester les posibilit�es
	 * @param joueur   num�ro du joueur qui ajoute ce pion
	 * @param colonne  num�ro de la colonne dans laquelle il ajoute ce pion
	 */
	private int ajouterPionIA(int joueur, int colonne, int[][] grille) {
		
		int noVide;
		
		noVide = 0;
		
		while (grille[colonne][noVide] != 0) {
			
			noVide++;
		}
		
		grille[colonne][noVide] = joueur;
		
		return verifierAlignementIA(colonne, noVide, grille);
	}
	
	/**
	 * V�rifie si il y a un alignement de 4 pion identique
	 * en diagonale, a l'horizontale et a la verticale
	 * @return <ul>
	 *		 <li>Le num�ro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est pr�sent dans la colonne</li>
	 *         </ul>
	 */
	public int verifierAlignement(int colonne, int ligne, int[][] grille) {
		
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
	 * Ajout d'un pion dans cette partie
	 * @param joueur   num�ro du joueur qui ajoute ce pion
	 * @param colonne  num�ro de la colonne dans laquelle il ajoute ce pion
	 * @return cette partie avec le pion ajouter 
	 * @throws IllegalArgumentException  Si le num�ro de la colonne est incorrect 
	 *                                   Si le num�ro du joueur est incorrect
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
	 * V�rifie si il y a un alignement de 4 pion identique
	 * en diagonale, a l'horizontale et a la verticale
	 * @return <ul>
	 *		 <li>Le num�ro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est pr�sent dans la colonne</li>
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
	 * Permet la v�rification de la colonne no colonne
	 * @param colonne num�ro de la colonne � verifier
	 * @return <ul>
	 *		 <li>Le num�ro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est pr�sent dans la colonne</li>
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
	 * Permet la v�rification de la ligne no ligne
	 * @param ligne num�ro de la ligne � verifier
	 * @return <ul>
	 *		 <li>Le num�ro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est pr�sent dans la ligne</li>
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
	 * Permet la v�rification des deux diagonales
	 * @return <ul>
	 *		 <li>Le num�ro du joueur gagnant</li>
	 *		 <li>0 si aucun alignement de 4 est pr�sent dans la ligne</li>
	 *         </ul>
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

	public void save() {
		
		try (FileOutputStream fos = new FileOutputStream("p4.ser");
			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(this);
		} catch (Exception erreurFichier) {
			// TODO: handle exception
		}
	}
	
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