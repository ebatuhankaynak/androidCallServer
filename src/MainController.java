import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {

    //@FXML private StackPane stackPane;
    @FXML private Label callerLabel;
    @FXML private Label codeLabel;
    @FXML private Button checkMailButton;

    public MainController(){
        new Thread(() -> {
            Server server = new Server();
            try {
                server.openPort();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    public void onCheckMailButtonClick(MouseEvent mouseEvent){
        MailFetcher mailFetcher = new MailFetcher(Transition.loginController.getEmail(), Transition.loginController.getPassword());
        codeLabel.setText(mailFetcher.getVerificationCode());
    }
}