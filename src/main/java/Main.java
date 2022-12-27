import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("server started");
        int port = 8089;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());

                    out.println(String.format("Hello my friend! Your port is %d", clientSocket.getPort()));

                    out.println("Please tell me your name!");

                    final String personName = in.readLine();
                    out.println(String.format("Hey %s! How old are you?", personName));

                    final int personAge = Integer.parseInt(in.readLine());
                    if (personAge >= 18) {
                        out.println("Access is allowed! Choose a cocktail:");
                        out.println(" 1.Margarita  2.Mojito  3.Whiskey cola  4.Daiquiri  5.Absinthe");
                        final int cocktail = Integer.parseInt(in.readLine());
                        out.println(chooseCocktail(cocktail) + "? Good choice! The cost is 5$. Enjoy!");

                    } else {
                        out.println("Access denied!");
                        out.println("Sorry...Please..press enter");
                        out.println("Goodbye...");
                    }
                }
            }
        }
    }

    public static String chooseCocktail(int number) {
        switch (number) {
            case 1:
                return "Margarita";
            case 2:
                return "Mojito";
            case 3:
                return "Whiskey cola";
            case 4:
                return "Daiquiri";
            case 5:
                return "Absinthe";
        }
        return "Just water";
    }
}
