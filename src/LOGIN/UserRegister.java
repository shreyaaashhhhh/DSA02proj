package LOGIN;

import java.util.Scanner;
import java.io.*;
import UTIL.Input;

public class UserRegister {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void register() {

        Scanner sc = Input.getScanner();

        String name;
        String number;
        String pan;
        String password;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println("====================================================");
        System.out.println("                                                    ");
        System.out.println("                USER REGISTER                       ");
        System.out.println("                                                    ");
        System.out.println("====================================================");

        System.out.println(RESET);

        // USERNAME
        while(true) {

            System.out.print(BRIGHT_YELLOW +
                    "   Enter Username : " + RESET);

            name = sc.nextLine();

            if(name.length() < 3) {

                System.out.println(BRIGHT_RED +
                        "   Username Too Short!" + RESET);
            }

            else if(checkUser(name)) {

                System.out.println(BRIGHT_RED +
                        "   Username Already Exists!" + RESET);
            }

            else {

                System.out.println(BRIGHT_GREEN +
                        "   Username Accepted" + RESET);

                break;
            }
        }

        // MOBILE NUMBER
        while(true) {

            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Mobile Number : " + RESET);

            number = sc.nextLine();

            if(number.length() == 10) {

                System.out.println(BRIGHT_GREEN +
                        "   Mobile Number Verified" + RESET);

                break;
            }

            else {

                System.out.println(BRIGHT_RED +
                        "   Invalid Mobile Number!" + RESET);
            }
        }

        // PAN NUMBER
        while(true) {

            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter PAN Number : " + RESET);

            pan = sc.nextLine();

            if(pan.length() == 10) {

                System.out.println(BRIGHT_GREEN +
                        "   PAN Number Verified" + RESET);

                break;
            }

            else {

                System.out.println(BRIGHT_RED +
                        "   Invalid PAN Number!" + RESET);
            }
        }

        // PASSWORD
        while(true) {

            System.out.print(BRIGHT_YELLOW +
                    "\n   Create Password : " + RESET);

            password = sc.nextLine();

            boolean capital = false;
            boolean small = false;
            boolean numberCheck = false;
            boolean special = false;

            for(int i = 0; i < password.length(); i++) {

                char ch = password.charAt(i);

                if(Character.isUpperCase(ch)) {

                    capital = true;
                }

                else if(Character.isLowerCase(ch)) {

                    small = true;
                }

                else if(Character.isDigit(ch)) {

                    numberCheck = true;
                }

                else {

                    special = true;
                }
            }

            if(password.length() >= 8
                    && capital
                    && small
                    && numberCheck
                    && special) {

                System.out.println(BRIGHT_GREEN +
                        "   Strong Password Created" + RESET);

                break;
            }

            else {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                 WEAK PASSWORD");

                System.out.println(
                        "====================================================");

                System.out.println(BRIGHT_WHITE);

                System.out.println(" Password Must Contain:");
                System.out.println(" ✔ 1 Capital Letter");
                System.out.println(" ✔ 1 Small Letter");
                System.out.println(" ✔ 1 Number");
                System.out.println(" ✔ 1 Special Character");
                System.out.println(" ✔ Minimum 8 Characters");

                System.out.println(RESET);
            }
        }

        // SAVE USER
        try {

            File file = new File("PendingUsers.txt");

            if(!file.exists()) {

                file.createNewFile();
            }

            FileWriter fw =
                    new FileWriter(file, true);

            fw.write(name + "," +
                    number + "," +
                    pan + "," +
                    password);

            fw.write("\n");

            fw.close();

            // SUCCESS MESSAGE
            System.out.println(BRIGHT_GREEN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "           REGISTRATION SUCCESSFUL");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_BLUE +
                    "\n   Wait For Admin Approval..."
                    + RESET);
        }

        catch(Exception e) {

            System.out.println(BRIGHT_RED);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "                 SYSTEM ERROR");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE +
                    "\n" + e + RESET);
        }
    }

    // CHECK EXISTING USER
    public boolean checkUser(String name) {

        try {

            File file =
                    new File("PendingUsers.txt");

            if(!file.exists()) {

                return false;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(name)) {

                    br.close();

                    return true;
                }
            }

            br.close();
        }

        catch(Exception e) {

            System.out.println(e);
        }

        return false;
    }
}
