package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(double inAvgTemp, double inDevTemp,
            double inMinTemp, double inMaxTemp) {
        avgTemp = inAvgTemp;
        devTemp = inDevTemp;
        minTemp = inMinTemp;
        maxTemp = inMaxTemp;
    }
    public double getAvgTemp() {
        return avgTemp;
    }
    public double getDevTemp() {
        return devTemp;
    }
    public double getMinTemp() {
        return minTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }

}
