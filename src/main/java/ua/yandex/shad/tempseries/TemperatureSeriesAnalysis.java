package ua.yandex.shad.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    public static final double MIN_TEMPERATURE = -273.0;
    private static final double EPSILON = 0.00001;
    private double[] temperatureSeries;
    private int length;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] tempSeries) {
        this.length = tempSeries.length;
        this.temperatureSeries = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            this.temperatureSeries[i] = tempSeries[i];
        }
    }

    public double[] getTemps() {
        double[] result = new double[temperatureSeries.length];
        for (int i = 0; i < this.length; i++) {
            result[i] = temperatureSeries[i];
        }
        return result;
    }

    public double average() {
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        else {
            double ans = 0;
            for (double val : temperatureSeries) {
                ans += val;
            }
            ans /= this.length;
            return ans;
        }
    }

    public double deviation() {
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        double ans = 0;
        double av = this.average();
        double sumSq = 0;
        for (double val : temperatureSeries) {
            double valSq = 0;
            valSq = (val - av) * (val - av);
            sumSq += valSq;
        }
        ans = sumSq / length;
        ans = Math.sqrt(ans);
        return ans;
    }

    public double min() {
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = temperatureSeries[0];
        for (double val : temperatureSeries) {
            if (val < min) {
                min = val;
            }
            else {
                continue;
            }
        }
        return min;
    }

    public double max() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double max = temperatureSeries[0];
        for (double val : temperatureSeries) {
            if (val > max) {
                max = val;
            }
            else {
                continue;
            }
        }
        return max;
    }

    public double findTempClosestToZero() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double closestToZero = temperatureSeries[0];
        for ( double val : temperatureSeries) {
            if(Math.abs(val) < Math.abs(closestToZero)) {
                closestToZero = val;
            }
            else {
                if ((Math.abs(val - Math.abs(closestToZero)) < EPSILON) && (val >= 0)) {
                    closestToZero = val;
                }
                else {
                    continue;
                }
            }
        }
        return closestToZero;
    }

    public double findTempClosestToValue(double tempValue) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double closestToVal = temperatureSeries[0];
        for (double val : temperatureSeries) {
            if (Math.abs(val - tempValue) < Math.abs(closestToVal - tempValue)) {
                closestToVal = val;
            }
            else {
                if ((Math.abs((double) (val - tempValue) -
                    Math.abs(closestToVal - tempValue)) < EPSILON) &&
                    ((val - tempValue) >= 0)) {
                        closestToVal = val;
                }
                else {
                    continue;
                }
            }
        }
        return closestToVal;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int counterLess = 0;
        for (double val : temperatureSeries) {
            if (val < tempValue) {
                counterLess++;
            }
        }
        if (counterLess == 0) {
            return null;
        }
        int i = 0;
        double[] tempsLessThen = new double[counterLess];
        for (double val : temperatureSeries) {
            if (val < tempValue) {
                tempsLessThen[i] = val;
                i++;
            }
            else {
                continue;
            }
        }
        return tempsLessThen;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int counterGreater = 0;
        for (double val : temperatureSeries) {
            if (val >= tempValue) {
                counterGreater++;
            }
            else {
                continue;
            }
        }
        if (counterGreater == 0) {
            return null;
        }
        int i = 0;
        double[] tempsGreaterThenValue = new double[counterGreater];
        for (double val : temperatureSeries) {
            if (val >= tempValue) {
                tempsGreaterThenValue[i] = val;
                i++;
            }
            else {
                continue;
            }
        }
        return tempsGreaterThenValue;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double ... temps) {
        for (int i = 0; i < temps.length; i++) {
            if (temps[i] < MIN_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }
        int expectedLength = temperatureSeries.length;
        if (expectedLength == 0) {
            expectedLength = temps.length;
        }
        while (temps.length + this.length > expectedLength) {
            expectedLength *= 2;
        }
        double[] result = new double [expectedLength];
        for (int i = 0; i < this.length; i++) {
            result[i] = temperatureSeries[i];
        }
        for (int i = this.length; i < length + temps.length; i++) {
            result[i] = temps[i-length];
        }
        this.length += temps.length;
        this.temperatureSeries = result;
        return length;
    }
}
