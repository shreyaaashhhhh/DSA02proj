package ANALYTICS;

import java.io.*;
import STOCK.StockRepository;
import SECTOR.SectorPerformance;

public class MergeSortStocks {

    // DARK THEME COLORS
    public static final String RESET = "\u001B[0m";

    public static final String BRIGHT_CYAN = "\u001B[96m";

    public static final String BRIGHT_GREEN = "\u001B[92m";

    public static final String BRIGHT_RED = "\u001B[91m";

    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static final String BRIGHT_WHITE = "\u001B[97m";

    public static final String BRIGHT_BLUE = "\u001B[94m";

    String stockNames[] = new String[500];

    int stockPrices[] = new int[500];

    int count = 0;

    public void loadStocks() {

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(StockRepository.getStockFile()));

            String line;

            while((line = br.readLine()) != null) {

                String data[] = line.split(",");

                stockNames[count] = data[0];

                stockPrices[count] =
                        Integer.parseInt(data[1]);

                count++;
            }

            br.close();

            // SORT STOCKS
            mergeSort(0, count - 1);

            // HEADER
            System.out.println("\n");

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "             STOCKS SORTED BY PRICE");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);

            // TABLE HEADER
            System.out.println(BRIGHT_YELLOW);

            System.out.printf(
                    "%-10s %-25s %-15s\n",
                    "RANK",
                    "STOCK NAME",
                    "PRICE");

            System.out.println(
                    "----------------------------------------------------");

            System.out.println(RESET);

            // DISPLAY STOCKS
            for(int i = 0; i < count; i++) {

                System.out.println(BRIGHT_GREEN);

                System.out.printf(
                        "%-10d %-25s ₹%-15d\n",
                        (i + 1),
                        stockNames[i],
                        stockPrices[i]);

                System.out.println(RESET);
            }

            System.out.println(BRIGHT_CYAN);

            System.out.println(
                    "====================================================");

            System.out.println(
                    "              SORTING COMPLETED");

            System.out.println(
                    "====================================================");

            System.out.println(RESET);
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

    // MERGE SORT
    public void mergeSort(int low, int high) {

        if(low < high) {

            int mid = (low + high) / 2;

            mergeSort(low, mid);

            mergeSort(mid + 1, high);

            merge(low, mid, high);
        }
    }

    // MERGE FUNCTION
    public void merge(int low,
                      int mid,
                      int high) {

        int tempPrices[] = new int[500];

        String tempNames[] = new String[500];

        int i = low;

        int j = mid + 1;

        int k = low;

        while(i <= mid && j <= high) {

            if(stockPrices[i] < stockPrices[j]) {

                tempPrices[k] = stockPrices[i];

                tempNames[k] = stockNames[i];

                i++;
            }

            else {

                tempPrices[k] = stockPrices[j];

                tempNames[k] = stockNames[j];

                j++;
            }

            k++;
        }

        while(i <= mid) {

            tempPrices[k] = stockPrices[i];

            tempNames[k] = stockNames[i];

            i++;
            k++;
        }

        while(j <= high) {

            tempPrices[k] = stockPrices[j];

            tempNames[k] = stockNames[j];

            j++;
            k++;
        }

        for(i = low; i <= high; i++) {

            stockPrices[i] = tempPrices[i];

            stockNames[i] = tempNames[i];
        }
    }

    // MERGE SORT FOR SECTOR PERFORMANCE RANKING
    public void sortSectorsByPerformance(SectorPerformance sectors[],
                                         int count) {

        mergeSortSectors(sectors,
                0,
                count - 1);
    }

    private void mergeSortSectors(SectorPerformance sectors[],
                                  int low,
                                  int high) {

        if(low < high) {

            int mid =
                    (low + high) / 2;

            mergeSortSectors(sectors,
                    low,
                    mid);

            mergeSortSectors(sectors,
                    mid + 1,
                    high);

            mergeSectors(sectors,
                    low,
                    mid,
                    high);
        }
    }

    private void mergeSectors(SectorPerformance sectors[],
                              int low,
                              int mid,
                              int high) {

        SectorPerformance temp[] =
                new SectorPerformance[sectors.length];

        int i =
                low;

        int j =
                mid + 1;

        int k =
                low;

        while(i <= mid && j <= high) {

            if(sectors[i].averageGrowth
                    >= sectors[j].averageGrowth) {

                temp[k] =
                        sectors[i];

                i++;
            }

            else {

                temp[k] =
                        sectors[j];

                j++;
            }

            k++;
        }

        while(i <= mid) {

            temp[k] =
                    sectors[i];

            i++;
            k++;
        }

        while(j <= high) {

            temp[k] =
                    sectors[j];

            j++;
            k++;
        }

        for(i = low; i <= high; i++) {

            sectors[i] =
                    temp[i];
        }
    }
}
