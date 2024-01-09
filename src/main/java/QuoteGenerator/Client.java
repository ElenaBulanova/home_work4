package QuoteGenerator;

import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String userName = "";

        String request = "Would You like to get a quote? (Y/N)";
        Scanner scanner = new Scanner(System.in);
        String answer = "Y";

        try (CreateSocket socket = new CreateSocket("127.0.0.1", 8000)) {
            System.out.println("Name: ");
            userName = scanner.nextLine();
            socket.writeLine(userName);
            if (!socket.readLine().equals("Unknown username.")) {

                System.out.println("Connected to server");
                while (answer.equals("Y")) {
                    System.out.println(request);
                    answer = scanner.nextLine();
                    if (!answer.equals("Y")) break;
                    socket.writeLine(answer);
                    String response = socket.readLine();
                    System.out.println(response);
                }
            } else System.out.println("Unknown username.");

        } catch (Exception  e ) {
            System.out.println("Sorry, but You've got enough quotes!");
            // e.printStackTrace();
        }
    }
}
