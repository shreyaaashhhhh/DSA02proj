package ANALYTICS;

import java.io.*;
import STOCK.StockRepository;

public class TradingStrategy {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    String stockNames[] = new String[500];

    double percentageGrowth[] = new double[500];

    int currentPrice[] = new int[500];

    int count = 0;

    public void suggest() {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            String line;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                int oldPrice =
                        Integer.parseInt(data[1]);

                int newPrice =
                        Integer.parseInt(data[2]);

                double growth =
                        ((double)(newPrice - oldPrice)
                        / oldPrice) * 100;

                stockNames[count] = data[0];

                currentPrice[count] = newPrice;

                percentageGrowth[count] = growth;

                count++;
            }

            br.close();

            // SORT STOCKS
            sortStocks();

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                TRADING STRATEGY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE +
                    "\n   Recommended Stocks To Buy"
                    + RESET);

            int limit = 3;

            if(count < 3) {

                limit = count;
            }

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "\n%-10s %-20s %-15s %-20s\n",
                    "RANK",
                    "STOCK",
                    "PRICE",
                    "GROWTH %");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            int rank = 1;

            // SHOW ONLY BEST PROFITABLE STOCKS
            for(int i = count - 1;
                i >= count - limit;
                i--) {

                // ONLY POSITIVE GROWTH STOCKS
                if(percentageGrowth[i] > 0) {

                    System.out.println(BRIGHT_GREEN);

                    System.out.printf(
                            "%-10d %-20s ₹%-14d %-20s\n",
                            rank,
                            stockNames[i],
                            currentPrice[i],
                            String.format("%.2f",
                                    percentageGrowth[i]) + "%");

                    System.out.println(RESET);

                    rank++;
                }
            }

            // BEST RECOMMENDATION
            int bestIndex = count - 1;

            if(percentageGrowth[bestIndex] > 0) {

                System.out.println(BRIGHT_CYAN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "               BEST BUY RECOMMENDATION");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n   Stock Name       : "
                        + stockNames[bestIndex]);

                System.out.println(
                        "   Current Price    : ₹"
                        + currentPrice[bestIndex]);

                System.out.println(
                        "   Growth Percentage: "
                        + String.format("%.2f",
                        percentageGrowth[bestIndex])
                        + "%");

                System.out.println(
                        "   Strategy         : STRONG BUY");

                System.out.println(RESET);
            }

            else {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "           NO PROFITABLE STOCKS FOUND");

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

    // BUBBLE SORT
    public void sortStocks() {

        for(int i = 0; i < count - 1; i++) {

            for(int j = 0;
                j < count - i - 1;
                j++) {

                if(percentageGrowth[j]
                        >
                        percentageGrowth[j + 1]) {

                    swap(j, j + 1);
                }
            }
        }
    }

    // SWAP
    public void swap(int i, int j) {

        double tempGrowth =
                percentageGrowth[i];

        percentageGrowth[i] =
                percentageGrowth[j];

        percentageGrowth[j] =
                tempGrowth;

        int tempPrice =
                currentPrice[i];

        currentPrice[i] =
                currentPrice[j];

        currentPrice[j] =
                tempPrice;

        String tempName =
                stockNames[i];

        stockNames[i] =
                stockNames[j];

        stockNames[j] =
                tempName;
    }
}
