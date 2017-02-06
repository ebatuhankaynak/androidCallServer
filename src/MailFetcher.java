import sound.Sound;

import javax.mail.*;
import java.util.Properties;

/**
 * Created by E.Batuhan Kaynak on 24.9.2016.
 */
public class MailFetcher {

    private String verificationCode;
    private boolean codeFound;

    public MailFetcher(String password){
        codeFound = false;
        //fetchMail(password);
    }

    public MailFetcher(String email, String password){
        codeFound = false;
        fetchMail(email, password);
    }

    private void fetchMail(String email, String password){
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        props.put("mail.imaps.ssl.trust", "*");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("mail.bilkent.edu.tr", email, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());
            Address[] in = msg.getFrom();
            for (Address address : in) {
                System.out.println("FROM:" + address.toString());
                if (address.toString().contains("<starsmsg@bilkent.edu.tr>")){
                    codeFound = true;
                }
            }

            if (msg.getContent() instanceof String)
            {
                String body = (String) msg.getContent();
                //System.out.println("CONTENT:" + body);
                getVerificationCode(body);
            }
            else if (msg.getContent() instanceof Multipart)
            {
                Multipart mp = (Multipart) msg.getContent();
                BodyPart bp = mp.getBodyPart(0);
                //System.out.println("CONTENT:" + bp.getContent());
                //getVerificationCode(bp.getContent());
            }

            if (codeFound){
                System.out.println(verificationCode);
                //Main.panel.refreshPanel(verificationCode);
                Sound sound = new Sound();
                sound.playNumbers(verificationCode);
            } else {
                verificationCode = "NO CODE FOUND";
            }

            //System.out.println("SENT DATE:" + msg.getSentDate());
            //System.out.println("SUBJECT:" + msg.getSubject());
        } catch (AuthenticationFailedException afe){
            afe.printStackTrace();
            System.out.println("WRONG PASSWORD");
        } catch (MessagingException me){
            me.printStackTrace();
            System.out.println("PROBLEM");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getVerificationCode(String body){
        String helperStr = "Verification Code: ";
        int startIndex = body.lastIndexOf(helperStr) + helperStr.length();
        verificationCode = body.substring(startIndex, startIndex + 5);
    }

    public String getVerificationCode(){
        return verificationCode;
    }
}
