package STOCK;

import java.util.List;
import java.util.Scanner;
import SECTOR.SectorAnalytics;
import SECTOR.SectorGraph;
import SECTOR.Trie;
import UTIL.Input;

public class SearchStock {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void search() {

        Scanner sc = Input.getScanner();

        String stockName;

        // HEADER
        System.out.println("\n");

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "====================================================");

        System.out.println(
                "                  SEARCH STOCK");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        // INPUT
        System.out.print(BRIGHT_YELLOW +
                "   Enter Stock Name Or Prefix : "
                + RESET);

        stockName = sc.nextLine();

        try {

            StockRepository repository =
                    new StockRepository();

            StockRecord stocks[] =
                    repository.loadStocks();

            SectorAnalytics analytics =
                    new SectorAnalytics();

            Trie trie =
                    analytics.buildTrie(stocks);

            BTree tree =
                    repository.loadStockTree();

            StockRecord stock =
                    tree.search(stockName);

            if(stock != null) {

                int oldPrice =
                        stock.oldPrice;

                int currentPrice =
                        stock.currentPrice;

                int difference =
                        currentPrice - oldPrice;

                double growth =
                        ((double) difference
                        / oldPrice) * 100;

                // STOCK DETAILS HEADER
                System.out.println(BRIGHT_CYAN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                 STOCK DETAILS");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                // STOCK DETAILS
                System.out.println(BRIGHT_WHITE);

                System.out.println(
                        "\n   Stock Name      : "
                        + stock.stockName);

                System.out.println(
                        "   Sector          : "
                        + stock.sector);

                System.out.println(
                        "   Old Price       : Rs."
                        + oldPrice);

                System.out.println(
                        "   Current Price   : Rs."
                        + currentPrice);

                // POSITIVE GROWTH
                if(difference > 0) {

                    System.out.println(BRIGHT_GREEN);

                    System.out.println(
                            "   Profit          : +Rs."
                            + difference);

                    System.out.println(
                            "   Growth %        : +"
                            + String.format("%.2f",
                            growth)
                            + "%");

                    System.out.println(
                            "   Market Status   : BULLISH");

                    System.out.println(RESET);
                }

                // NEGATIVE GROWTH
                else if(difference < 0) {

                    System.out.println(BRIGHT_RED);

                    System.out.println(
                            "   Loss            : Rs."
                            + difference);

                    System.out.println(
                            "   Growth %        : "
                            + String.format("%.2f",
                            growth)
                            + "%");

                    System.out.println(
                            "   Market Status   : BEARISH");

                    System.out.println(RESET);
                }

                // NO CHANGE
                else {

                    System.out.println(BRIGHT_YELLOW);

                    System.out.println(
                            "   No Change In Price");

                    System.out.println(
                            "   Market Status   : STABLE");

                    System.out.println(RESET);
                }

                printSimilarStocks(stock);
            }

            // TRIE AUTO COMPLETE FOR PARTIAL SEARCH
            if(stock == null) {

                analytics.showSuggestions(trie,
                        stockName);
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

    private void printSimilarStocks(StockRecord selectedStock)
            throws Exception {

        SectorGraph graph =
                new SectorGraph();

        graph.buildGraph();

        List<Stock> stocks =
                graph.getStocksBySector(selectedStock.sector);

        boolean found =
                false;

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "\n====================================================");

        System.out.println(
                "          SIMILAR STOCK RECOMMENDATIONS");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        for(int i = 0; i < stocks.size(); i++) {

            Stock stock =
                    stocks.get(i);

            if(!stock.stockName.equalsIgnoreCase(selectedStock.stockName)) {

                System.out.println(BRIGHT_GREEN +
                        "   " + stock.stockName
                        + " | Rs." + stock.currentPrice
                        + " | " + stock.sector
                        + RESET);

                found =
                        true;
            }
        }

        if(found == false) {

            System.out.println(BRIGHT_RED +
                    "   No similar stocks found."
                    + RESET);
        }
    }
}
