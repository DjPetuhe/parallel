package Task1;

import java.util.HashMap;
import java.util.Map;

public class StatisticAnalyser {
    private double expectedValue;
    private double squaredExpectedValue;
    private double dispersion;
    private double meanSquareDeviation;
    public StatisticAnalyser() { }
    public StatisticAnalyser(HashMap<Integer, Integer> lengths) {
        AnalyseText(lengths);
    }

    public void AnalyseText(HashMap<Integer, Integer> lengths) {
        expectedValue = 0;
        squaredExpectedValue = 0;
        int allWordsAmount = 0;
        for (Map.Entry<Integer, Integer> entry : lengths.entrySet()) {
            int len = entry.getKey();
            int amount = entry.getValue();
            expectedValue += len * amount;
            squaredExpectedValue += Math.pow(len, 2) * amount;
            allWordsAmount += amount;
        }
        expectedValue /= allWordsAmount;
        squaredExpectedValue /= allWordsAmount;
        dispersion = squaredExpectedValue - Math.pow(expectedValue, 2);
        meanSquareDeviation = Math.sqrt(dispersion);
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    public double getSquaredExpectedValue() {
        return  squaredExpectedValue;
    }

    public  double getDispersion() {
        return dispersion;
    }

    public double getMeanSquareDeviation() {
        return meanSquareDeviation;
    }
}
