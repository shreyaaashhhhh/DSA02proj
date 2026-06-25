package USER;

import java.util.Scanner;

import STOCK.DisplayStocks;
import STOCK.RangeStockSearch;
import SECTOR.SectorAnalytics;
import UTIL.Input;

import ANALYTICS.MergeSortStocks;
import ANALYTICS.QuickSortStocks;
import ANALYTICS.RiskMonitor;
import ANALYTICS.TradingStrategy;

public class UserDashboard {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void menu(String username) {

        Scanner sc = Input.getScanner();

        int choice;

        while(true) {

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "                 USER DASHBOARD");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // WELCOME
            System.out.println(BRIGHT_WHITE);

            System.out.println(
                    "   Welcome Back : "
                    + username.toUpperCase());

            System.out.println(
                    "   Status       : ACTIVE USER");

            System.out.println(RESET);

            // MENU HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.println(
                    "\n----------------------------------------------------");

            System.out.println(
                    "                 AVAILABLE OPTIONS");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            // MENU OPTIONS
            System.out.println(BRIGHT_GREEN +
                    "   [1]  View Stocks");

            System.out.println(BRIGHT_GREEN +
                    "   [2]  Buy Stock");

            System.out.println(BRIGHT_GREEN +
                    "   [3]  Sell Stock");

            System.out.println(BRIGHT_GREEN +
                    "   [4]  View Portfolio");

            System.out.println(BRIGHT_GREEN +
                    "   [5]  Transaction History");

            System.out.println(BRIGHT_GREEN +
                    "   [6]  Sort Stocks");

            System.out.println(BRIGHT_GREEN +
                    "   [7]  View Stocks by Sector");

            System.out.println(BRIGHT_GREEN +
                    "   [8]  Similar Stock Recommendations");

            System.out.println(BRIGHT_GREEN +
                    "   [9]  Top Performing Sectors");

            System.out.println(BRIGHT_GREEN +
                    "   [10] Top Gainers");

            System.out.println(BRIGHT_GREEN +
                    "   [11] Top Losers");

            System.out.println(BRIGHT_GREEN +
                    "   [12] Top Performing Stocks");

            System.out.println(BRIGHT_GREEN +
                    "   [13] Risk Monitor");

            System.out.println(BRIGHT_GREEN +
                    "   [14] Trading Strategy");

            System.out.println(BRIGHT_GREEN +
                    "   [15] Search Stocks By Price Range");

            System.out.println(BRIGHT_RED +
                    "   [16] Logout");

            System.out.println(RESET);

            // INPUT
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Choice : "
                    + RESET);

            choice = sc.nextInt();

            sc.nextLine();

            // VIEW STOCKS
            if(choice == 1) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Loading Stock Market..."
                        + RESET);

                DisplayStocks display =
                        new DisplayStocks();

                display.display();
            }

            // BUY STOCK
            else if(choice == 2) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Buy Portal..."
                        + RESET);

                BuyStock buy =
                        new BuyStock();

                buy.buy(username);
            }

            // SELL STOCK
            else if(choice == 3) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Sell Portal..."
                        + RESET);

                SellStock sell =
                        new SellStock();

                sell.sell(username);
            }

            // PORTFOLIO
            else if(choice == 4) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Loading Portfolio..."
                        + RESET);

                Portfolio p =
                        new Portfolio();

                p.showPortfolio(username);
            }

            // TRANSACTION HISTORY
            else if(choice == 5) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Loading Transaction History..."
                        + RESET);

                TransactionHistory t =
                        new TransactionHistory();

                t.showHistory(username);
            }

            // SORT STOCKS
            else if(choice == 6) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Sorting Stocks..."
                        + RESET);

                MergeSortStocks merge =
                        new MergeSortStocks();

                merge.loadStocks();
            }

            // VIEW STOCKS BY SECTOR
            else if(choice == 7) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Loading Sector Graph..."
                        + RESET);

                SectorAnalytics sectorAnalytics =
                        new SectorAnalytics();

                sectorAnalytics.viewStocksBySector();
            }

            // SIMILAR STOCK RECOMMENDATIONS
            else if(choice == 8) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Finding Similar Stocks..."
                        + RESET);

                SectorAnalytics sectorAnalytics =
                        new SectorAnalytics();

                sectorAnalytics.similarStockRecommendations();
            }

            // TOP PERFORMING SECTORS
            else if(choice == 9) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Ranking Sectors..."
                        + RESET);

                SectorAnalytics sectorAnalytics =
                        new SectorAnalytics();

                sectorAnalytics.topPerformingSectors();
            }

            // TOP GAINERS
            else if(choice == 10) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Building Top Gainers Heap..."
                        + RESET);

                SectorAnalytics sectorAnalytics =
                        new SectorAnalytics();

                sectorAnalytics.topGainers();
            }

            // TOP LOSERS
            else if(choice == 11) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Building Top Losers Heap..."
                        + RESET);

                SectorAnalytics sectorAnalytics =
                        new SectorAnalytics();

                sectorAnalytics.topLosers();
            }

            // TOP STOCKS
            else if(choice == 12) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Analyzing Market Trends..."
                        + RESET);

                QuickSortStocks quick =
                        new QuickSortStocks();

                quick.loadStocks();
            }

            // RISK MONITOR
            else if(choice == 13) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Checking Market Risks..."
                        + RESET);

                RiskMonitor risk =
                        new RiskMonitor();

                risk.checkRisk(username);
            }

            // TRADING STRATEGY
            else if(choice == 14) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Generating Trading Strategy..."
                        + RESET);

                TradingStrategy strategy =
                        new TradingStrategy();

                strategy.suggest();
            }

            // RANGE SEARCH
            else if(choice == 15) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Price Range Search..."
                        + RESET);

                RangeStockSearch rangeSearch =
                        new RangeStockSearch();

                rangeSearch.searchByRange();
            }

            // LOGOUT
            else if(choice == 16) {

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             LOGGED OUT SUCCESSFULLY");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                System.out.println(BRIGHT_WHITE +
                        "\n   Thank You For Using AlgoTrade!"
                        + RESET);

                break;
            }

            // INVALID OPTION
            else {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                INVALID CHOICE");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                System.out.println(BRIGHT_WHITE +
                        "\n   Please Enter A Valid Option!"
                        + RESET);
            }
        }
    }
}
