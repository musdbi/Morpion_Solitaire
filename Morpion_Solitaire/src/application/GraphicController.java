package application;

import algorithms.GaussianCurve;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class GraphicController {
	
	@FXML
	private Stage stage;
	
	private static Map<String, Double> score0;
	private static Map<String, Double> score1;
	private static Map<String, Double> score2;
	private static Map<String, Double> score3;
	@FXML
	private AreaChart<Number, Number> chart;
	
	public void initialize () {
		if (score0 != null) {
			plotGaussianCurve(score0.get("Mean"), score0.get("Variance"));
		}
		if (score1 != null) {
		    plotGaussianCurve(score1.get("Mean"), score1.get("Variance"));
		}
		if (score2 != null) {
		    plotGaussianCurve(score2.get("Mean"), score2.get("Variance"));
		}
//		if (score3 != null) {
//		    plotGaussianCurve(score3.get("Mean"), score3.get("Variance"));
//		}
	}
	
	public void plotGaussianCurve(double mean, double variance) {
        GaussianCurve gaussianCurve = new GaussianCurve(mean, variance);
        XYChart.Series<Number, Number> series = gaussianCurve.createGaussianSeries();
        chart.getData().add(series);
    }
	
	public static void setScore0(Map<String, Double> score) {
		score0 = score;
	}
	
	public static void setScore1(Map<String, Double> score) {
		score1 = score;
	}
	
	public static void setScore2(Map<String, Double> score) {
		score2 = score;
	}
	
	public static void setScore3(Map<String, Double> score) {
		score3 = score;
	}
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}

}
