import com.sun.tools.jconsole.JConsoleContext;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.addUser("u1", "username1", "email1", "1111 1111");
        controller.addUser("u2", "username2", "email2", "2222 2222");
        controller.addUser("u3", "username3", "email3", "3333 3333");
        controller.addUser("u4", "username4", "email4", "4444 4444");

        controller.start();
    }
}
