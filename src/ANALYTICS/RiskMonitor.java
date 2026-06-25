package ANALYTICS;

import java.io.*;
import STOCK.StockRepository;

public class RiskMonitor {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void checkRisk(String username) {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            String line;

            boolean riskFound = false;

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                MARKET RISK REPORT");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                int oldPrice =
                        Integer.parseInt(data[1]);

                int newPrice =
                        Integer.parseInt(data[2]);

                int difference =
                        newPrice - oldPrice;

                // SHOW ONLY NEGATIVE STOCKS
                if(difference < 0) {

                    riskFound = true;

                    System.out.println(BRIGHT_RED);

                    System.out.println(
                            "----------------------------------------------------");

                    System.out.println(
                            "   STOCK NAME      : " + data[0]);

                    System.out.println(
                            "   PREVIOUS PRICE  : ₹" + oldPrice);

                    System.out.println(
                            "   CURRENT PRICE   : ₹" + newPrice);

                    System.out.println(
                            "   LOSS            : ₹" + difference);

                    System.out.println(
                            "   STATUS          : HIGH RISK");

                    System.out.println(
                            "----------------------------------------------------");

                    System.out.println(RESET);
                }
            }

            br.close();

            // NO RISK FOUND
            if(riskFound == false) {

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "            NO RISKY STOCKS FOUND");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                System.out.println(BRIGHT_WHITE +
                        "\n   Market Looks Stable Right Now!"
                        + RESET);
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
