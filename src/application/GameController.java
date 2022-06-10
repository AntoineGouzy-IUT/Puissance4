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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameController extends ButtonFXMLController implements Initializable{

	@FXML
	private Label username1;
	@FXML
	private Label username2;
	
	@FXML
	private ImageView redToken;
	@FXML
	private ImageView yellowToken;

	@FXML
	private Button button;
	
	@FXML
	protected VBox rootPane;
	
	public static VBox staticRootPane;
	
	public static Label staticUsername1;
	public static Label staticUsername2;
	
	private Image redInactiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenRedInactive.png"));
	private Image redActiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenRedActive.png"));
	private Image yellowInactiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenYellowInactive.png"));
	private Image yellowActiveToken = new Image(getClass().getResourceAsStream("/ressource/assets/TokenYellowActive.png"));
	
	private static final String POPUP_RULES_PATH = "/ressource/popUpRules.fxml";
	private static final String POPUP_EXIT_PATH = "/ressource/popUpExit.fxml";
	private static final String ICON_PATH = "/ressource/assets/Logo_Puissance4_V4.png";
	
	public static String couleurJoueur1;
	public static String couleurJoueur2;
	
	public static int actualPlayer;
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		staticUsername1 = username1;
		staticUsername2 = username2;
		staticRootPane = rootPane;
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
    
    @FXML
    public void joue (javafx.event.ActionEvent event) {
    	
    	Button clicked = (Button) event.getTarget();
    	
    	if (actualPlayer == 2) {
    		
    		clicked.setDisable(true);
    		clicked.getStyleClass().add(couleurJoueur2 + "_token");
    		
    		username2.getStyleClass().remove("inactive");
    		username1.getStyleClass().add("inactive");
			redToken.setImage(redInactiveToken);
    		yellowToken.setImage(yellowActiveToken);
			
			actualPlayer--;
		} else {
			
			clicked.setDisable(true);
			clicked.getStyleClass().add(couleurJoueur1 + "_token");
			
			username1.getStyleClass().remove("inactive");
    		username2.getStyleClass().add("inactive");
    		redToken.setImage(redActiveToken);
    		yellowToken.setImage(yellowInactiveToken);
    		
    		actualPlayer++;
		}
    	
    }
}
