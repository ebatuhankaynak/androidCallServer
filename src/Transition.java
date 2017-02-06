import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Transition {

    public static LoginController loginController;

    public static void setLoginController(LoginController loginController) {
        Transition.loginController = loginController;
    }

    public static void loadFXML(String fxml) {
        try {
            loginController.setStackPane(FXMLLoader.load(Transition.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}