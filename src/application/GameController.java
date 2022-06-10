package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author antoine.gouzy
 * @author vincent.faure
 * @author romain.courbaize
 */

public class GameController extends MenuController implements Initializable{

	@FXML
	private Label username1;
	@FXML
	private Label username2;
	@FXML
	private Label endGameMessage;

	@FXML
	private ImageView redToken;
	@FXML
	private ImageView yellowToken;

	@FXML
	private Label jeton;

	@FXML
	private VBox col0;
	@FXML
	private VBox col1;
	@FXML
	private VBox col2;
	@FXML
	private VBox col3;
	@FXML
	private VBox col4;
	@FXML
	private VBox col5;
	@FXML
	private VBox col6;

	public static VBox staticRootPane;
	public static Label staticUsername1;
	public static Label staticUsername2;
	public static Label staticEndGameMessage;
	public static ImageView staticRedToken;
	public static ImageView staticYellowToken;

	private Image redInactiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenRedInactive.png"));
	protected Image redActiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenRedActive.png"));
	protected Image yellowInactiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenYellowInactive.png"));
	private Image yellowActiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenYellowActive.png"));

	private static final String POPUP_RULES_PATH = "/ressource/FXML/popUpRules.fxml";
	private static final String POPUP_EXIT_PATH = "/ressource/FXML/popUpExit.fxml";
	private static final String POPUP_ENDGAMEPVC_PATH = "/ressource/FXML/popUpEndGamePvc.fxml";
	private static final String POPUP_ENDGAMEPVP_PATH = "/ressource/FXML/popUpEndGamePvp.fxml";
	
	public static String couleurJoueur1;
	public static String couleurJoueur2;
	
	public static boolean ordi;
	
	public static int actualPlayer;
	public static int alea;
	
	public static Partie partie;
	
	public int winner;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		staticUsername1 = username1;
		staticUsername2 = username2;
		staticRootPane = rootPane;
		staticRedToken = redToken;
		staticYellowToken = yellowToken;
		staticEndGameMessage = endGameMessage;

