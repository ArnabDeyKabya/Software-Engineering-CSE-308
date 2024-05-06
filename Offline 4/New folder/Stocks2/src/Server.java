
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private HashMap<String, Stock> stocks = new HashMap<>();
    private Vector<User> users = new Vector<>();
    private int userIdCounter = 0;

    public Server() throws IOException {
        loadStocksFromFile();

        Thread consoleStream = new Thread(() -> handleConsoleInput());
        consoleStream.start();

        startServer();
    }

    private void loadStocksFromFile() {
        try (Scanner scanner = new Scanner(new File("src/input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                String name = tokenizer.nextToken();
                int count = Integer.parseInt(tokenizer.nextToken());
                float cost = Float.parseFloat(tokenizer.nextToken());
                stocks.put(name, new Stock(name, count, cost));
            }

            System.out.println("Stock Details: " + stocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConsoleInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Console Stream: ");
            String input = scanner.nextLine();

            handleConsoleCommand(input);
        }
    }

    private void handleConsoleCommand(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        Vector<String> tokens = new Vector<>();

        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        if (!tokens.isEmpty()) {
            String command = tokens.elementAt(0);
            String stockName = tokens.elementAt(1);
            float changeAmount = Float.parseFloat(tokens.elementAt(2));

            if ("I".equals(command) || "D".equals(command)) {
                updateStockPrice(stockName, command.equals("I") ? changeAmount : -changeAmount);
            } else if ("C".equals(command)) {
                updateStockCount(stockName, (int) changeAmount);
            }

            System.out.println("Updated Stock Details: " + stocks);

            notifyObservers(stockName);
        }
    }

    private void updateStockPrice(String stockName, float amount) {
        Stock stock = stocks.get(stockName);
        stock.setPrice(stock.getPrice() + amount);
        stocks.put(stockName, stock);
    }

    private void updateStockCount(String stockName, int count) {
        Stock stock = stocks.get(stockName);
        stock.setCount(count);
        stocks.put(stockName, stock);
    }

    private void notifyObservers(String stockName) {
        for (Stock stock : stocks.values()) {
            if (stock.getName().equals(stockName)) {
                stock.notifyObservers(stockName + " has been updated");
            }
        }
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(6788)) {
            System.out.println("Server is running...");

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                handleClientConnection(connectionSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientConnection(Socket connectionSocket) {
        try {
            DataInputStream dis = new DataInputStream(connectionSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());

            User curUser = new User(userIdCounter, dis, dos);
            users.add(curUser);

            dos.writeUTF("Stock Details: " + stocks.toString());
            dos.flush();

            Thread workerThread = new Thread(() -> handleClientCommunication(curUser));
            workerThread.start();

            System.out.println("Client [" + userIdCounter + "] is now connected. No. of worker threads = " + (userIdCounter + 1));
            userIdCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientCommunication(User curUser) {
        try {
            DataInputStream dis = curUser.getDis();

            while (true) {
                String textFromClient = dis.readUTF();
                System.out.println("Text from client " + curUser.getId() + ": " + textFromClient);

                handleClientCommand(textFromClient, curUser);
            }
        } catch (IOException e) {
            handleClientDisconnect(curUser);
        }
    }

    private void handleClientCommand(String clientCommand, User curUser) {
        StringTokenizer tokenizer = new StringTokenizer(clientCommand, " ");
        Vector<String> tokens = new Vector<>();

        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }

        if (!tokens.isEmpty()) {
            String command = tokens.elementAt(0);
            String stockName;

            switch (command) {
                case "S":
                    stockName = tokens.elementAt(1);
                    subscribeToStock(curUser, stockName);
                    break;
                case "U":
                    stockName = tokens.elementAt(1);
                    unsubscribeFromStock(curUser, stockName);
                    break;
                case "N":
                    sendStockDetails(curUser);
                    break;
                case "V":
                    System.out.println( curUser.subscribedStocks.toString());
                    break;
                case "exit":
                    handleClientDisconnect(curUser);
                    break;
            }
        }
    }

    private void subscribeToStock(User curUser, String stockName) {
        System.out.println("Client [" + curUser.getId() + "] subscribed to " + stockName);

        for (Stock stock : stocks.values()) {
            if (stock.getName().equals(stockName)) {
                curUser.subscribedStocks.add(stock);
                stock.registerObserver(curUser);
            }
        }
    }

    private void unsubscribeFromStock(User curUser, String stockName) {
        System.out.println("Client [" + curUser.getId() + "] unsubscribed from " + stockName);

        for (Stock stock : stocks.values()) {
            if (stock.getName().equals(stockName)) {
                curUser.subscribedStocks.remove(stock);
                stock.removeObserver(curUser);
            }
        }
    }

    private void sendStockDetails(User curUser) {
        try {
            curUser.getDos().writeUTF(stocks.toString());
            curUser.getDos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClientDisconnect(User curUser) {
        System.out.println("Client " + curUser.getId() + " exited");

        for (Stock stock : stocks.values()) {
            stock.removeObserver(curUser);
        }

        users.remove(curUser);
        Thread.currentThread().interrupt();
    }

    public static void main(String[] args) throws Exception {
        new Server();
    }
}

