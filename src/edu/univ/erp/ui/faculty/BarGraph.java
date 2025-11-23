
package project;

import org.knowm.xchart.*;
import java.util.Arrays;
import java.awt.BorderLayout;
import java.awt.Color;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.*;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;



public class BarGraph extends JPanel {

    public BarGraph(int width, int height, List<String> x_axis, List<String> y_axis, String x_axis_title, String y_axis_title, String mainTitle) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);   
    
        // List<String> courseCodes = Arrays.asList("CS101", "CS102", "CS103", "CS104", "CS105");
        // List<Integer> assessments = Arrays.asList(5, 3, 6, 4, 2);

        CategoryChart chart = new CategoryChartBuilder()
                .width(width)
                .height(height)
                .title(mainTitle)
                .xAxisTitle(x_axis_title)
                .yAxisTitle(y_axis_title)
                .build();

        
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotBorderVisible(true);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);

        chart.addSeries("Assessments", x_axis, y_axis)
                .setFillColor(new Color(255, 165, 0)); // orange bars

        XChartPanel<CategoryChart> chartPanel = new XChartPanel<>(chart);
        chartPanel.setBackground(Color.WHITE);

        add(chartPanel, BorderLayout.CENTER);
    }

    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("Chart Preview");
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //     // create chart object for testing
    //     course_code chart = new course_code(900, 500);

    //     frame.add(chart);
    //     frame.pack();
    //     frame.setSize(1000, 600);
    //     frame.setVisible(true);
    // }
}
