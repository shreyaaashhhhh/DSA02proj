package USER;

import java.util.Scanner;
import java.io.*;
import STOCK.StockRepository;
import UTIL.Input;

public class BuyStock {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void buy(String username) {

        Scanner sc = Input.getScanner();

        String stockName;

        int quantity;

        boolean stockFound = false;

        int stockPrice = 0;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "====================================================");

        System.out.println(
                "                    BUY STOCK");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        // STOCK NAME
        System.out.print(BRIGHT_YELLOW +
                "   Enter Stock Name : "
                + RESET);

        stockName = sc.nextLine();

        // QUANTITY
        System.out.print(BRIGHT_YELLOW +
                "   Enter Quantity   : "
                + RESET);

        quantity = sc.nextInt();

        try {

            BufferedReader stockReader =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            String line;

            // SEARCH STOCK
            while((line = stockReader.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(stockName)) {

                    stockFound = true;

                    stockPrice =
                            Integer.parseInt(data[2]);
                }
            }

            stockReader.close();

            // STOCK NOT FOUND
            if(stockFound == false) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                STOCK NOT FOUND");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            int totalAmount =
                    stockPrice * quantity;

            // PAYMENT SUMMARY HEADER
            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "                 PAYMENT SUMMARY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // DETAILS
            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "\n   Username       : "
                    + username);

            System.out.println(
                    "   Stock Name     : "
                    + stockName.toUpperCase());

            System.out.println(
                    "   Current Price  : ₹"
                    + stockPrice);

            System.out.println(
                    "   Quantity       : "
                    + quantity);

            System.out.println(
                    "   Total Amount   : ₹"
                    + totalAmount);

            System.out.println(RESET);

            // PAYMENT PROCESS
            System.out.println(BRIGHT_BLUE);

            System.out.println(
                    "\n   Processing Payment...");

            System.out.println(
                    "   Verifying Transaction...");

            System.out.println(RESET);

            // SUCCESS PAYMENT
            System.out.println(BRIGHT_GREEN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "               PAYMENT SUCCESSFUL");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            File portfolioFile =
                    new File("Portfolio.txt");

            File tempFile =
                    new File("Temp.txt");

            boolean updated = false;

            BufferedReader br;

            if(portfolioFile.exists()) {

                br = new BufferedReader(
                        new FileReader(portfolioFile));
            }

            else {

                portfolioFile.createNewFile();

                br = new BufferedReader(
                        new FileReader(portfolioFile));
            }

            FileWriter fw =
                    new FileWriter(tempFile);

            // UPDATE PORTFOLIO
            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(username)
                        &&
                        data[1].equalsIgnoreCase(stockName)) {

                    int oldQuantity =
                            Integer.parseInt(data[2]);

                    int newQuantity =
                            oldQuantity + quantity;

                    fw.write(username
                            + ","
                            + stockName
                            + ","
                            + newQuantity
                            + ","
                            + stockPrice);

                    fw.write("\n");

                    updated = true;
                }

                else {

                    fw.write(line);

                    fw.write("\n");
                }
            }

            // NEW STOCK ENTRY
            if(updated == false) {

                fw.write(username
                        + ","
                        + stockName
                        + ","
                        + quantity
                        + ","
                        + stockPrice);

                fw.write("\n");
            }

            br.close();

            fw.close();

            portfolioFile.delete();

            tempFile.renameTo(portfolioFile);

            // TRANSACTION HISTORY
            FileWriter transaction =
                    new FileWriter(
                            "Transactions.txt",
                            true);

            transaction.write(username
                    + " BOUGHT "
                    + stockName
                    + " Quantity "
                    + quantity
                    + " Amount "
                    + totalAmount);

            transaction.write("\n");

            transaction.close();

            // FINAL SUCCESS
            System.out.println(BRIGHT_GREEN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "          STOCK PURCHASED SUCCESSFULLY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "\n   Added To Portfolio");

            System.out.println(
                    "   Transaction Saved Successfully");

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
