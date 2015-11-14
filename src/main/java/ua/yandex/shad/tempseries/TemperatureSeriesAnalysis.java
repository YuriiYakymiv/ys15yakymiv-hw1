package ua.yandex.shad.tempseries;
import java.util.InputMismatchException;
import static  java.lang.Math.*;

public class TemperatureSeriesAnalysis {

    private double[] temperatureSeries;
    private static final double EPSILON = 0.00001;
    private int length;
    public static final double MIN_TEMPERATURE = -273.0;

    public double[] getTemps() {
        double[] result = new double[temperatureSeries.length];
        for (int i = 0; i < this.length; i++) {
            result[i] = temperatureSeries[i];
        }
        return result;
    }

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[0];
        this.length = 0;
    }

    public TemperatureSeriesAnalysis(double[] tempSeries) {
        this.length = tempSeries.length;
        this.temperatureSeries = new double[this.length];
        for (int i = 0; i < this.length; i++) {
            this.temperatureSeries[i] = tempSeries[i];
        }
    }

    public double average(){
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        else {
            double ans = 0;
            for (double val : temperatureSeries) {
                ans+=val;
            }
            ans /= this.length;
            return ans;
        }
    }

    public double deviation(){
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        double ans = 0;
        double av = this.average();
        double sumSq = 0;
        for ( double val : temperatureSeries) {
            double valSq = 0;
            valSq = (val-av)*(val-av);
            sumSq+=valSq;
        }
        ans = sumSq/length;
        ans = sqrt(ans);
        return ans;
    }

    public double min(){
        if (this.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = temperatureSeries[0];
        for ( double val : temperatureSeries) {
            if (val < min) {
                min = val;
            }
            else {
                continue;
            }
        }
        return min;
    }

    public double max(){
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double max = temperatureSeries[0];
        for ( double val : temperatureSeries) {
            if (val > max) {
                max = val;
            }
            else {
                continue;
            }
        }
        return max;
    }

    public double findTempClosestToZero(){
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double ans=temperatureSeries[0];
        for ( double val : temperatureSeries) {
            if(Math.abs(val) < Math.abs(ans)) {
                ans = val;
            }
            else {
                if ((Math.abs(val - Math.abs(ans)) < EPSILON) && (val >= 0)) {
                    ans = val;
                }
                else {
                    continue;
                }
            }
        }
        return ans;
    }

    public double findTempClosestToValue(double tempValue){
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double ans = temperatureSeries[0];
        for (double val : temperatureSeries) {
            if (Math.abs(val - tempValue) < Math.abs(ans - tempValue)) {
                ans = val;
            }
            else {
                if ((Math.abs((double) (val - tempValue) - Math.abs(ans - tempValue)) < EPSILON) && ((val - tempValue) >= 0)) {
                        ans = val;
                }
                else {
                    continue;
                }
            }
        }
        return ans;
}

    public double[] findTempsLessThen(double tempValue){
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (double val : temperatureSeries) {
            if (val < tempValue) {
                counter++;
            }
        }
        if (counter == 0) {
            return null;
        }
        double[] ans = new double[counter];
        int i = 0;
        for (double val : temperatureSeries) {
            if (val < tempValue) {
                ans[i] = val;
                i++;
            }
            else {
                continue;
            }
        }
        return ans;
    }

    public double[] findTempsGreaterThen(double tempValue){
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (double val : temperatureSeries) {
            if (val >= tempValue) {
                counter++;
            }
            else {
                continue;
            }
        }
        if ( counter == 0 ) {
            return null;
        }
        double[] ans = new double[counter];
        int i = 0;
        for (double val : temperatureSeries) {
            if (val >= tempValue) {
                ans[i] = val;
                i++;
            }
            else {
                continue;
            }
        }
        return ans;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double ... temps){
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
