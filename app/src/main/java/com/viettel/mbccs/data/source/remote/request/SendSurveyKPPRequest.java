package com.viettel.mbccs.data.source.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by minhnx on 6/7/17.
 */

public class SendSurveyKPPRequest {
    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("surveyResponse")
    @Expose
    private SurveyResponse surveyResponse;

    public SurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(SurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static class SurveyResponse {

        @SerializedName("surveyId")
        @Expose
        private int surveyId;
        @SerializedName("surveyResponseAnswer")
        @Expose
        private List<SurveyResponseAnswer> surveyResponseAnswer = null;

        public int getSurveyId() {
            return surveyId;
        }

        public void setSurveyId(int surveyId) {
            this.surveyId = surveyId;
        }

        public List<SurveyResponseAnswer> getSurveyResponseAnswer() {
            return surveyResponseAnswer;
        }

        public void setSurveyResponseAnswer(List<SurveyResponseAnswer> surveyResponseAnswer) {
            this.surveyResponseAnswer = surveyResponseAnswer;
        }
    }

    public static class SurveyResponseAnswer {

        @SerializedName("surveyQuestionId")
        @Expose
        private int surveyQuestionId;
        @SerializedName("answer")
        @Expose
        private String answer;

        public int getSurveyQuestionId() {
            return surveyQuestionId;
        }

        public void setSurveyQuestionId(int surveyQuestionId) {
            this.surveyQuestionId = surveyQuestionId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
