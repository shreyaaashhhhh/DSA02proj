package STOCK;

import java.io.*;

public class DisplayStocks {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    BTree tree = new BTree();

    int totalStocks = 0;

    public void display() {

        loadStocks();

        // NO STOCKS
        if(totalStocks == 0) {

            System.out.println(BRIGHT_RED);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "               NO STOCKS AVAILABLE");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            return;
        }

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "====================================================");

        System.out.println(
                "                   STOCK MARKET");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        // TABLE HEADER
        System.out.println(BRIGHT_YELLOW);

        System.out.printf(
                "%-10s %-25s %-15s %-15s\n",
                "ID",
                "STOCK NAME",
                "PRICE",
                "SECTOR");

        System.out.println(
                "----------------------------------------------------");

        System.out.println(RESET);

        // DISPLAY STOCKS
        tree.inorder();

        // FOOTER
        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "\n====================================================");

        System.out.println(
                "             TOTAL STOCKS : "
                + totalStocks);

        System.out.println(
                "====================================================");

        System.out.println(RESET);
    }

    // LOAD STOCKS
    public void loadStocks() {

        try {

            tree = new BTree();

            totalStocks = 0;

            StockRepository repository =
                    new StockRepository();

            StockRecord stocks[] =
                    repository.loadStocks();

            for(int i = 0; i < stocks.length; i++) {

                tree.insert(stocks[i]);

                totalStocks++;
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
