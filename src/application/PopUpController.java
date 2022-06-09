package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
	
	private static final String MENU_PATH = "/ressource/menu.fxml";
	private static final String POPUP_SAVE_PATH = "/ressource/popUpSave.fxml";
	
	
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

    	if (player1.getText().isBlank() || player2.getText().isBlank()) {
    		
    		userHelp.setTextFill(Color.RED);
		} else {
			
			Random rand = new Random();
			int alea = rand.nextInt(1,3);
			System.out.println(alea);
			
			if (alea == 1) {
				
				staticUsername1.setText(player1.getText());
				staticUsername2.setText(player2.getText());
				actualPlayer = 2;
				couleurJoueur2 = "yellow";
				couleurJoueur1 = "red";
			} else {
				
				staticUsername1.setText(player2.getText());
				staticUsername2.setText(player1.getText());
				actualPlayer = 1;
				couleurJoueur1 = "yellow";
				couleurJoueur2 = "red";
			}
			staticUsername1.getStyleClass().add("inactive");
			((Stage) button.getScene().getWindow()).close();
		}
    }
    
    @FXML
    public void playPvC (Event e) throws IOException {
    	
    	staticUsername1.setText(player1.getText());
    	staticUsername2.setText("Ordinateur");
    	
    	if (player1.getText().isBlank()) {
    		
    		staticUsername1.setText("Joueur Humain");
		}

    	((Stage) button.getScene().getWindow()).close();
    }

    @FXML
    public void goToMainMenu (Event e) throws IOException {
    	
    	rootPane = previousScreen;
    	VBox pane = FXMLLoader.load(getClass().getResource(MENU_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	((Stage) button.getScene().getWindow()).close();
    }

    @FXML
    public void save (Event e) throws IOException {
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(POPUP_SAVE_PATH));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void replay (Event e) throws IOException {
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(POPUP_SAVE_PATH));
    	rootPane.getChildren().setAll(pane);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
}