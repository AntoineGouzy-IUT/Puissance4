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
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author antoine.gouzy
 * @author vincent.faure
 * @author romain.courbaize
 */

public class MenuController implements Initializable{
	
	@FXML
	public VBox rootPane;
	
	@FXML
	protected static VBox previousScreen;
	
	private static final String RULES_PATH = "/ressource/rules.fxml";
	private static final String MENU_PATH = "/ressource/menu.fxml";
	private static final String CREDITS_PATH = "/ressource/credits.fxml";
	
	protected static final String MYGAMES_PATH = "/ressource/myGames.fxml";
	protected static final String GAME_SELECTION_PATH = "/ressource/gameSelection.fxml";
	protected static final String GAME_PVC_PATH = "/ressource/gamePvC.fxml";
	protected static final String GAME_PVP_PATH = "/ressource/gamePvP.fxml";
	protected static final String POPUP_GAME_PVP_PATH = "/ressource/popUpPvP.fxml";
	protected static final String POPUP_GAME_PVC_PATH = "/ressource/popUpPVC.fxml";
	protected static final String ICON_PATH = "/ressource/assets/Logo_Puissance4_V4.png";
	
	protected static String previousScreenPath;
	
    /* menu.fxml actions */
    @FXML
    public void goToGameSelection(Event e) throws IOException{
  
    	previousScreenPath = MENU_PATH;
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_SELECTION_PATH));
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void goToMyGames(Event e) throws IOException{
    	
    	previousScreenPath = MENU_PATH;
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(MYGAMES_PATH)); 
    	rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void goToCredits(Event e) throws IOException{
    	
    	previousScreenPath = MENU_PATH;
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(CREDITS_PATH)); 
    	rootPane.getChildren().setAll(pane);
    }
    
    /* gameSelection.fxml actions */
    @FXML
    public void goToRules (Event e) throws IOException {

    	previousScreenPath = GAME_SELECTION_PATH;
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(RULES_PATH));
    	rootPane.getChildren().setAll(pane);
    }
        
    @FXML
    public void goBack (Event e) throws IOException {
    	
    	VBox pane = FXMLLoader.load(getClass().getResource(previousScreenPath));
    	rootPane.getChildren().setAll(pane);
    	
    	previousScreenPath = MENU_PATH;
    }
    
    
    /* Génération des modes de jeu */
    @FXML
    public void playerVersusPlayer (Event e) throws IOException {

		previousScreen = rootPane;
		previousScreenPath = GAME_SELECTION_PATH;

    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_PVP_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	
    	Parent popup = FXMLLoader.load(getClass()
    			                 .getResource(POPUP_GAME_PVP_PATH));
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
    public void playerVersusComputer (Event e) throws IOException {

    	previousScreen = rootPane;
		previousScreenPath = GAME_SELECTION_PATH;
		
    	VBox pane = FXMLLoader.load(getClass().getResource(GAME_PVC_PATH));
    	rootPane.getChildren().setAll(pane);
    	
    	Parent popup = FXMLLoader.load(getClass()
    			                 .getResource(POPUP_GAME_PVC_PATH));
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
		previousScreen = rootPane;
		previousScreenPath = MENU_PATH;
	}


}