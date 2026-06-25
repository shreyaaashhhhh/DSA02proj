package LOGIN;

import java.util.Scanner;

import ADMIN.AdminMenu;
import UTIL.Input;

public class AdminLogin {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void login() {

        Scanner sc = Input.getScanner();

        String username;
        String password;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);
        System.out.println("====================================================");
        System.out.println("                                                    ");
        System.out.println("                 ADMIN LOGIN                        ");
        System.out.println("                                                    ");
        System.out.println("====================================================");
        System.out.println(RESET);

        // USERNAME
        System.out.print(BRIGHT_YELLOW +
                "   Enter Username : " + RESET);

        username = sc.nextLine();

        // PASSWORD
        System.out.print(BRIGHT_YELLOW +
                "   Enter Password : " + RESET);

        password = sc.nextLine();

        // VALID LOGIN
        if(username.equals("admin")
                && password.equals("admin123")) {

            System.out.println(BRIGHT_GREEN);

            System.out.println("\n====================================================");

            System.out.println("           WELCOME BACK ADMIN");

            System.out.println("====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_BLUE +
                    "\n   Loading Dashboard..." + RESET);

            AdminMenu menu = new AdminMenu();

            menu.showMenu();
        }

        // INVALID LOGIN
        else {

            System.out.println(BRIGHT_RED);

            System.out.println("\n====================================================");

            System.out.println("           INVALID ADMIN CREDENTIALS");

            System.out.println("====================================================");

            System.out.println(RESET);
        }
    }
}
