package USER;

import java.io.*;
import STOCK.StockRepository;

public class Portfolio {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void showPortfolio(String username) {

        try {

            File portfolioFile =
                    new File("Portfolio.txt");

            // FILE CHECK
            if(!portfolioFile.exists()) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "               NO PORTFOLIO FOUND");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                return;
            }

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(portfolioFile));

            String line;

            boolean found = false;

            int totalInvestment = 0;

            int totalCurrentValue = 0;

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                  YOUR PORTFOLIO");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-15s %-10s %-15s %-15s %-15s\n",
                    "STOCK",
                    "SHARES",
                    "BUY PRICE",
                    "CURRENT",
                    "P/L");

            System.out.println(
                    "--------------------------------------------------------------");

            System.out.println(RESET);

            // READ PORTFOLIO
            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(username)) {

                    String stockName =
                            data[1];

                    int shares =
                            Integer.parseInt(data[2]);

                    int buyPrice =
                            Integer.parseInt(data[3]);

                    int currentPrice =
                            getCurrentPrice(stockName);

                    int investedAmount =
                            buyPrice * shares;

                    int currentValue =
                            currentPrice * shares;

                    int profitLoss =
                            currentValue
                            - investedAmount;

                    totalInvestment += investedAmount;

                    totalCurrentValue += currentValue;

                    found = true;

                    // PROFIT
                    if(profitLoss > 0) {

                        System.out.println(BRIGHT_GREEN);

                        System.out.printf(
                                "%-15s %-10d ₹%-14d ₹%-14d +₹%-10d\n",
                                stockName,
                                shares,
                                buyPrice,
                                currentPrice,
                                profitLoss);

                        System.out.println(RESET);
                    }

                    // LOSS
                    else if(profitLoss < 0) {

                        System.out.println(BRIGHT_RED);

                        System.out.printf(
                                "%-15s %-10d ₹%-14d ₹%-14d ₹%-10d\n",
                                stockName,
                                shares,
                                buyPrice,
                                currentPrice,
                                profitLoss);

                        System.out.println(RESET);
                    }

                    // NO CHANGE
                    else {

                        System.out.println(BRIGHT_YELLOW);

                        System.out.printf(
                                "%-15s %-10d ₹%-14d ₹%-14d ₹0\n",
                                stockName,
                                shares,
                                buyPrice,
                                currentPrice);

                        System.out.println(RESET);
                    }
                }
            }

            br.close();

            // NO STOCKS
            if(found == false) {

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

            // SUMMARY
            int totalProfitLoss =
                    totalCurrentValue
                    - totalInvestment;

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "                PORTFOLIO SUMMARY");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "\n   Total Investment : ₹"
                    + totalInvestment);

            System.out.println(
                    "   Current Value    : ₹"
                    + totalCurrentValue);

            // OVERALL PROFIT
            if(totalProfitLoss > 0) {

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "   Overall Profit   : +₹"
                        + totalProfitLoss);

                System.out.println(
                        "   Portfolio Status : PROFIT");

                System.out.println(RESET);
            }

            // OVERALL LOSS
            else if(totalProfitLoss < 0) {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "   Overall Loss     : ₹"
                        + totalProfitLoss);

                System.out.println(
                        "   Portfolio Status : LOSS");

                System.out.println(RESET);
            }

            // STABLE
            else {

                System.out.println(BRIGHT_YELLOW);

                System.out.println(
                        "   No Profit No Loss");

                System.out.println(
                        "   Portfolio Status : STABLE");

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

    // GET CURRENT STOCK PRICE
    public int getCurrentPrice(String stockName) {

        int currentPrice = 0;

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            String line;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                if(data[0].equalsIgnoreCase(stockName)) {

                    currentPrice =
                            Integer.parseInt(data[2]);
                }
            }

            br.close();
        }

        catch(Exception e) {

            System.out.println(e);
        }

        return currentPrice;
    }
}
