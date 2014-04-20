package com.example.app;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.WindowManager;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpensePlotActivity extends ActionBarActivity {

    private List<UserAccount> _accountList;
    private UserAccount _account;

    private XYPlot plot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle v = getIntent().getExtras();


        // fun little snippet that prevents users from taking screenshots
        // on ICS+ devices :-)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_expense_plot);

        _accountList = (List<UserAccount>) ParseSingleton.getInstance().get("accountsList");
        _account = _accountList.get(v.getInt("accountID"));









        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {1000, 380, 555, 432, 237, 474};
        Number[] series2Numbers = {514, 536, 463, 778, 662, 810};
        Number[] series3Numbers = {126, 146, 83, 168, 142, 235};
        Number[] series4Numbers = {797, 836, 878, 846, 747, 704};


        ExpensePlotData epd = new ExpensePlotData();

       // Number[] months = {2,3,4,5,6,7};


        //String[] months = {"January","February","March","April","May"};

        ArrayList<Number> plotNums =  (ArrayList<Number>) epd.getTotalBalance(5, _account);
        XYSeries series1 = new SimpleXYSeries(
                         // SimpleXYSeries takes a List so turn our array into a List
                Arrays.asList(epd.getXVals(plotNums)),
                epd.getTotalBalance(5, _account), // can use Y_VALS_ONLY here to use the element index as the x value
                "Balance");                             // Set the display title of the series


        //XYSeries series2 = new SimpleXYSeries(Arrays.asList(months), Arrays.asList(series2Numbers), "Series2");

        //XYSeries series3 = new SimpleXYSeries(Arrays.asList(months), Arrays.asList(series3Numbers), "Series3");
        //XYSeries series4 = new SimpleXYSeries(Arrays.asList(months), Arrays.asList(series4Numbers), "Series4");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:




        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.argb(255, 255, 175, 80), Color.argb(255,255,175,80), null, null);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
        //LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.argb(255,235,120,120), Color.argb(255,235,120,120), null, null);

        //plot.addSeries(series2, series2Format);

        //LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.argb(255,125,180,255), Color.argb(255,125,180,255), null, null);

        //plot.addSeries(series3, series3Format);

        //LineAndPointFormatter series4Format = new LineAndPointFormatter(Color.argb(255,240,255,110), Color.argb(255,240,255,110), null, null);

       // plot.addSeries(series4, series4Format);


        // draw a domain tick for each year:
        //plot.setDomainStep(XYStepMode.SUBDIVIDE, epd.getXVals(epd.getTotalBalance(5, _account)).length);


        double step = plotNums.get(plotNums.size()-1).doubleValue();
        step = ((int) Math.log10(step));
        step = Math.pow(10, step);








        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, step);
        plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 1);
        // customize our domain/range labels
        plot.setDomainLabel("Transaction");
        plot.setRangeLabel("Amount Spent ($)");

        plot.setRangeValueFormat(new DecimalFormat("#.00"));
        plot.setDomainValueFormat(new DecimalFormat("0"));


        // reduce the number of range labels
        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

        //epd = new ExpensePlotData();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_plot, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_expense_plot, container, false);
            return rootView;
        }
    }

}
