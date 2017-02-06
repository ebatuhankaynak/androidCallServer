/**
 * Created by E.Batuhan Kaynak on 24.9.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = null;
        try {
            mainPane = (Pane) loader.load(getClass().getResourceAsStream(FXMLList.LOGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LoginController loginController = loader.getController();

        Transition.setLoginController(loginController);

        stage.setScene(createScene(mainPane));
        stage.setTitle("Notifier");
        stage.show();
    }

    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        return scene;
    }
}
