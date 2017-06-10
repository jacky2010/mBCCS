package com.androidadvance.androidsurvey.formatters;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

/**
 * Created by minhnx on 6/10/17.
 */

public class XAxisValueFormatter implements IAxisValueFormatter {

    private BarLineChartBase<?> chart;
    private List<String> xAxisValues;

    public XAxisValueFormatter(BarLineChartBase<?> chart, List<String> xAxisValues) {
        this.chart = chart;
        this.xAxisValues = xAxisValues;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try{
            return xAxisValues.get((int)value);
        }catch (IndexOutOfBoundsException ie){
            return "";
        }
    }
}
