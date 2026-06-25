package SECTOR;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import ANALYTICS.MergeSortStocks;
import STOCK.BTree;
import STOCK.Stock;
import STOCK.StockRecord;
import STOCK.StockRepository;
import UTIL.Input;

public class SectorAnalytics {

    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void viewStocksBySector() {

        Scanner sc =
                Input.getScanner();

        try {

            SectorGraph graph =
                    new SectorGraph();

            graph.buildGraph();

            String sectors[] =
                    graph.getSectors();

            if(sectors.length == 0) {

                printNoStocks();

                return;
            }

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "              AVAILABLE SECTORS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            for(int i = 0; i < sectors.length; i++) {

                System.out.println(BRIGHT_GREEN +
                        "   [" + (i + 1) + "] " + sectors[i]
                        + RESET);
            }

            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Sector Number : "
                    + RESET);

            int choice =
                    sc.nextInt();

            sc.nextLine();

            if(choice < 1 || choice > sectors.length) {

                System.out.println(BRIGHT_RED +
                        "\n   Invalid Sector Choice"
                        + RESET);

                return;
            }

            List<Stock> stocks =
                    graph.getStocksBySector(sectors[choice - 1]);

            printStockTable("STOCKS IN " + sectors[choice - 1].toUpperCase(),
                    stocks);
        }

        catch(Exception e) {

            printError(e);
        }
    }

    public void similarStockRecommendations() {

        Scanner sc =
                Input.getScanner();

        try {

            StockRepository repository =
                    new StockRepository();

            StockRecord stocks[] =
                    repository.loadStocks();

            if(stocks.length == 0) {

                printNoStocks();

                return;
            }

            Trie trie =
                    buildTrie(stocks);

            BTree tree =
                    repository.loadStockTree();

            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Stock Name Or Prefix : "
                    + RESET);

            String stockName =
                    sc.nextLine();

            StockRecord selectedStock =
                    tree.search(stockName);

            if(selectedStock == null) {

                showSuggestions(trie,
                        stockName);

                return;
            }

            SectorGraph graph =
                    new SectorGraph();

            graph.buildGraph();

            List<Stock> sameSectorStocks =
                    graph.getStocksBySector(selectedStock.sector);

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "          SIMILAR STOCK RECOMMENDATIONS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_WHITE +
                    "\n   Selected Stock : " + selectedStock.stockName);

            System.out.println(
                    "   Sector         : " + selectedStock.sector
                    + RESET);

            boolean found =
                    false;

            for(int i = 0; i < sameSectorStocks.size(); i++) {

                Stock stock =
                        sameSectorStocks.get(i);

                if(!stock.stockName.equalsIgnoreCase(selectedStock.stockName)) {

                    if(found == false) {

                        printStockHeader();
                    }

                    printStockRow(i + 1,
                            stock);

                    found = true;
                }
            }

            if(found == false) {

                System.out.println(BRIGHT_RED +
                        "\n   No similar stocks found in this sector."
                        + RESET);
            }
        }

        catch(Exception e) {

            printError(e);
        }
    }

    public void topPerformingSectors() {

        try {

            SectorGraph graph =
                    new SectorGraph();

            graph.buildGraph();

            String sectors[] =
                    graph.getSectors();

            if(sectors.length == 0) {

                printNoStocks();

                return;
            }

            SectorPerformance performances[] =
                    new SectorPerformance[sectors.length];

            for(int i = 0; i < sectors.length; i++) {

                List<Stock> stocks =
                        graph.getStocksBySector(sectors[i]);

                double totalGrowth =
                        0;

                for(int j = 0; j < stocks.size(); j++) {

                    totalGrowth +=
                            calculateGrowth(stocks.get(j));
                }

                double averageGrowth =
                        totalGrowth / stocks.size();

                performances[i] =
                        new SectorPerformance(sectors[i],
                                stocks.size(),
                                averageGrowth);
            }

            MergeSortStocks mergeSort =
                    new MergeSortStocks();

            mergeSort.sortSectorsByPerformance(performances,
                    performances.length);

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "\n====================================================");

            System.out.println(
                    "              TOP PERFORMING SECTORS");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-10s %-20s %-15s %-15s\n",
                    "RANK",
                    "SECTOR",
                    "STOCKS",
                    "AVG GROWTH");

            System.out.println(
                    "--------------------------------------------------------------");

            System.out.println(RESET);

            for(int i = 0; i < performances.length; i++) {

                System.out.println(BRIGHT_GREEN);

                System.out.printf(
                        "%-10d %-20s %-15d %.2f%%\n",
                        (i + 1),
                        performances[i].sectorName,
                        performances[i].stockCount,
                        performances[i].averageGrowth);

                System.out.println(RESET);
            }
        }

        catch(Exception e) {

            printError(e);
        }
    }

    public void topGainers() {

        showTopStocks(true);
    }

    public void topLosers() {

        showTopStocks(false);
    }

    public Trie buildTrie(StockRecord stocks[]) {

        Trie trie =
                new Trie();

        for(int i = 0; i < stocks.length; i++) {

            trie.insert(stocks[i].stockName);
        }

        return trie;
    }

    public void showSuggestions(Trie trie,
                                String prefix) {

        String suggestions[] =
                trie.searchByPrefix(prefix);

        if(suggestions.length == 0) {

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

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "\n====================================================");

        System.out.println(
                "              AUTO COMPLETE MATCHES");

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        for(int i = 0; i < suggestions.length; i++) {

            System.out.println(BRIGHT_GREEN +
                    "   " + suggestions[i]
                    + RESET);
        }
    }

    private void showTopStocks(boolean gainers) {

        try {

            StockRepository repository =
                    new StockRepository();

            StockRecord records[] =
                    repository.loadStocks();

            if(records.length == 0) {

                printNoStocks();

                return;
            }

            Comparator<Stock> comparator;

            if(gainers) {

                comparator =
                        new Comparator<Stock>() {

                            public int compare(Stock first,
                                               Stock second) {

                                return Double.compare(
                                        calculateGrowth(first),
                                        calculateGrowth(second));
                            }
                        };
            }

            else {

                comparator =
                        new Comparator<Stock>() {

                            public int compare(Stock first,
                                               Stock second) {

                                return Double.compare(
                                        calculateGrowth(second),
                                        calculateGrowth(first));
                            }
                        };
            }

            // Heap keeps only top 5 records, avoiding a full stock sort.
            PriorityQueue<Stock> heap =
                    new PriorityQueue<Stock>(comparator);

            for(int i = 0; i < records.length; i++) {

                heap.add(new Stock(records[i]));

                if(heap.size() > 5) {

                    heap.poll();
                }
            }

            Stock result[] =
                    new Stock[heap.size()];

            int index =
                    result.length - 1;

            while(!heap.isEmpty()) {

                result[index] =
                        heap.poll();

                index--;
            }

            printTopStockTable(gainers,
                    result);
        }

        catch(Exception e) {

            printError(e);
        }
    }

    private double calculateGrowth(StockRecord stock) {

        if(stock.oldPrice == 0) {

            return 0;
        }

        return ((double)(stock.currentPrice - stock.oldPrice)
                / stock.oldPrice) * 100;
    }

    private void printStockTable(String title,
                                 List<Stock> stocks) {

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "\n====================================================");

        System.out.println(
                "              " + title);

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        printStockHeader();

        for(int i = 0; i < stocks.size(); i++) {

            printStockRow(i + 1,
                    stocks.get(i));
        }
    }

    private void printStockHeader() {

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
    }

    private void printStockRow(int id,
                               StockRecord stock) {

        System.out.println(BRIGHT_WHITE);

        System.out.printf(
                "%-10d %-25s Rs.%-12d %-15s\n",
                id,
                stock.stockName,
                stock.currentPrice,
                stock.sector);

        System.out.println(RESET);
    }

    private void printTopStockTable(boolean gainers,
                                    Stock result[]) {

        System.out.println(BRIGHT_CYAN);

        System.out.println(
                "\n====================================================");

        if(gainers) {

            System.out.println(
                    "                   TOP GAINERS");
        }

        else {

            System.out.println(
                    "                   TOP LOSERS");
        }

        System.out.println(
                "====================================================");

        System.out.println(RESET);

        System.out.println(BRIGHT_YELLOW);

        System.out.printf(
                "%-10s %-25s %-15s %-15s\n",
                "RANK",
                "STOCK NAME",
                "SECTOR",
                "GROWTH");

        System.out.println(
                "--------------------------------------------------------------");

        System.out.println(RESET);

        for(int i = 0; i < result.length; i++) {

            System.out.println(BRIGHT_GREEN);

            System.out.printf(
                    "%-10d %-25s %-15s %.2f%%\n",
                    (i + 1),
                    result[i].stockName,
                    result[i].sector,
                    calculateGrowth(result[i]));

            System.out.println(RESET);
        }
    }

    private void printNoStocks() {

        System.out.println(BRIGHT_RED);

        System.out.println(
                "\n====================================================");

        System.out.println(
                "               NO STOCKS AVAILABLE");

        System.out.println(
                "====================================================");

        System.out.println(RESET);
    }

    private void printError(Exception e) {

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
