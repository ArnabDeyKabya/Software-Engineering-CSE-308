
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isRunning;

    public Client(String serverAddress, int serverPort) {
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            dis = new DataInputStream(clientSocket.getInputStream());
            dos = new DataOutputStream(clientSocket.getOutputStream());
            isRunning = true;
        } catch (IOException e) {
            System.out.println("Error connecting to the server. Exiting.");
            close();
        }

        startListeningThread();
        startConsoleInputThread();
    }

    private void startListeningThread() {
        Thread listenFromServer = new Thread(() -> {
            while (isRunning) {
                try {
                    String message = dis.readUTF();
                    System.out.println("From Server: " + message);
                } catch (IOException e) {
                    System.out.println("Connection to server lost. Exiting.");
                    close();
                }
            }
        });
        listenFromServer.start();
    }

    private void startConsoleInputThread() {
        Thread consoleInput = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (isRunning) {
                System.out.print("Enter message (type 'exit' to quit): ");
                String userInput = scanner.nextLine();

                try {
                    dos.writeUTF(userInput);
                    dos.flush();

                    if ("exit".equals(userInput)) {
                        close();
                    }

                } catch (IOException e) {
                    System.out.println("Error sending message to server. Exiting.");
                    close();
                }
            }
        });
        consoleInput.start();
    }

    private void close() {
        isRunning = false;
        try {
            if (dos != null) dos.close();
            if (dis != null) dis.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 6788);
    }
}