		col0.setOnMouseClicked(event -> {
			affichagePion(col0);
		});
		col1.setOnMouseClicked(event -> {
			affichagePion(col1);
		});
		col2.setOnMouseClicked(event -> {
			affichagePion(col2);
		});
		col3.setOnMouseClicked(event -> {
			affichagePion(col3);
		});
		col4.setOnMouseClicked(event -> {
			affichagePion(col4);
		});
		col5.setOnMouseClicked(event -> {
			affichagePion(col5);
		});
		col6.setOnMouseClicked(event -> {
			affichagePion(col6);
		});
		
	}

	@FXML
	public void goToRules (Event e) throws IOException {

		Parent popup = FXMLLoader.load(getClass().getResource(POPUP_RULES_PATH));
		Scene scene = new Scene(popup);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(rootPane.getScene().getWindow());
		stage.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream(ICON_PATH));
		stage.getIcons().add(icon);

		stage.show();
	}

	@FXML
	public void exit (Event e) throws IOException {

		Parent popup = FXMLLoader.load(getClass().getResource(POPUP_EXIT_PATH));
		Scene scene = new Scene(popup);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(rootPane.getScene().getWindow());
		stage.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream(ICON_PATH));
		stage.getIcons().add(icon);

		stage.show(); 
	}

	public void affichagePion(VBox box) {
		
		if (ordi) {
			afficherPionOrdi(box);
		} else {
			afficherPionJoueur(box);
		}
	}

	private void afficherPionJoueur(VBox box) {
		
		String colonneString = box.getId();
		String colonneId = colonneString.substring(colonneString.length()-1);
		int colonneInt = Integer.parseInt(colonneId);
		
		int[] resultat = null;
		
		try {
			resultat = partie.ajouterPion(actualPlayer, colonneInt);
			
			if (actualPlayer == 2 && alea == 2) {

			box.getChildren().get(5 - resultat[1]).setDisable(true);
			box.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur2 + "_token");

			username2.getStyleClass().remove("inactive");
			username1.getStyleClass().add("inactive");
			redToken.setImage(redInactiveToken);
			yellowToken.setImage(yellowActiveToken);

			actualPlayer--;

		} else if (actualPlayer == 1 && alea == 2) {
			box.getChildren().get(5 - resultat[1]).setDisable(true);
			box.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur1 + "_token");

			username1.getStyleClass().remove("inactive");
			username2.getStyleClass().add("inactive");
			redToken.setImage(redActiveToken);
			yellowToken.setImage(yellowInactiveToken);

			actualPlayer++;
			
		} else if (actualPlayer == 2 && alea == 1) {

			box.getChildren().get(5 - resultat[1]).setDisable(true);
			box.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur2 + "_token");

			
			username1.getStyleClass().remove("inactive");
			username2.getStyleClass().add("inactive");
			redToken.setImage(redActiveToken);
			yellowToken.setImage(yellowInactiveToken);
			

			actualPlayer--;

		} else if (actualPlayer == 1 && alea == 1) {
			box.getChildren().get(5 - resultat[1]).setDisable(true);
			box.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur1 + "_token");
			
			username2.getStyleClass().remove("inactive");
			username1.getStyleClass().add("inactive");
			redToken.setImage(redInactiveToken);
			yellowToken.setImage(yellowActiveToken);

			actualPlayer++;
		}
		
		victoire(resultat[2]);
		
		} catch (ArrayIndexOutOfBoundsException e) {
			//Si colonne deja pleine
		} catch (NullPointerException e) {
			//Si colonne deja pleine
		}
		
		if (partie.grilleComplete()) {

			try {
				endGame("Partie nulle");
			} catch (IOException e) {
			}
		}
	}

	private void afficherPionOrdi(VBox box) {
		
		String colonneString = box.getId();
		String colonneId = colonneString.substring(colonneString.length()-1);
		int colonneInt = Integer.parseInt(colonneId);
		boolean victoire = false;
		int[] resultat = null;
		
		try {
			resultat = partie.ajouterPion(actualPlayer, colonneInt);

			box.getChildren().get(5 - resultat[1]).setDisable(true);
			box.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur1 + "_token");

			if (resultat[2] == 1 || resultat[2] == 2 && !victoire) {
				victoire(resultat[2]);
				victoire = true;
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			//Si colonne deja pleine
		} catch (NullPointerException e) {
			//Si colonne deja pleine
		}
		
		if (partie.grilleComplete()) {

			try {
				endGame("Partie nulle");
			} catch (IOException e) {
			}
		}

		try {
			resultat = partie.ordinateur();

			VBox boxOrdi;
			boxOrdi = (VBox) box.getParent().getChildrenUnmodifiable().get(resultat[0]);

			boxOrdi.getChildren().get(5 - resultat[1]).setDisable(true);
			boxOrdi.getChildren().get(5 - resultat[1]).getStyleClass().add(couleurJoueur2 + "_token");

			if ((resultat[2] == 1 || resultat[2] == 2) && !victoire) {
				victoire(resultat[2]);
				victoire = true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//Si colonne deja pleine
		} catch (NullPointerException e) {
			//Si colonne deja pleine
		}
		
		if (partie.grilleComplete()) {

			try {
				endGame("Partie nulle");
			} catch (IOException e) {
			}
		}
	}
	
	private void victoire(int winner) {
		if (winner == 1 && alea == 1) {
			try {
				endGame("Félicitation " + username1.getText());
			} catch (IOException e) {
			}
		}

		if (winner == 2 && alea == 1) {
			try {
				endGame("Félicitation " + username2.getText());
			} catch (IOException e) {
			}
		}
		
		if (winner == 1 && alea == 2) {
			try {
				endGame("Félicitation " + username2.getText());
			} catch (IOException e) {
			}
		}

		if (winner == 2 && alea == 2 ) {
			try {
				endGame("Félicitation " + username1.getText());
			} catch (IOException e) {
			}
		}
		
	}
	
	public void endGame (String msg) throws IOException {
		
		previousScreen = rootPane;
		Parent popupEndGame;
		
		if (ordi) { 
			 popupEndGame = FXMLLoader.load(getClass().getResource(POPUP_ENDGAMEPVC_PATH));
			 staticEndGameMessage.setText(msg);
		} else {
			 popupEndGame = FXMLLoader.load(getClass().getResource(POPUP_ENDGAMEPVP_PATH));
			 staticEndGameMessage.setText(msg);
		}
		
		Scene sceneEndGame = new Scene(popupEndGame);
		Stage stageEndGame = new Stage();
		stageEndGame.setScene(sceneEndGame);
		stageEndGame.initModality(Modality.WINDOW_MODAL);
		stageEndGame.initOwner(rootPane.getScene().getWindow());
		stageEndGame.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream(ICON_PATH));
		stageEndGame.getIcons().add(icon);

		stageEndGame.show();
	}
}
