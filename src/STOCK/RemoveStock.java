package STOCK;

import java.util.Scanner;
import UTIL.Input;

public class RemoveStock {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void remove() {

        Scanner sc = Input.getScanner();

        try {

            StockRepository repository =
                    new StockRepository();

            BTree tree =
                    repository.loadStockTree();

            StockRecord stocks[] =
                    tree.toArray();

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                AVAILABLE STOCKS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            if(tree.size() == 0) {

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

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-25s %-15s %-15s\n",
                    "STOCK NAME",
                    "OLD PRICE",
                    "CURRENT PRICE");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            // DISPLAY STOCKS FROM B-TREE
            for(int i = 0; i < stocks.length; i++) {

                System.out.println(BRIGHT_WHITE);

                System.out.printf(
                        "%-25s Rs.%-12d Rs.%-12d\n",
                        stocks[i].stockName,
                        stocks[i].oldPrice,
                        stocks[i].currentPrice);

                System.out.println(RESET);
            }

            String stockName;

            // INPUT
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Stock Name To Remove : "
                    + RESET);

            stockName = sc.nextLine();

            boolean found =
                    tree.delete(stockName);

            repository.saveStocks(tree);

            // SUCCESS
            if(found) {

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "            STOCK REMOVED SUCCESSFULLY");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                System.out.println(BRIGHT_WHITE +
                        "\n   Removed Stock : "
                        + stockName.toUpperCase()
                        + RESET);
            }

            // NOT FOUND
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
