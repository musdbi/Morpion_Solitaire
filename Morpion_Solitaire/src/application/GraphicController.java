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
	@FXML
	private AreaChart<Number, Number> chart;
	
	public void initialize () {
		if (ResearchAlgorithmController.getScore0() != null) {
			plotGaussianCurve(ResearchAlgorithmController.getScore0().get("Mean"), ResearchAlgorithmController.getScore0().get("Variance"));
		}
		if (ResearchAlgorithmController.getScore1() != null) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore1().get("Mean"), ResearchAlgorithmController.getScore1().get("Variance"));
		}
		if (ResearchAlgorithmController.getScore2() != null) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore2().get("Mean"), ResearchAlgorithmController.getScore2().get("Variance"));
		}
		if (ResearchAlgorithmController.getScore3() != null) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore3().get("Mean"), ResearchAlgorithmController.getScore3().get("Variance"));
		}
	}
	
	public void plotGaussianCurve(double mean, double variance) {
        GaussianCurve gaussianCurve = new GaussianCurve(mean, variance);
        XYChart.Series<Number, Number> series = gaussianCurve.createGaussianSeries();
        chart.getData().add(series);
    }
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}

}
