package Dialog;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EasyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8081);
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        Scanner in = new Scanner(socket.getInputStream());
        Scanner sendToServer = new Scanner(System.in);
        String toServer = "";


        while (!toServer.equals("bye")) {      //&& in.hasNext() почему не работает??? приходится писать строки 22-24

            System.out.println("\tWrite smth to server: ");
            toServer = sendToServer.nextLine();
            out.println(toServer);
            out.flush();
            if (in.hasNext()) {
                System.out.println(in.nextLine());
            } else toServer = "bye";

        }
    }

}

