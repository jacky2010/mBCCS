package com.androidadvance.androidsurvey.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.androidadvance.androidsurvey.Answer;
import com.androidadvance.androidsurvey.Answers;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.formatters.AxisValueFormatter;
import com.androidadvance.androidsurvey.formatters.XAxisValueFormatter;
import com.androidadvance.androidsurvey.models.Question;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentRadioboxes extends Fragment {

    private Question q_data;
    private FragmentActivity mContext;
    private Button button_continue;
    private Button button_exit;
    private TextView textview_q_title;
    private RadioGroup radioGroup;
    private BarChart bc_answers;
    private final ArrayList<RadioButton> allRb = new ArrayList<>();
    private boolean at_leaset_one_checked = false;
    private boolean isReadOnly = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_radioboxes, container, false);

        isReadOnly = getArguments().getBoolean("read_only", false);

        button_continue = (Button) rootView.findViewById(R.id.button_continue);
        button_exit = (Button) rootView.findViewById(R.id.button_exit);
        textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        bc_answers = (BarChart) rootView.findViewById(R.id.bc_answers);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SurveyActivity) mContext).go_to_next();
            }
        });

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SurveyActivity) mContext).event_survey_exit();
            }
        });

        return rootView;
    }

    private void collect_data() {

        //----- collection & validation for is_required
        String the_choice = "";
        at_leaset_one_checked = false;
        for (RadioButton rb : allRb) {
            if (rb.isChecked()) {
                at_leaset_one_checked = true;
                the_choice = rb.getText().toString();
            }
        }

        if (the_choice.length() > 0) {
            Answers.getInstance().put_answer(textview_q_title.getText().toString(), the_choice);
        }


        if (q_data.getRequired()) {
            if (at_leaset_one_checked || isReadOnly) {
                button_continue.setEnabled(true);
            } else {
                button_continue.setEnabled(false);
            }
        }

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mContext = getActivity();
        q_data = (Question) getArguments().getSerializable("data");

        textview_q_title.setText(q_data.getQuestionTitle());


        List<String> qq_data = q_data.getChoices();
        if (q_data.getRandomChoices()) {
            Collections.shuffle(qq_data);
        }

        for (String choice : qq_data) {
            RadioButton rb = new RadioButton(mContext);
            rb.setText(Html.fromHtml(choice));
            rb.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            rb.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(rb);
            allRb.add(rb);

            rb.setEnabled(!isReadOnly);

            rb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    collect_data();
                }
            });
        }

        if (q_data.getRequired()) {
            if (at_leaset_one_checked || isReadOnly) {
                button_continue.setEnabled(true);

                List<BarEntry> xAxisEntries = new ArrayList<>();
                List<String> xAxisValues = new ArrayList<>();
                BarEntry entry;

                for (int i = 0; i < q_data.getAnswers().size(); i++) {

                    Answer answer = q_data.getAnswers().get(i);

                    xAxisValues.add(answer.getAnswer());

                    entry = new BarEntry(i, answer.getAnswerPercent());
                    xAxisEntries.add(entry);
                }

                setChartData(xAxisEntries, xAxisValues);
            } else {
                button_continue.setEnabled(false);
            }
        }


    }

    private void setChartData(List<BarEntry> xAxisEntries, List<String> xAxisValues) {
        BarDataSet dataSet = new BarDataSet(xAxisEntries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(dataSet);

        BarData data = new BarData(dataSets);
        data.setValueTextSize(10f);
        data.setBarWidth(0.9f);

        IAxisValueFormatter xAxisFormatter = new XAxisValueFormatter(bc_answers, xAxisValues);

        XAxis xAxis = bc_answers.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter yAxisFormatter = new AxisValueFormatter();

        YAxis leftAxis = bc_answers.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(yAxisFormatter);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = bc_answers.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(8, false);
        rightAxis.setValueFormatter(yAxisFormatter);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        bc_answers.setData(data);
        bc_answers.invalidate();
        bc_answers.getDescription().setEnabled(false);
        bc_answers.getLegend().setEnabled(false);
        bc_answers.setDrawBarShadow(false);
        bc_answers.setDrawValueAboveBar(true);
        bc_answers.setPinchZoom(false);
        bc_answers.setDrawGridBackground(false);
    }
}