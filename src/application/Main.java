package application; 


import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
 
/**
 * @author antoine.gouzy
 * @author vincent.faure
 * @author romain.courbaize
 */

public class Main extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/ressource/FXML/menu.fxml"));
		Scene scene = new Scene(root, 1074,705);
		stage.setTitle("Puissance4");
		stage.setScene(scene);
		stage.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream("/ressource/assets/Logo_Puissance4_V4.png"));
	    stage.getIcons().add(icon);
		stage.show();
		
        File f = new File("p4.ser");

        if (!f.createNewFile()) {
    		Parent popup = FXMLLoader.load(getClass().getResource("/ressource/FXML/popUpMyGames.fxml"));
    		Scene scenePopup = new Scene(popup);
    		Stage stagePopup = new Stage();
    		stagePopup.setScene(scenePopup);
    		stagePopup.initModality(Modality.WINDOW_MODAL);
    		stagePopup.setResizable(false);
    		Image iconPopup = new Image(getClass().getResourceAsStream("/ressource/assets/Logo_Puissance4_V4.png"));
    		stagePopup.getIcons().add(iconPopup);

    		stagePopup.show();
        }
    }
     
    public static void main(String[] args) {
        launch(args);
    }
}