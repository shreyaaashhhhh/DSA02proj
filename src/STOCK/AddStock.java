package STOCK;

import java.util.Scanner;
import UTIL.Input;

public class AddStock {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void add() {

        Scanner sc = Input.getScanner();

        String stockName;

        int stockPrice;

        String sector;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "====================================================");

        System.out.println(
                "                    ADD STOCK");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        // STOCK NAME
        System.out.print(BRIGHT_YELLOW +
                "   Enter Stock Name : " + RESET);

        stockName = sc.nextLine();

        // STOCK PRICE
        System.out.print(BRIGHT_YELLOW +
                "   Enter Stock Price : Rs." + RESET);

        stockPrice = sc.nextInt();

        sc.nextLine();

        // STOCK SECTOR
        System.out.println(BRIGHT_YELLOW);

        System.out.println("\n   Available Sectors:");
        System.out.println("   Technology");
        System.out.println("   Banking");
        System.out.println("   Oil & Gas");
        System.out.println("   Healthcare");
        System.out.println("   FMCG");

        System.out.print("\n   Enter Sector : " + RESET);

        sector = sc.nextLine();

        try {

            StockRepository repository =
                    new StockRepository();

            BTree tree =
                    repository.loadStockTree();

            tree.insert(new Stock(stockName,
                    stockPrice,
                    stockPrice,
                    stockPrice,
                    sector));

            repository.saveStocks(tree);

            // SUCCESS MESSAGE
            System.out.println(BRIGHT_GREEN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "             STOCK ADDED SUCCESSFULLY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // STOCK DETAILS
            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "\n   Stock Name   : " + stockName);

            System.out.println(
                    "   Stock Price  : Rs." + stockPrice);

            System.out.println(
                    "   Sector       : " + sector);

            System.out.println(
                    "   Status       : LISTED ON ALGOTRADE");

            System.out.println(RESET);

            System.out.println(BRIGHT_BLUE +
                    "\n   Market Updated Successfully!"
                    + RESET);
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
