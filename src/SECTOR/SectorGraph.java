package SECTOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import STOCK.Stock;
import STOCK.StockRecord;
import STOCK.StockRepository;

public class SectorGraph {

    private HashMap<String, List<Stock>> sectorGraph =
            new HashMap<String, List<Stock>>();

    // Graph adjacency list: Sector -> all stocks that belong to that sector.
    public void buildGraph() throws Exception {

        StockRepository repository =
                new StockRepository();

        StockRecord records[] =
                repository.loadStocks();

        sectorGraph.clear();

        for(int i = 0; i < records.length; i++) {

            Stock stock =
                    new Stock(records[i]);

            String sector =
                    stock.sector;

            if(sector == null || sector.trim().length() == 0) {

                sector =
                        "General";
            }

            if(!sectorGraph.containsKey(sector)) {

                sectorGraph.put(sector,
                        new ArrayList<Stock>());
            }

            sectorGraph.get(sector).add(stock);
        }
    }

    public HashMap<String, List<Stock>> getGraph() {

        return sectorGraph;
    }

    public String[] getSectors() {

        String sectors[] =
                new String[sectorGraph.size()];

        int index =
                0;

        for(String sector : sectorGraph.keySet()) {

            sectors[index] =
                    sector;

            index++;
        }

        return sectors;
    }

    public List<Stock> getStocksBySector(String sector) {

        for(String key : sectorGraph.keySet()) {

            if(key.equalsIgnoreCase(sector)) {

                return sectorGraph.get(key);
            }
        }

        return new ArrayList<Stock>();
    }
}
