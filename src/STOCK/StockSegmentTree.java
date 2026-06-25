package STOCK;

public class StockSegmentTree {

    private StockRecord stocks[];

    private int minTree[];

    private int maxTree[];

    private int count;

    public StockSegmentTree(StockRecord stockRecords[]) {

        stocks = stockRecords;

        count = stockRecords.length;

        sortByCurrentPrice();

        if(count > 0) {

            minTree =
                    new int[count * 4];

            maxTree =
                    new int[count * 4];

            build(1,
                    0,
                    count - 1);
        }
    }

    public StockRecord[] searchByPriceRange(int lowPrice,
                                            int highPrice) {

        StockRecord result[] =
                new StockRecord[count];

        int resultCount[] =
                new int[1];

        if(count > 0) {

            query(1,
                    0,
                    count - 1,
                    lowPrice,
                    highPrice,
                    result,
                    resultCount);
        }

        StockRecord finalResult[] =
                new StockRecord[resultCount[0]];

        for(int i = 0; i < resultCount[0]; i++) {

            finalResult[i] = result[i];
        }

        return finalResult;
    }

    private void build(int node,
                       int left,
                       int right) {

        if(left == right) {

            minTree[node] =
                    stocks[left].currentPrice;

            maxTree[node] =
                    stocks[left].currentPrice;

            return;
        }

        int mid =
                (left + right) / 2;

        build(node * 2,
                left,
                mid);

        build((node * 2) + 1,
                mid + 1,
                right);

        minTree[node] =
                Math.min(minTree[node * 2],
                        minTree[(node * 2) + 1]);

        maxTree[node] =
                Math.max(maxTree[node * 2],
                        maxTree[(node * 2) + 1]);
    }

    private void query(int node,
                       int left,
                       int right,
                       int lowPrice,
                       int highPrice,
                       StockRecord result[],
                       int resultCount[]) {

        if(maxTree[node] < lowPrice
                || minTree[node] > highPrice) {

            return;
        }

        if(left == right) {

            result[resultCount[0]] =
                    stocks[left];

            resultCount[0]++;

            return;
        }

        int mid =
                (left + right) / 2;

        query(node * 2,
                left,
                mid,
                lowPrice,
                highPrice,
                result,
                resultCount);

        query((node * 2) + 1,
                mid + 1,
                right,
                lowPrice,
                highPrice,
                result,
                resultCount);
    }

    private void sortByCurrentPrice() {

        for(int i = 0; i < count - 1; i++) {

            for(int j = 0; j < count - i - 1; j++) {

                if(stocks[j].currentPrice
                        > stocks[j + 1].currentPrice) {

                    StockRecord temp =
                            stocks[j];

                    stocks[j] =
                            stocks[j + 1];

                    stocks[j + 1] =
                            temp;
                }
            }
        }
    }
}
