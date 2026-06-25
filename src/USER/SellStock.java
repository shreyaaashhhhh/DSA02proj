package USER;

import java.util.Scanner;
import java.io.*;
import STOCK.StockRepository;
import UTIL.Input;

public class SellStock {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void sell(String username) {

        Scanner sc = Input.getScanner();

        try {

            File portfolioFile =
                    new File("Portfolio.txt");

            // FILE CHECK
            if(!portfolioFile.exists()) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                NO STOCKS OWNED");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            BufferedReader showReader =
                    new BufferedReader(
                            new FileReader(portfolioFile));

            String line;

            boolean foundStocks = false;

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                    SELL STOCK");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-25s %-15s\n",
                    "STOCK NAME",
                    "SHARES");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            // DISPLAY USER STOCKS
            while((line = showReader.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(username)) {

                    System.out.println(BRIGHT_WHITE);

                    System.out.printf(
                            "%-25s %-15s\n",
                            data[1],
                            data[2] + " Shares");

                    System.out.println(RESET);

                    foundStocks = true;
                }
            }

            showReader.close();

            // NO STOCKS
            if(foundStocks == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                NO STOCKS OWNED");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            String stockName;

            int quantity;

            boolean found = false;

            int currentPrice = 0;

            // INPUT STOCK NAME
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Stock Name To Sell : "
                    + RESET);

            stockName = sc.nextLine();

            // INPUT QUANTITY
            System.out.print(BRIGHT_YELLOW +
                    "   Enter Quantity To Sell   : "
                    + RESET);

            quantity = sc.nextInt();

            BufferedReader stockReader =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            // GET CURRENT PRICE
            while((line = stockReader.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(stockName)) {

                    currentPrice =
                            Integer.parseInt(data[2]);
                }
            }

            stockReader.close();

            File tempFile =
                    new File("Temp.txt");

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(portfolioFile));

            FileWriter fw =
                    new FileWriter(tempFile);

            // SELL PROCESS
            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(username)
                        &&
                        data[1].equalsIgnoreCase(stockName)) {

                    int oldQuantity =
                            Integer.parseInt(data[2]);

                    // VALID QUANTITY
                    if(oldQuantity >= quantity) {

                        int remaining =
                                oldQuantity - quantity;

                        int totalCredit =
                                currentPrice * quantity;

                        // SELL SUMMARY HEADER
                        System.out.println(BRIGHT_CYAN);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "                  SELL SUMMARY");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);

                        // DETAILS
                        System.out.println(BRIGHT_WHITE);

                        System.out.println(
                                "\n   Stock Name      : "
                                + stockName.toUpperCase());

                        System.out.println(
                                "   Selling Price   : ₹"
                                + currentPrice);

                        System.out.println(
                                "   Quantity Sold   : "
                                + quantity);

                        System.out.println(
                                "   Total Credit    : ₹"
                                + totalCredit);

                        System.out.println(RESET);

                        // PROCESSING
                        System.out.println(BRIGHT_BLUE);

                        System.out.println(
                                "\n   Processing Transaction...");

                        System.out.println(
                                "   Updating Portfolio...");

                        System.out.println(RESET);

                        // SUCCESS
                        System.out.println(BRIGHT_GREEN);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "             TRANSACTION SUCCESSFUL");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);

                        // REMAINING SHARES
                        if(remaining > 0) {

                            fw.write(username
                                    + ","
                                    + stockName
                                    + ","
                                    + remaining
                                    + ","
                                    + data[3]);

                            fw.write("\n");
                        }

                        found = true;
                    }

                    // NOT ENOUGH SHARES
                    else {

                        System.out.println(BRIGHT_RED);

                        System.out.println(
                                "\n====================================================");

                        System.out.println(
                                "               NOT ENOUGH SHARES");

                        System.out.println(
                                "====================================================");

                        System.out.println(RESET);

                        fw.write(line);

                        fw.write("\n");
                    }
                }

                else {

                    fw.write(line);

                    fw.write("\n");
                }
            }

            br.close();

            fw.close();

            portfolioFile.delete();

            tempFile.renameTo(portfolioFile);

            // TRANSACTION HISTORY
            if(found) {

                FileWriter transaction =
                        new FileWriter(
                                "Transactions.txt",
                                true);

                transaction.write(username
                        + " SOLD "
                        + stockName
                        + " Quantity "
                        + quantity);

                transaction.write("\n");

                transaction.close();

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "            STOCK SOLD SUCCESSFULLY");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);
            }

            // STOCK NOT FOUND
            else {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                STOCK NOT FOUND");

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
