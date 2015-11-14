package ua.yandex.shad.tempseries;
import java.util.InputMismatchException;
import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverage() {
        double[] temperatureSeries = {1.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.5;
        double actualResult = seriesAnalysis.average();

        assertEquals(expResult, actualResult, 0.00001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverage_EmptyList(){
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviation_EmptyList(){
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, 3.0, 9.0, 9.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;
        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMin_EmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.min();
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, 3.0, 9.0, 9.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;
        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMax_EmptyList() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.max();
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, 3.0, 9.0, 9.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 9.0;
        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZero_EmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.1;
        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithTheSameValue() {
        double[] temperatureSeries = {4.3, 0.1, -0.1, 11.7, -3.92, 0.1, -1.9, 8};
        TemperatureSeriesAnalysis seriesAnalysis =
        new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.1;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero_WithTheSameAbsValue() {
        double[] temperatureSeries = {7.3, -0.2, 12.0, -1.56, 0.2, -2.8, 4};
        TemperatureSeriesAnalysis seriesAnalysis =
        new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.2;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithZero_Element() {
        double[] temperatureSeries = {2.1, 5.5, -3.6, 0.0, 2, 13.521};
        TemperatureSeriesAnalysis seriesAnalysis =
        new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals (expResult, actualResult, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValue_EmptyList() {
        double[] temperatureSeries = {};
        double tempValue = 0.0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempClosestToValue(tempValue);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        double value = 2.65;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(value);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThen_Empty () {
        double tempValue = 0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempsLessThen(tempValue);
    }

    @Test
    public void testFindTempLessThen_returnsNull() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        double tempValue = -52.0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsLessThen(tempValue);

        assertNull(actualResult);
    }

    @Test
    public void testFindTempLessThan() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        double tempValue = 0.1;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-0.1, -4.0};
        double[] actualResult = seriesAnalysis.findTempsLessThen(tempValue);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThen_Empty () {
        double[] temperatureSeries = {};
        double tempValue = 0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.findTempsGreaterThen(tempValue);
    }

    @Test
    public void testFindTempGreaterThen_returnsNull() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        double tempValue = 52.0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(tempValue);

        assertNull(actualResult);
    }

    @Test
    public void testFindTempGreaterThan() {
        double[] temperatureSeries = {0.1, -0.1, 2.0, 3.0, -4.0};
        double tempValue = 2.0;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {2.0, 3.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(tempValue);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testSummaryStatistics_testValues() {
    	double[] temperatureSeries = {1.0, -10.0, -1.0, 5.0, 60};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	TempSummaryStatistics statistics = seriesAnalysis.summaryStatistics();
    	double expDev = Math.sqrt(624.4);
    	double[] expResult = {11.0, expDev, -10.0, 60.0};
    	double actualAvg = statistics.getAvgTemp();
    	double actualDev = statistics.getDevTemp();
    	double actualMin = statistics.getMinTemp();
    	double actualMax = statistics.getMaxTemp();
    	double[] actualResult = {actualAvg, actualDev, actualMin, actualMax};
    	assertArrayEquals(expResult, actualResult, 0.0001);

    }

    @Test(expected=InputMismatchException.class)
    public void testAddTemps_testOnLessThanAbsoluteMin() {
    	double[] temperatureSeries = {};
    	double[] newTempSeries = {4.2,5.0, -273.0, -274.0};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	seriesAnalysis.addTemps(newTempSeries);
    }
    @Test
    public void testAddTemps_needToResize(){
    	double[] temperatureSeries = {2.0,6.0,8.0,0.0};
    	double[] newTempSeries = {4.2, 5.0,-50.0};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	seriesAnalysis.addTemps(newTempSeries);
    	double[] actualResult = seriesAnalysis.getTemps();
    	double[] expResult = {2.0,6.0,8.0,0.0,4.2,5.0,-50.0,0.0};
    	assertArrayEquals(expResult,actualResult,0.00001);
    }
    @Test
    public void testAddTemps_multipleAdding(){
    	double[] temperatureSeries = {2.0};
    	double[] newTempSeries = {4.0, 5.0,-50.0,4.0};
    	double[] extraTempSeries = {6.0};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	seriesAnalysis.addTemps(newTempSeries);
    	seriesAnalysis.addTemps(extraTempSeries);
    	double[] actualResult = seriesAnalysis.getTemps();
    	double[] expResult = {2.0,4.0, 5.0,-50.0,4.0,6.0,0.0,0.0};
    	assertArrayEquals(expResult,actualResult,0.00001);

    }

    @Test
    public void testAddTemps_addToEmpty(){
    	double[] temperatureSeries = {};
    	double[] newTempSeries = {4.2, 5.0,-50.0};
    	double[] extraTempSeries = {6.0,9.0};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	seriesAnalysis.addTemps(newTempSeries);
    	seriesAnalysis.addTemps(extraTempSeries);
    	double[] actualResult = seriesAnalysis.getTemps();
    	double[] expResult = {4.2,5.0,-50.0,6.0,9.0,0.0};
    	assertArrayEquals(expResult,actualResult,0.00001);
    }

    @Test
    public void testAddTemps_checkArrayLen(){
    	double[] temperatureSeries = {};
    	double[] newTempSeries = {4.2, 5.0,-50.0};
    	double[] extraTempSeries = {6.0};
    	TemperatureSeriesAnalysis seriesAnalysis =
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	seriesAnalysis.addTemps(newTempSeries);
    	int actualResult = seriesAnalysis.addTemps(extraTempSeries);
    	int expResult = 4;
    	assertEquals(expResult,actualResult);
    }
}
