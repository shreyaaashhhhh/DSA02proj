package USER;

import java.io.*;

public class TransactionHistory {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void showHistory(String username) {

        try {

            File file =
                    new File("Transactions.txt");

            // FILE CHECK
            if(!file.exists()) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             NO TRANSACTIONS FOUND");

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

            int transactionCount = 0;

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "               TRANSACTION HISTORY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-10s %-50s\n",
                    "ID",
                    "TRANSACTION DETAILS");

            System.out.println(
                    "--------------------------------------------------------------");

            System.out.println(RESET);

            // DISPLAY TRANSACTIONS
            while((line = br.readLine()) != null) {

                if(line.contains(username)) {

                    transactionCount++;

                    // BUY TRANSACTION
                    if(line.contains("BOUGHT")) {

                        System.out.println(BRIGHT_GREEN);
                    }

                    // SELL TRANSACTION
                    else if(line.contains("SOLD")) {

                        System.out.println(BRIGHT_RED);
                    }

                    else {

                        System.out.println(BRIGHT_WHITE);
                    }

                    System.out.printf(
                            "%-10d %-50s\n",
                            transactionCount,
                            line);

                    System.out.println(RESET);

                    found = true;
                }
            }

            br.close();

            // NO HISTORY
            if(found == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             NO TRANSACTIONS FOUND");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            // SUMMARY
            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "               HISTORY SUMMARY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "\n   Username           : "
                    + username);

            System.out.println(
                    "   Total Transactions : "
                    + transactionCount);

            System.out.println(
                    "   Status             : ACTIVE TRADER");

            System.out.println(RESET);
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