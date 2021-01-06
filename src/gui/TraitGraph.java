import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TraitGraph {
    List<Color> defaultLineColors;
    List<String> seriesNames;

    XYLineAndShapeRenderer renderer;
    private XYSeriesCollection dataset;
    private JFreeChart chart;
    private final int MAXDATA = 200;
    private int seriesIndex = 0;

    public TraitGraph(){
        seriesNames = new ArrayList<>();

        initDefaultLineColors();
        renderer = new XYLineAndShapeRenderer();
        createDataset();
        initChart();
    }

    private void initChart() {
        chart = ChartFactory.createXYLineChart(
                "Traits",
                "Generation",
                "Trait value",
                dataset
        );

        XYPlot plot = chart.getXYPlot();
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(false);

        chart.getLegend().visible = true;
        chart.setTitle(new TextTitle("Traits", new Font("Serif", java.awt.Font.BOLD, 18)));

    }

    private void createDataset() {
        dataset = new XYSeriesCollection();
    }

    public void addNewSeries(String newSeries){
        if (seriesNames.contains(newSeries)){
            System.out.println("Cannot register series with duplicate name");
            return;
        }else{
            seriesNames.add(newSeries);
        }

        Color lineColor = defaultLineColors.get(seriesIndex);
        dataset.addSeries(new XYSeries(newSeries));
        renderer.setSeriesPaint(seriesIndex, lineColor);
        renderer.setSeriesStroke(seriesIndex, new BasicStroke(2.0f));
        renderer.setSeriesShapesVisible(seriesIndex, false);
        seriesIndex++;

    }

    public void dataReceived(String seriesName, int time, double value){
        if(!seriesNames.contains(seriesName)){
            //System.out.println("Error - data recieved for a non existant series: " + seriesName);
            return;
        }

        dataset.getSeries(seriesName).add(time, value);
        if(time > MAXDATA){
            dataset.getSeries(seriesName).remove(0); //This means there will be a max of 400 data points showing concurrently
        }
    }


    public ChartPanel getChart() {
        //gridbaglayouts, when shrunk, try to turn things into their minimum size rather than resizing.
        //      We create a new dimension to define the preferred size to be smaller, so it will not resize perfectly, but won't shrink to nothing
        ChartPanel toReturn = new ChartPanel(chart);
        Dimension smallest = new Dimension((int)(toReturn.getPreferredSize().getWidth()*.75), (int)(toReturn.getPreferredSize().getHeight()*.75));
        toReturn.setPreferredSize(smallest);
        return toReturn;
    }

    public void reset() {
        dataset.removeAllSeries();
        for(String series : seriesNames) {
            dataset.addSeries(new XYSeries(series));
        }
    };

    private void initDefaultLineColors() {
        defaultLineColors = new ArrayList<Color>();
        defaultLineColors.add(Color.BLUE);
        defaultLineColors.add(Color.GREEN);
        defaultLineColors.add(Color.ORANGE);
        defaultLineColors.add(Color.CYAN);
        defaultLineColors.add(Color.RED);
    }
}
