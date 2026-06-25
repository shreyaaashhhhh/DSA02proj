package ADMIN;

import java.util.Scanner;
import UTIL.Input;

import STOCK.AddStock;
import STOCK.RemoveStock;
import STOCK.UpdateStockPrice;
import STOCK.SearchStock;
import STOCK.DisplayStocks;
import STOCK.RangeStockSearch;

public class AdminMenu {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    public void showMenu() {

        Scanner sc = Input.getScanner();

        int choice;

        while(true) {

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println("====================================================");
            System.out.println("                                                    ");
            System.out.println("               ADMIN DASHBOARD                      ");
            System.out.println("                                                    ");
            System.out.println("====================================================");

            System.out.println(RESET);

            // MENU OPTIONS
            System.out.println(BRIGHT_WHITE +
                    "                  CONTROL PANEL");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(BRIGHT_GREEN +
                    "   [1] View Pending Users");

            System.out.println(BRIGHT_GREEN +
                    "   [2] Approve User");

            System.out.println(BRIGHT_GREEN +
                    "   [3] Add Stock");

            System.out.println(BRIGHT_GREEN +
                    "   [4] Remove Stock");

            System.out.println(BRIGHT_GREEN +
                    "   [5] Update Stock Price");

            System.out.println(BRIGHT_GREEN +
                    "   [6] Search Stock");

            System.out.println(BRIGHT_GREEN +
                    "   [7] Display Stocks");

            System.out.println(BRIGHT_GREEN +
                    "   [8] Search Stocks By Price Range");

            System.out.println(BRIGHT_RED +
                    "   [9] Logout");

            System.out.println(BRIGHT_WHITE +
                    "----------------------------------------------------");

            // INPUT
            System.out.print(BRIGHT_YELLOW +
                    "\n   Enter Choice : " + RESET);

            choice = sc.nextInt();

            sc.nextLine();

            // VIEW PENDING USERS
            if(choice == 1) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Pending Users..."
                        + RESET);

                ViewPendingUsers view =
                        new ViewPendingUsers();

                view.showUsers();
            }

            // APPROVE USER
            else if(choice == 2) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Approval System..."
                        + RESET);

                ApproveUser approve =
                        new ApproveUser();

                approve.approve();
            }

            // ADD STOCK
            else if(choice == 3) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Add Stock..."
                        + RESET);

                AddStock add =
                        new AddStock();

                add.add();
            }

            // REMOVE STOCK
            else if(choice == 4) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Remove Stock..."
                        + RESET);

                RemoveStock remove =
                        new RemoveStock();

                remove.remove();
            }

            // UPDATE PRICE
            else if(choice == 5) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Stock Price Update..."
                        + RESET);

                UpdateStockPrice update =
                        new UpdateStockPrice();

                update.update();
            }

            // SEARCH STOCK
            else if(choice == 6) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Stock Search..."
                        + RESET);

                SearchStock search =
                        new SearchStock();

                search.search();
            }

            // DISPLAY STOCKS
            else if(choice == 7) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Loading Stock Market..."
                        + RESET);

                DisplayStocks display =
                        new DisplayStocks();

                display.display();
            }

            // RANGE SEARCH
            else if(choice == 8) {

                System.out.println(BRIGHT_BLUE +
                        "\n   Opening Price Range Search..."
                        + RESET);

                RangeStockSearch rangeSearch =
                        new RangeStockSearch();

                rangeSearch.searchByRange();
            }

            // LOGOUT
            else if(choice == 9) {

                System.out.println(BRIGHT_GREEN);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "             LOGGED OUT SUCCESSFULLY");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);

                break;
            }

            // INVALID
            else {

                System.out.println(BRIGHT_RED);

                System.out.println(
                        "\n====================================================");

                System.out.println(
                        "                INVALID CHOICE");

                System.out.println(
                        "====================================================");

                System.out.println(RESET);
            }
        }
    }
}
