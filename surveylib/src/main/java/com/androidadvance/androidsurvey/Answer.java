package com.androidadvance.androidsurvey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by minhnx on 6/10/17.
 */

public class Answer {
    @Expose
    @SerializedName("answer")
    private String answer;
    @Expose
    @SerializedName("answer_percent")
    private int answerPercent;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getAnswerPercent() {
        return answerPercent;
    }

    public void setAnswerPercent(int answerPercent) {
        this.answerPercent = answerPercent;
    }

    public Answer(String answer, int answerPercent) {
        this.answer = answer;
        this.answerPercent = answerPercent;
    }

    public Answer() {
    }
}
