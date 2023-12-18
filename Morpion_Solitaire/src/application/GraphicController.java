package application;

import algorithms.GaussianCurve;
import game.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GraphicController {
	
	@FXML
	private Stage stage;
	@FXML
	private AreaChart<Number, Number> chart;
	@FXML
	private Text mode;
	
	/*
	 * Initialize the relevant mode and plot the distribution curves associated with each search algorithm, if statistics are available.
	 */
	
	public void initialize () {
		mode.setText(Mode.toStringStatic());
		chart.getData().clear();
		if (!Double.isNaN(ResearchAlgorithmController.getScore0().get("Mean"))) {
			plotGaussianCurve(ResearchAlgorithmController.getScore0().get("Mean"), ResearchAlgorithmController.getScore0().get("Variance"),"Random Algorithm");
		}
		if (!Double.isNaN(ResearchAlgorithmController.getScore1().get("Mean"))) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore1().get("Mean"), ResearchAlgorithmController.getScore1().get("Variance"),"NMCS Level 1");
		}
		if (!Double.isNaN(ResearchAlgorithmController.getScore2().get("Mean"))) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore2().get("Mean"), ResearchAlgorithmController.getScore2().get("Variance"),"NMCS Level 2");
		}
		if (!Double.isNaN(ResearchAlgorithmController.getScore3().get("Mean"))) {
		    plotGaussianCurve(ResearchAlgorithmController.getScore3().get("Mean"), ResearchAlgorithmController.getScore3().get("Variance"),"NMCS Level 3");
		}
	}
	
	/**
	 * Draw the curve
	 * @param mean mean of the algorithm
	 * @param variance variance of the algorithm
	 * @param legendName legend associated with each algorithm
	 */
	
	public void plotGaussianCurve(double mean, double variance, String legendName) {
		if (!(variance == 0 || mean == 0)) {
	        GaussianCurve gaussianCurve = new GaussianCurve(mean, variance);
	        XYChart.Series<Number, Number> series = gaussianCurve.createGaussianSeries();
	        series.setName(legendName);
	        chart.getData().add(series);
	        
	        // Background of chart
	        chart.lookup(".chart-plot-background").setStyle("-fx-background-color: #283943;");
	        
	        // Label background color
	        Node legend = chart.lookup(".chart-legend");
	        if (legend != null) {
	            legend.setStyle("-fx-background-color: #283943;");
	        }
	        
	        // Label text color
	        String legendItemStyle = "-fx-text-fill: #EEE8AA;";
            for (Node item : legend.lookupAll(".chart-legend-item")) {
                item.setStyle(legendItemStyle);
            }
            
//         // Axis labels color
            chart.getXAxis().setStyle("-fx-text-fill: white;");
            chart.getYAxis().setStyle("-fx-text-fill: white;");

		}
    }
	
	/**
     * Changes the current scene to return to the main menu.
     * This method is triggered by an action event, typically a button click.
     *
     * @param event The event that triggered this method.
     */
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
}