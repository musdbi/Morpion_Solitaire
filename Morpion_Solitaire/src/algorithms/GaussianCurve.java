package algorithms;

import javafx.scene.chart.XYChart;

public class GaussianCurve {

    private double mean;
    private double variance;
    private double stdDeviation;

    public GaussianCurve(double mean, double variance) {
        this.mean = mean;
        this.variance = variance;
        this.stdDeviation = Math.sqrt(variance);
    }

    public XYChart.Series<Number, Number> createGaussianSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        double startX = mean - 3 * stdDeviation;
        double endX = mean + 3 * stdDeviation;
        for (double x = startX; x <= endX; x += stdDeviation / 10) {
            double y = gaussianPDF(x);
            series.getData().add(new XYChart.Data<>(x, y));
        }
        return series;
    }

    private double gaussianPDF(double x) {
        return (1 / (stdDeviation * Math.sqrt(2 * Math.PI))) *
                Math.exp(-Math.pow(x - mean, 2) / (2 * variance));
    }
}

