import sound.Sound;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by E.Batuhan Kaynak on 19.9.2016.
 */
public class Server{
    private int portNumber = 6543;
    private ServerSocket serverSocket;
    private String cellNumber;

    public void openPort() throws IOException {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println("Waiting for connection.....");

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Connection successful");
        System.out.println("Waiting for input.....");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Server: " + inputLine);
                out.println(inputLine);
                cellNumber = inputLine;
                //Main.panel.refreshPanel(cellNumber);
                new Sound().playAlo();

                if (inputLine.equals("Bye."))
                    break;
            }
        } catch (SocketException se){
            serverSocket.close();
            openPort();
        }


        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    public String getCellNumber(){
        return cellNumber;
    }
}
