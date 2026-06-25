package MAIN;

import LOGIN.AdminLogin;
import LOGIN.UserLogin;
import LOGIN.UserRegister;
import UTIL.Input;
import java.util.Scanner;

public class Main_page {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BLACK_BG = "\u001B[40m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public static void main(String[] args) {

        Main_page obj = new Main_page();

        obj.showMenu();
    }

    public void showMenu() {

        Scanner sc = Input.getScanner();

        int choice;

        while(true) {

            // CLEAR SPACING
            System.out.println("\n\n");

            // DARK THEME HEADER
            System.out.println(BLACK_BG + BRIGHT_CYAN);

            System.out.println("====================================================");
            System.out.println("                                                    ");
            System.out.println("               ALGOTRADE SYSTEM                     ");
            System.out.println("                                                    ");
            System.out.println("====================================================");

            System.out.println(RESET);

            // MENU OPTIONS
            System.out.println(BRIGHT_WHITE + "               MAIN MENU");
            System.out.println("----------------------------------------------------");

            System.out.println(BRIGHT_GREEN + "   [1] ADMIN Login");

            System.out.println(BRIGHT_GREEN + "   [2] User Register");

            System.out.println(BRIGHT_GREEN + "   [3] User Login");

            System.out.println(BRIGHT_RED + "   [4] Exit");

            System.out.println(BRIGHT_WHITE +
                    "----------------------------------------------------");

            // INPUT
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Your Choice : " + RESET);

            choice = sc.nextInt();

            sc.nextLine();

            // ADMIN
            if(choice == 1) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Admin Login..." + RESET);

                AdminLogin admin =
                        new AdminLogin();

                admin.login();
            }

            // REGISTER
            else if(choice == 2) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening User Registration..." + RESET);

                UserRegister reg =
                        new UserRegister();

                reg.register();
            }

            // USER LOGIN
            else if(choice == 3) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening User Login..." + RESET);

                UserLogin user =
                        new UserLogin();

                user.login();
            }

            // EXIT
            else if(choice == 4) {

                System.out.println(BRIGHT_GREEN +
                        "\n====================================================");

                System.out.println("        THANK YOU FOR USING ALGOTRADE");

                System.out.println("===================================================="
                        + RESET);

                break;
            }

            // INVALID
            else {

                System.out.println(BRIGHT_RED +
                        "\n   Invalid Choice! Please Try Again."
                        + RESET);
            }
        }
    }
}
