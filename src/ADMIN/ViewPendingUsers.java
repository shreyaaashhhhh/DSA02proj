package ADMIN;

import java.io.*;

public class ViewPendingUsers {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void showUsers() {

        try {

            File file =
                    new File("PendingUsers.txt");

            // FILE NOT FOUND
            if(!file.exists()) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             NO PENDING USERS FOUND");

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

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                  PENDING USERS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // USER LIST
            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                found = true;

                System.out.println(BRIGHT_WHITE);

                System.out.println(
                        "----------------------------------------------------");

                System.out.println(
                        "   Username : " + data[0]);

                System.out.println(
                        "   Mobile   : " + data[1]);

                System.out.println(
                        "   PAN No   : " + data[2]);

                System.out.println(
                        "----------------------------------------------------");

                System.out.println(RESET);
            }

            br.close();

            // EMPTY FILE
            if(found == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                NO USERS AVAILABLE");

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
                    "                  SYSTEM ERROR");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE +
                    "\n" + e + RESET);
        }
    }
}