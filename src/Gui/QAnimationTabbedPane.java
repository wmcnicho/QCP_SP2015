package Gui;

import java.awt.Color;

import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**An tabbed animation area used to display the probability histogram of the simulators states
 * and the vector rotation of Grover's Algorithm.
 *
 * @author William Hunter McNichols
 */
public class QAnimationTabbedPane extends JTabbedPane {
	
	private XYBarDataset probData;
	private VectorSeriesCollection vecDataset;	
	private JFreeChart pChart;
	private JFreeChart vecChart;
	private ChartPanel probPanel;
	private boolean is_grover;
	
	/**Basic setup of the tabbed area. Calls functions to set up the desired animation tabs */
	public QAnimationTabbedPane(){
		super();
		addHist();
		addCoords();
		is_grover = true;
	}
	
	/**Sets up the default histogram chart */
	protected void addHist(){
		XYSeries series = new XYSeries("No Data");
        XYSeriesCollection defaultData = new XYSeriesCollection(series);
        probData = new XYBarDataset(defaultData, .95);
		pChart = ChartFactory.createXYBarChart("State histogram", "State # (of 2^n states)", false, "Probability before measurement", probData, PlotOrientation.VERTICAL
				,true, true, true);
		
		ChartPanel probPanel = new ChartPanel(pChart);
		this.addTab("Probability Hist", probPanel);
		
	}
	/**Sets up the default vector rotation graph.*/
	protected void addCoords(){
 
		VectorSeries greenSeries =new VectorSeries("Non-solution Vector");
		greenSeries.add(0, 0, 1, 0);
		
		VectorSeries blueSeries =new VectorSeries("Solution Vector");
		blueSeries.add(0, 0, 0, 1);
		
		VectorSeries redSeries = new VectorSeries("State Vector");

		vecDataset = new VectorSeriesCollection();
		vecDataset.addSeries(redSeries);
		vecDataset.addSeries(greenSeries);
		vecDataset.addSeries(blueSeries);
		

		VectorRenderer r = new VectorRenderer();
		r.setSeriesPaint(0, Color.red);
		r.setSeriesPaint(1, Color.green.darker());
		r.setSeriesPaint(2, Color.blue.darker());
		XYPlot xyPlot = new XYPlot(vecDataset, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(-0.2, 1.5);
		NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(-0.2, 1.3);
        
		vecChart = new JFreeChart(xyPlot);
		ChartPanel vecPanel = new ChartPanel(vecChart);
		
		this.addTab("Vector graph", vecPanel);		
	}
	/**Changes the values of the histogram to reflect the input in regValues. Should be called by the QViewModel
	 * and not directly.
	 * N.B. This operation is slow because it re-renders the entire graph and should be called sparingly
	 * 
	 *  @param regValues 	The values of the registers indicating the probability of each state
	 */
	public void updateHistogram(double[] regValues){
		int num_bins = regValues.length;
		XYSeries series = new XYSeries("States Data");
		for (int i = 0; i < regValues.length; i++) {
			series.add(i, regValues[i]);
		}
		XYSeriesCollection newDataCollection = new XYSeriesCollection(series);
		probData = new XYBarDataset(newDataCollection, .95);
	
		pChart = ChartFactory.createXYBarChart("State Probabilites", "State # (of 2^n states)", false
				, "Probability before measurement", probData, PlotOrientation.VERTICAL, true, true, true);
		probPanel = new ChartPanel(pChart);
		this.setComponentAt(0, probPanel);
	}
	/**Changes the value of the red vector in the vector rotation animation. Should be called by the QViewModel and
	 * not directly.
	 * N.B. This operation is slow because it re-renders the entire graph and should be called sparingly
	 * 
	 *@param xval	The new x value of the red vector
	 *@param yval	The new y value of the red vector
	 */
	public void updateVector(double xval, double yval){
		VectorSeries greenSeries =new VectorSeries("Non-solution Vector");
		greenSeries.add(0, 0, 1, 0);
		
		VectorSeries blueSeries =new VectorSeries("Solution Vector");
		blueSeries.add(0, 0, 0, 1);
		
		VectorSeries redSeries = new VectorSeries("State Vector");
		redSeries.add(0, 0, xval, yval);
	
		vecDataset = new VectorSeriesCollection();
		vecDataset.addSeries(redSeries);
		vecDataset.addSeries(greenSeries);
		vecDataset.addSeries(blueSeries);
		

		VectorRenderer r = new VectorRenderer();
		r.setSeriesPaint(0, Color.red);
		r.setSeriesPaint(1, Color.green.darker());
		r.setSeriesPaint(2, Color.blue.darker());
		XYPlot xyPlot = new XYPlot(vecDataset, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(-0.2, 1.5);
		NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(-0.2, 1.3);
		vecChart = new JFreeChart(xyPlot);
		ChartPanel vecPanel = new ChartPanel(vecChart);
		
		this.setComponentAt(1, vecPanel);
		
	}
	/**Called by the QuantumGuiPanel to display/remove the vector rotation tab
	 * 
	 *@param b	A boolean indicating whether or not the vector rotation tab should be displayed
	 */
	public void showVector(boolean b) {
		if(b && !is_grover){
			addCoords();
			is_grover = true;
		}
		else if(!b && is_grover){
			this.remove(1);
			is_grover = false;
		}
	}

}
