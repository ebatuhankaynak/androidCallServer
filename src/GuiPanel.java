import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E.Batuhan Kaynak on 24.9.2016.
 */
public class GuiPanel extends JPanel {

    private JTextArea textArea;
    private JButton button;
    private JPasswordField passwordField;

    public GuiPanel(){
        buildGui();
    }

    private void buildGui() {
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Listening Incoming Calls");
        Font font = textArea.getFont();
        float size = font.getSize() + 1.0f;
        textArea.setFont(font.deriveFont(size));
        add(textArea);

        passwordField = new JPasswordField(15);
        add(passwordField);

        button = new JButton("Check Mail");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MailFetcher(String.valueOf(passwordField.getPassword()));
            }
        });
        add(button);
    }

    public void refreshPanel(String str){
        textArea.setText(str);
        requestFocus();
    }
}
