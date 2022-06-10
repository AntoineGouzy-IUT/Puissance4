package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author antoine.gouzy
 * @author vincent.faure
 * @author romain.courbaize
 */

public class PopUpController extends GameController implements Initializable{
	
	@FXML
	private VBox rootPanePopUp;
	
	@FXML
	private Button button;
	
	@FXML
	private TextField player1;
	@FXML
	private TextField player2;
	
	@FXML
	private Label userHelp,
				  endGameMessage;
	
	private static final String POPUP_SAVE_PATH = "/ressource/FXML/popUpSave.fxml";
	
	
    @FXML
    public void goBackPopUp (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	VBox pane = FXMLLoader.load(getClass().getResource(previousScreenPath));
    	rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    }
    
    @FXML
    public void closePopUp (Event e) throws IOException {
    	((Stage) button.getScene().getWindow()).close();
    }
            
    /* Save pseudo and launch game */
    @FXML
    public void playPvP (javafx.event.ActionEvent event) {
    	
    	ordi = false;
    	if (player1.getText().isBlank() || player2.getText().isBlank()) {
    		
    		userHelp.setTextFill(Color.RED);
		} else {
			
			Random rand = new Random(); 
			alea = rand.nextInt(1,3);
			
			if (alea == 1) {
				
				staticUsername1.setText(player1.getText());
				staticUsername2.setText(player2.getText());
				actualPlayer = 2;
				couleurJoueur2 = "yellow";
				couleurJoueur1 = "red";
				
				partie = new Partie(player2.getText(), player1.getText());
			} else {
				
				staticUsername1.setText(player2.getText());
				staticUsername2.setText(player1.getText());
				actualPlayer = 1;
				couleurJoueur1 = "yellow";
				couleurJoueur2 = "red";
				
				partie = new Partie(player1.getText(), player2.getText());
			}
			((Stage) button.getScene().getWindow()).close();
		}
    }
    
    @FXML
    public void playPvC (Event e) throws IOException {
    	
    	ordi = true;
    	alea = 1;
    	staticUsername1.setText(player1.getText());
    	couleurJoueur1 = "red";
    	actualPlayer = 1;
    	staticUsername2.setText("Ordinateur");
    	couleurJoueur2 = "yellow";

		staticUsername1.getStyleClass().remove("inactive");
		staticUsername2.getStyleClass().add("inactive");
		staticRedToken.setImage(redActiveToken);
		staticYellowToken.setImage(yellowInactiveToken);
    	
    	
    	if (player1.getText().isBlank()) {
    		staticUsername1.setText("Joueur Humain");
		}
    	
		partie = new Partie(staticUsername1.getText(), "Ordinateur");

    	((Stage) button.getScene().getWindow()).close();
    }
    
    @FXML
    public void exit (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	((Stage) rootPane.getScene().getWindow()).close();
    	((Stage) button.getScene().getWindow()).close();
    }
    
    @FXML
    public void goToGameSelection (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_SELECTION_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    }
    
    @FXML
    public void goToMyGames (Event e) throws IOException {
    	//TODO Corriger probleme sauvegarde
    	rootPane = previousScreen;
    	//VBox pane = FXMLLoader.load(getClass().getResource(MYGAMES_PATH));

        partie = new Partie("j1", "j2");
    	partie.load();   	
    	
    	//rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    }
    

    @FXML
    public void save (Event e) throws IOException {
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(POPUP_SAVE_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	partie.save();
    }
    
    @FXML
    public void replayPvP (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_PVP_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    	
    	Parent popup = FXMLLoader.load(getClass().getResource(POPUP_GAME_PVP_PATH));
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
    public void replayPvC (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_PVC_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    	
    	Parent popup = FXMLLoader.load(getClass().getResource(POPUP_GAME_PVC_PATH));
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
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		staticEndGameMessage = endGameMessage;
	}
}