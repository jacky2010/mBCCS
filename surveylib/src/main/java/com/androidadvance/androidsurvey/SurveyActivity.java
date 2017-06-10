package com.androidadvance.androidsurvey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidadvance.androidsurvey.adapters.AdapterFragmentQ;
import com.androidadvance.androidsurvey.fragment.FragmentCheckboxes;
import com.androidadvance.androidsurvey.fragment.FragmentEnd;
import com.androidadvance.androidsurvey.fragment.FragmentMultiline;
import com.androidadvance.androidsurvey.fragment.FragmentNumber;
import com.androidadvance.androidsurvey.fragment.FragmentRadioboxes;
import com.androidadvance.androidsurvey.fragment.FragmentStart;
import com.androidadvance.androidsurvey.fragment.FragmentTextSimple;
import com.androidadvance.androidsurvey.models.Question;
import com.androidadvance.androidsurvey.models.SurveyPojo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SurveyActivity extends AppCompatActivity {

    public static final String DATA_SURVEY_JSON = "json_survey";
    public static final String DATA_IS_READ_ONLY = "is_read_only";
    public static final String DATA_SURVEY_STYLE = "style";
    public static final String DATA_ANSWERS = "answers";

    private Activity activity = this;
    private SurveyPojo mSurveyPojo;
    private ViewPager mPager;
    private String style_string = null;
    private TextView mTitleTextView;
    private boolean isReadOnly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            mSurveyPojo = new Gson().fromJson(bundle.getString(DATA_SURVEY_JSON), SurveyPojo.class);
            if (bundle.containsKey(DATA_SURVEY_STYLE)) {
                style_string = bundle.getString(DATA_SURVEY_STYLE);
            }

            if (bundle.containsKey(DATA_IS_READ_ONLY))
                isReadOnly = bundle.getBoolean(DATA_IS_READ_ONLY);
        }

        mTitleTextView = (TextView) findViewById(R.id.txtTitle);
        mTitleTextView.setText(mSurveyPojo.getSurveyProperties().getTitle());

        ImageView btnBack = (ImageView) findViewById(R.id.image_left);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        Log.i("json Object = ", String.valueOf(mSurveyPojo.getQuestions()));

        final ArrayList<Fragment> arraylist_fragments = new ArrayList<>();

        //- START -
        if (!mSurveyPojo.getSurveyProperties().getSkipIntro()) {
            FragmentStart frag_start = new FragmentStart();
            Bundle sBundle = new Bundle();
            sBundle.putSerializable("survery_properties", mSurveyPojo.getSurveyProperties());
            sBundle.putString("style", style_string);
            sBundle.putBoolean("read_only", isReadOnly);
            frag_start.setArguments(sBundle);
            arraylist_fragments.add(frag_start);
        }

        //- FILL -
        for (Question mQuestion : mSurveyPojo.getQuestions()) {

            if (mQuestion.getQuestionType().equals("String")) {
                FragmentTextSimple frag = new FragmentTextSimple();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putBoolean("read_only", isReadOnly);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Checkboxes")) {
                FragmentCheckboxes frag = new FragmentCheckboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putBoolean("read_only", isReadOnly);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Radioboxes")) {
                FragmentRadioboxes frag = new FragmentRadioboxes();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putBoolean("read_only", isReadOnly);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("Number")) {
                FragmentNumber frag = new FragmentNumber();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putBoolean("read_only", isReadOnly);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

            if (mQuestion.getQuestionType().equals("StringMultiline")) {
                FragmentMultiline frag = new FragmentMultiline();
                Bundle xBundle = new Bundle();
                xBundle.putSerializable("data", mQuestion);
                xBundle.putString("style", style_string);
                xBundle.putBoolean("read_only", isReadOnly);
                frag.setArguments(xBundle);
                arraylist_fragments.add(frag);
            }

        }

        //- END -
        FragmentEnd frag_end = new FragmentEnd();
        Bundle eBundle = new Bundle();
        eBundle.putSerializable("survery_properties", mSurveyPojo.getSurveyProperties());
        eBundle.putString("style", style_string);
        eBundle.putBoolean("read_only", isReadOnly);
        frag_end.setArguments(eBundle);
        arraylist_fragments.add(frag_end);


        mPager = (ViewPager) findViewById(R.id.pager);
        AdapterFragmentQ mPagerAdapter = new AdapterFragmentQ(getSupportFragmentManager(), arraylist_fragments);
        mPager.setAdapter(mPagerAdapter);


    }

    public void go_to_next() {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
        refreshPageTitle(mPager.getCurrentItem());
    }

    private void refreshPageTitle(int currentPage) {
        try {
            if (mTitleTextView != null) {

                int totalPage = mPager.getAdapter().getCount() - 2;

                if (currentPage > totalPage || currentPage == 0)
                    mTitleTextView.setText(mSurveyPojo.getSurveyProperties().getTitle());
                else
                    mTitleTextView.setText(getString(R.string.question_item_per_list, String.valueOf(currentPage), String.valueOf(totalPage)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);

            refreshPageTitle(mPager.getCurrentItem());
        }
    }

    public void event_survey_completed(Answers instance) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DATA_ANSWERS, instance.get_json_object());
        returnIntent.putExtra(DATA_IS_READ_ONLY, isReadOnly);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void event_survey_exit() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(DATA_IS_READ_ONLY, isReadOnly);
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}
