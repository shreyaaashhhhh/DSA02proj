package STOCK;

public class Stock extends StockRecord {

    public Stock(String stockName,
                 int oldPrice,
                 int currentPrice,
                 int highestPrice,
                 String sector) {

        super(stockName,
                oldPrice,
                currentPrice,
                highestPrice,
                sector);
    }

    public Stock(StockRecord record) {

        super(record.stockName,
                record.oldPrice,
                record.currentPrice,
                record.highestPrice,
                record.sector);
    }
}
