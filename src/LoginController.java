import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javax.mail.*;
import java.util.Properties;

public class LoginController {

    @FXML private StackPane stackPane;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;

    public void setStackPane(Node node) {
        stackPane.getChildren().setAll(node);
    }

    @FXML
    public void onLoginButtonClick(MouseEvent mouseEvent){
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            props.put("mail.imaps.ssl.trust", "*");
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("mail.bilkent.edu.tr", emailTextField.getText(), passwordField.getText());
        } catch (AuthenticationFailedException afe){
            System.out.println("WRONG PW");
            return;
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
        Transition.loadFXML(FXMLList.MAIN);
    }

    public String getEmail() {
        return emailTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void initialize(){
        emailTextField.setText("@ug.bilkent.edu.tr");
    }
}