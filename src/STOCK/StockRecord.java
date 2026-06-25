package STOCK;

public class StockRecord {

    public String stockName;

    public int oldPrice;

    public int currentPrice;

    public int highestPrice;

    public String sector;

    public StockRecord(String stockName,
                       int oldPrice,
                       int currentPrice,
                       int highestPrice) {

        this(stockName,
                oldPrice,
                currentPrice,
                highestPrice,
                "General");
    }

    public StockRecord(String stockName,
                       int oldPrice,
                       int currentPrice,
                       int highestPrice,
                       String sector) {

        this.stockName = stockName;

        this.oldPrice = oldPrice;

        this.currentPrice = currentPrice;

        this.highestPrice = highestPrice;

        this.sector = sector;
    }
}
