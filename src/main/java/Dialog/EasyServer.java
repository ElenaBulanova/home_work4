package Dialog;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EasyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket client = serverSocket.accept();
        Scanner in = new Scanner(client.getInputStream());
        Scanner sendToClient = new Scanner(System.in);
        PrintWriter out = new PrintWriter(client.getOutputStream());
        String toClient = "";

        while (!toClient.equals("bye") && in.hasNext() ){
            System.out.println(in.nextLine());
            System.out.println("\tWrite smth to Client: ");
            toClient = sendToClient.nextLine();
            out.println(toClient);
            out.flush();

        }
    }
}
