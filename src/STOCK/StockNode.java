package STOCK;

public class StockNode {

    String stockName;

    int stockPrice;

    StockNode left;

    StockNode right;

    public StockNode(String stockName,
                     int stockPrice) {

        this.stockName = stockName;

        this.stockPrice = stockPrice;

        left = null;

        right = null;
    }
}
