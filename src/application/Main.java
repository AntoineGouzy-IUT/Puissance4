package application; 

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
 
public class Main extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/ressource/menu.fxml"));
		Scene scene = new Scene(root, 1074,705);
		stage.setTitle("Puissance4");
		stage.setScene(scene);
		stage.setResizable(false);
		Image icon = new Image(getClass().getResourceAsStream("/ressource/assets/Logo_Puissance4_V4.png"));
	    stage.getIcons().add(icon);
		stage.show();
	}
     
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * modif 
 */