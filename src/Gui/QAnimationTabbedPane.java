package Gui;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisSpace;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.VectorRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.statistics.HistogramBin;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.VectorSeries;
import org.jfree.data.xy.VectorSeriesCollection;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class QAnimationTabbedPane extends JTabbedPane {
	
	private XYBarDataset probData;
	private VectorSeriesCollection vecDataset;
	
	private JFreeChart pChart;
	private JFreeChart vecChart;
	
	private ChartPanel probPanel;
	
	public QAnimationTabbedPane(){
		super();
		addHist();
		addCoords();
		
	}
	
	protected void addHist(){
		
		//probData = new XYDataset(probData, 0.9);
		XYSeries series = new XYSeries("No Data");
        XYSeriesCollection defaultData = new XYSeriesCollection(series);
        probData = new XYBarDataset(defaultData, .95);
		pChart = ChartFactory.createXYBarChart("State histogram", "Probability before measurement", false, "State # (of 2^n states)", probData, PlotOrientation.VERTICAL
				,true, true, true);
		
		ChartPanel probPanel = new ChartPanel(pChart);
		this.addTab("Probability Hist", probPanel);
		
	}
	
	protected void addCoords(){
 
		VectorSeries greenSeries =new VectorSeries("Non-solution Vector");
		greenSeries.add(0, 0, 1, 0);
		
		VectorSeries blueSeries =new VectorSeries("Solution Vector");
		blueSeries.add(0, 0, 0, 1);
		
		VectorSeries redSeries = new VectorSeries("State Vector");
		//redSeries.add(0, 0, 1, 1);
	
		vecDataset = new VectorSeriesCollection();	
		vecDataset.addSeries(greenSeries);
		vecDataset.addSeries(blueSeries);
		vecDataset.addSeries(redSeries);
		

		VectorRenderer r = new VectorRenderer();
		//r.setBasePaint(Color.white);
		r.setSeriesPaint(0, Color.green.darker());
		r.setSeriesPaint(1, Color.blue.darker());
		r.setSeriesPaint(2, Color.red);
		XYPlot xyPlot = new XYPlot(vecDataset, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(-1.5, 1.5);
		NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(-1.5, 1.5);
        
		vecChart = new JFreeChart(xyPlot);
		ChartPanel vecPanel = new ChartPanel(vecChart);
		
		this.addTab("Vector graph", vecPanel);
		
	}
	
	public void updateHistogram(double[] regValues){
		int num_bins = regValues.length;
		XYSeries series = new XYSeries("States Data");
		for (int i = 0; i < regValues.length; i++) {
			series.add(i, regValues[i]);
		}
		XYSeriesCollection newDataCollection = new XYSeriesCollection(series);
		probData = new XYBarDataset(newDataCollection, .95);
		
		//Probably not the best way to do this, look into other ways
		pChart = ChartFactory.createXYBarChart("State Probabilites", "Probability before measurement", false
				, "State # (of 2^n states)", probData, PlotOrientation.VERTICAL, true, true, true);
		probPanel = new ChartPanel(pChart);
		this.setComponentAt(0, probPanel);
	}
	
	public void updateVector(double xval, double yval){
		VectorSeries greenSeries =new VectorSeries("Non-solution Vector");
		greenSeries.add(0, 0, 1, 0);
		
		VectorSeries blueSeries =new VectorSeries("Solution Vector");
		blueSeries.add(0, 0, 0, 1);
		
		VectorSeries redSeries = new VectorSeries("State Vector");
		redSeries.add(0, 0, xval, yval);
	
		vecDataset = new VectorSeriesCollection();	
		vecDataset.addSeries(greenSeries);
		vecDataset.addSeries(blueSeries);
		vecDataset.addSeries(redSeries);
		

		VectorRenderer r = new VectorRenderer();
		//r.setBasePaint(Color.white);
		r.setSeriesPaint(0, Color.green.darker());
		r.setSeriesPaint(1, Color.blue.darker());
		r.setSeriesPaint(2, Color.red);
		XYPlot xyPlot = new XYPlot(vecDataset, new NumberAxis("Axis X"), new NumberAxis("Axis Y"), r);
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        domain.setRange(-1.5, 1.5);
		NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
        range.setRange(-1.5, 1.5);
		vecChart = new JFreeChart(xyPlot);
		ChartPanel vecPanel = new ChartPanel(vecChart);
		
		this.setComponentAt(1, vecPanel);
		
	}

}
