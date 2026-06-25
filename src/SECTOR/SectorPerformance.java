package SECTOR;

public class SectorPerformance {

    public String sectorName;

    public int stockCount;

    public double averageGrowth;

    public SectorPerformance(String sectorName,
                             int stockCount,
                             double averageGrowth) {

        this.sectorName = sectorName;

        this.stockCount = stockCount;

        this.averageGrowth = averageGrowth;
    }
}
