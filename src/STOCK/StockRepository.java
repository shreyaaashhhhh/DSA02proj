package STOCK;

import java.io.*;

public class StockRepository {

    public static File getStockFile() {

        File currentFolderFile =
                new File("Stocks.txt");

        if(currentFolderFile.exists()) {

            return currentFolderFile;
        }

        File projectFolderFile =
                new File("HACKATHON2", "Stocks.txt");

        if(projectFolderFile.exists()) {

            return projectFolderFile;
        }

        return currentFolderFile;
    }

    public StockRecord[] loadStocks() throws Exception {

        StockRecord records[] =
                new StockRecord[500];

        File file =
                getStockFile();

        if(!file.exists()) {

            return new StockRecord[0];
        }

        BufferedReader br =
                new BufferedReader(
                        new FileReader(file));

        String line;

        int count = 0;

        while((line = br.readLine()) != null) {

            String data[] = line.split(",");

            if(data.length >= 2) {

                String stockName =
                        data[0];

                int oldPrice =
                        Integer.parseInt(data[1]);

                int currentPrice =
                        oldPrice;

                int highestPrice =
                        oldPrice;

                if(data.length >= 3) {

                    currentPrice =
                            Integer.parseInt(data[2]);
                }

                if(data.length >= 4) {

                    highestPrice =
                            Integer.parseInt(data[3]);
                }

                String sector =
                        "General";

                if(data.length >= 5) {

                    sector =
                            data[4];
                }

                records[count] =
                        new Stock(stockName,
                                oldPrice,
                                currentPrice,
                                highestPrice,
                                sector);

                count++;
            }
        }

        br.close();

        StockRecord result[] =
                new StockRecord[count];

        for(int i = 0; i < count; i++) {

            result[i] = records[i];
        }

        return result;
    }

    public BTree loadStockTree() throws Exception {

        StockRecord records[] =
                loadStocks();

        BTree tree =
                new BTree();

        for(int i = 0; i < records.length; i++) {

            tree.insert(records[i]);
        }

        return tree;
    }

    public void saveStocks(BTree tree) throws Exception {

        FileWriter fw =
                new FileWriter(getStockFile());

        StockRecord records[] =
                tree.toArray();

        for(int i = 0; i < records.length; i++) {

            fw.write(records[i].stockName
                    + ","
                    + records[i].oldPrice
                    + ","
                    + records[i].currentPrice
                    + ","
                    + records[i].highestPrice
                    + ","
                    + records[i].sector);

            fw.write("\n");
        }

        fw.close();
    }
}
