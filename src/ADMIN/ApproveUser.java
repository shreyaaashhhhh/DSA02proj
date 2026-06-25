package ADMIN;

import java.util.Scanner;
import java.io.*;
import UTIL.Input;

public class ApproveUser {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void approve() {

        Scanner sc = Input.getScanner();

        File pendingFile =
                new File("PendingUsers.txt");

        // FILE CHECK
        if(!pendingFile.exists()) {

            System.out.println(BRIGHT_RED);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "                NO PENDING USERS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            return;
        }

        try {

            BufferedReader showReader =
                    new BufferedReader(
                            new FileReader(pendingFile));

            String line;

            boolean foundUsers = false;

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

            // DISPLAY USERS
            while((line = showReader.readLine()) != null) {

                System.out.println(BRIGHT_WHITE +
                        "   " + line + RESET);

                foundUsers = true;
            }

            showReader.close();

            // NO USERS
            if(foundUsers == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                NO PENDING USERS");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            String username;

            int choice;

            // USERNAME INPUT
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Username : " + RESET);

            username = sc.nextLine();

            // OPTIONS
            System.out.println(BRIGHT_GREEN);

            System.out.println(
                    "\n   [1] Approve User");

            System.out.println(
                    "   [2] Disapprove User");

            System.out.println(RESET);

            // CHOICE
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Choice : " + RESET);

            choice = sc.nextInt();

            File tempFile =
                    new File("Temp.txt");

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(pendingFile));

            FileWriter tempWriter =
                    new FileWriter(tempFile);

            FileWriter approvedWriter =
                    new FileWriter(
                            "ApprovedUsers.txt",
                            true);

            boolean found = false;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(username)) {

                    found = true;

                    // APPROVE USER
                    if(choice == 1) {

                        approvedWriter.write(line);

                        approvedWriter.write("\n");

                        System.out.println(BRIGHT_GREEN);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "                USER APPROVED");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);

                        System.out.println(BRIGHT_WHITE +
                                "\n   Welcome " + data[0]);

                        System.out.println(
                                "   Your AlgoTrade Account Is Now Active");

                        System.out.println(
                                "   Start Your Trading Journey!"
                                + RESET);
                    }

                    // DISAPPROVE USER
                    else if(choice == 2) {

                        System.out.println(BRIGHT_RED);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "               USER DISAPPROVED");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);

                        System.out.println(BRIGHT_WHITE +
                                "\n   " + data[0]
                                + " Removed From Pending Users"
                                + RESET);
                    }

                    // INVALID OPTION
                    else {

                        System.out.println(BRIGHT_RED);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "                INVALID OPTION");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);
                    }
                }

                else {

                    tempWriter.write(line);

                    tempWriter.write("\n");
                }
            }

            br.close();

            tempWriter.close();

            approvedWriter.close();

            pendingFile.delete();

            tempFile.renameTo(pendingFile);

            // USER NOT FOUND
            if(found == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                 USER NOT FOUND");

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
