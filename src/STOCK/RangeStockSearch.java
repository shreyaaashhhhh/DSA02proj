package STOCK;

import java.util.Scanner;
import UTIL.Input;

public class RangeStockSearch {

    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public void searchByRange() {

        Scanner sc =
                Input.getScanner();

        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "====================================================");

        System.out.println(
                "             STOCK PRICE RANGE SEARCH");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        System.out.print(BRIGHT_YELLOW
                + "   Enter Minimum Price : Rs."
                + RESET);

        int minPrice =
                sc.nextInt();

        System.out.print(BRIGHT_YELLOW
                + "   Enter Maximum Price : Rs."
                + RESET);

        int maxPrice =
                sc.nextInt();

        if(minPrice > maxPrice) {

            int temp =
                    minPrice;

            minPrice =
                    maxPrice;

            maxPrice =
                    temp;
        }

        try {

            StockRepository repository =
                    new StockRepository();

            StockRecord stocks[] =
                    repository.loadStocks();

            StockSegmentTree segmentTree =
                    new StockSegmentTree(stocks);

            StockRecord result[] =
                    segmentTree.searchByPriceRange(minPrice,
                            maxPrice);

            if(result.length == 0) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "          NO STOCKS FOUND IN THIS RANGE");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "              STOCKS IN PRICE RANGE");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-25s %-15s %-15s\n",
                    "STOCK NAME",
                    "OLD PRICE",
                    "CURRENT PRICE");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            for(int i = 0; i < result.length; i++) {

                System.out.println(BRIGHT_WHITE);

                System.out.printf(
                        "%-25s Rs.%-12d Rs.%-12d\n",
                        result[i].stockName,
                        result[i].oldPrice,
                        result[i].currentPrice);

                System.out.println(RESET);
            }

            System.out.println(BRIGHT_GREEN
                    + "\n   Total Stocks Found : "
                    + result.length
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

            System.out.println(BRIGHT_WHITE
                    + "\n" + e + RESET);
        }
    }
}
