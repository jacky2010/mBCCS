package com.androidadvance.androidsurvey.fragment;

import android.app.Service;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidadvance.androidsurvey.Answers;
import com.androidadvance.androidsurvey.R;
import com.androidadvance.androidsurvey.SurveyActivity;
import com.androidadvance.androidsurvey.models.Question;

public class FragmentMultiline extends Fragment {

    private FragmentActivity mContext;
    private Button button_continue;
    private Button button_exit;
    private TextView textview_q_title;
    private EditText editText_answer;
    private boolean isReadOnly = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_text_multiline, container, false);

        isReadOnly = getArguments().getBoolean("read_only", false);

        button_continue = (Button) rootView.findViewById(R.id.button_continue);
        button_exit = (Button) rootView.findViewById(R.id.button_exit);
        textview_q_title = (TextView) rootView.findViewById(R.id.textview_q_title);
        editText_answer = (EditText) rootView.findViewById(R.id.editText_answer);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                 Answers.getInstance().put_answer(textview_q_title.getText().toString(), editText_answer.getText().toString().trim());
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

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();
        Question q_data = (Question) getArguments().getSerializable("data");

        if (q_data.getRequired() && !isReadOnly) {
            button_continue.setEnabled(false);
            editText_answer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 3) {
                        button_continue.setEnabled(true);
                    } else {
                        button_continue.setEnabled(false);
                    }
                }
            });
        }else{
            button_continue.setEnabled(true);
        }

        textview_q_title.setText(Html.fromHtml(q_data.getQuestionTitle()));

        if(isReadOnly)
            editText_answer.setEnabled(false);
        else {
            editText_answer.setEnabled(true);
            editText_answer.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText_answer, 0);


    }
}