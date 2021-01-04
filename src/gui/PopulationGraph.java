import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class PopulationGraph {

    private final int MAXDATA = 200;
    private JFreeChart chart;
    private XYSeriesCollection dataset;

    PopulationGraph(){
        createDataset();
        initChart();
    }

    public ChartPanel getChart(){
        return new ChartPanel(chart);
    }

    private void initChart(){
        chart = ChartFactory.createXYLineChart(
                "Population",
                "Generation",
                "Number of Individuals",
                dataset
        );
        var renderer = new XYLineAndShapeRenderer();
        //The 0 in the series spot in these next 3 lines is because we only have 1 series, so it's in series position 0
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesShapesVisible(0, false);

        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setDomainGridlinesVisible(false); //Should make horizontal lines not show
        //plot.setDomainGridlinePaint(Color.gray);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Population Size Over Time",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

    }

    private XYDataset createDataset() {
        dataset = new XYSeriesCollection();
        var series = new XYSeries("Individuals");
        dataset.addSeries(series);
        return dataset;
    }

    public void dataReceived(int time, double value) {
        dataset.getSeries("Individuals").add(time, value);
        if(time > MAXDATA){
            dataset.getSeries("Individuals").remove(0); //This means there will be a max of 400 data points showing at a time
        }
    }

    public void reset() {
        dataset.removeAllSeries();
        var series = new XYSeries("Individuals");
        dataset.addSeries(series);
    };

}
