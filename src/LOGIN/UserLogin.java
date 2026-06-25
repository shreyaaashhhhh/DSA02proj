package LOGIN;

import java.util.Scanner;
import java.io.*;

import USER.UserDashboard;
import UTIL.Input;

public class UserLogin {

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

        String name;

        String password;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println("====================================================");
        System.out.println("                                                    ");
        System.out.println("                  USER LOGIN                        ");
        System.out.println("                                                    ");
        System.out.println("====================================================");

        System.out.println(RESET);

        // USERNAME
        System.out.print(BRIGHT_YELLOW +
                "   Enter Username : " + RESET);

        name = sc.nextLine();

        // PASSWORD
        System.out.print(BRIGHT_YELLOW +
                "   Enter Password : " + RESET);

        password = sc.nextLine();

        try {

            File file =
                    new File("ApprovedUsers.txt");

            // FILE NOT FOUND
            if(!file.exists()) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             NO APPROVED USERS FOUND");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            boolean found = false;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equals(name)
                        &&
                        data[3].equals(password)) {

                    found = true;

                    // SUCCESS LOGIN
                    System.out.println(BRIGHT_GREEN);

                    System.out.println(
                            "\n====================================================");

                    System.out.println(
                            "             WELCOME BACK " +
                                    name.toUpperCase());

                    System.out.println(
                            "====================================================");

                    System.out.println(RESET);

                    System.out.println(BRIGHT_BLUE +
                            "\n   Loading User Dashboard..."
                            + RESET);

                    UserDashboard dashboard =
                            new UserDashboard();

                    dashboard.menu(name);
                }
            }

            br.close();

            // INVALID LOGIN
            if(found == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "      ACCOUNT NOT APPROVED OR WRONG DETAILS");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);
            }
        }

        catch(Exception e) {

            System.out.println(BRIGHT_RED);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "                SYSTEM ERROR");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE +
                    "\n" + e + RESET);
        }
    }
}
