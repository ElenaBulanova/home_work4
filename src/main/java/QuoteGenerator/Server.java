package QuoteGenerator;

import java.io.*;
import java.net.ServerSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;


public class Server {
    public static void main(String[] args) {

        String[] qouteArr = initQuoteArr();
        String userName = "";
        List<String> usersList = initUsersList();

        try (ServerSocket server = new ServerSocket(8000, 2);) { //надо считать подключения руками?  потом запрещать accept?
            System.out.println("Server started.");

            while (true) {
                CreateSocket socket1 = new CreateSocket(server);

                userName = socket1.readLine();
                if (usersList.contains(userName)) {
                    socket1.writeLine("Ok");
                    final int[] requestNumber = {0};
                    //int requestNumber1 = 0;
                    String finalUserName = userName;
                    new Thread(() -> {

                        String log = "";
                        String dateTimeStart = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                                .format(LocalDateTime.now());
                        log = log + "Start for " + finalUserName + " " + dateTimeStart + "\n";

                        while (requestNumber[0] <= 4) {

                            requestNumber[0]++;
                            String request = socket1.readLine();

                            if (request != null && request.equals("Y")) {
                                String response = qouteArr[(int) (Math.random() * qouteArr.length)];
                                socket1.writeLine(response);
                                log = log + response + "\n";
                            } else break;
                        }
                        try {
                            String dateTimeEnd = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                                    .format(LocalDateTime.now());
                            log = log + "End for " + finalUserName + " " + dateTimeEnd + "\n";
                            System.out.println(log);
                            socket1.close();
                        } catch (IOException e) {
                        }
                    }).start();
                } else {
                    socket1.writeLine("Unknown username.");
                    try {
                        socket1.close();
                    } catch (IOException e) {
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String[] initQuoteArr() {
        String[] quoterArr = {
                "Не волнуйся, если не работает. Если бы все всегда работало, у тебя бы не было работы.",
                "Всегда пиши код так, как будто человек, который будет его саппортить — психопат-убийца, который знает, где ты живешь.",
                "Любой твой код, в который ты не заглядывал больше шести месяцев, будет выглядеть как код, который написал кто-то другой.",
                "Если сразу не получилось хорошо, назовите это версией 1.0.",
                "Плохое ПО одного человека — постоянная работа другого.",
                "Если бы в Java была реализована уборка мусора, большинство программ удаляли бы себя сразу после запуска.",
                "Java относится к JavaScript так же, как Сом к Сомали.",
                "Совсем не важно, чтобы получилось хорошо с первого раза. Жизненно важно, чтобы хорошо получилось с последнего.",
                "Если отладка — это процесс удаления багов из кода, то получается, что программирование — это процесс помещения их туда.",
                "Плохой код на самом деле не плохой. Его просто не так поняли.",
                "Софт проходит бета-тестирование незадолго до того, как выходит в свет. Бета на латыни означает «все еще не работает».",
                "Программное обеспечение не закончено до тех пор, пока не умер последний пользователь.",
                "Чтобы понять рекурсию, нужно сперва понять рекурсию.",
                "Языки программирования бывают двух видов: те, на которые все жалуются, и такие, на которых никто не пишет.",
                "Настоящий программист никогда не пишет комментариев к коду. Что создавалось с трудом, должно с трудом и пониматься. ",
                "Кому и командная строка — дружественный интерфейс.",
                "В мире есть 10 категорий людей – те, кто знают про двоичное счисление и те кто нет.",
                "Если программист признан незаменимым, то лучшее, что можно сделать — избавиться от него как можно скорее."

        };
        return quoterArr;
    }

    public static List<String> initUsersList() {
        List<String> usersList = Arrays.asList("Elena", "Nick", "Alex", "Kat");
        return usersList;
    }
}
