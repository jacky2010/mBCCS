package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyQuestion implements Parcelable {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("isRequired")
    @Expose
    private String isRequired;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("surveyId")
    @Expose
    private int surveyId;
    @SerializedName("surveyQuestionAnswers")
    @Expose
    private List<Answer> surveyQuestionAnswers = null;
    @SerializedName("type")
    @Expose
    private int type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public List<Answer> getSurveyQuestionAnswers() {
        return surveyQuestionAnswers;
    }

    public void setSurveyQuestionAnswers(List<Answer> surveyQuestionAnswers) {
        this.surveyQuestionAnswers = surveyQuestionAnswers;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static class Answer implements Parcelable {
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("surveyQuestionId")
        @Expose
        private int surveyQuestionId;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSurveyQuestionId() {
            return surveyQuestionId;
        }

        public void setSurveyQuestionId(int surveyQuestionId) {
            this.surveyQuestionId = surveyQuestionId;
        }

        @Override
        public String toString() {
            return answer;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.answer);
            dest.writeInt(this.id);
            dest.writeString(this.status);
            dest.writeInt(this.surveyQuestionId);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public Answer() {
        }

        protected Answer(Parcel in) {
            this.answer = in.readString();
            this.id = in.readInt();
            this.status = in.readString();
            this.surveyQuestionId = in.readInt();
            this.isSelect = in.readByte() != 0;
        }

        public static final Creator<Answer> CREATOR = new Creator<Answer>() {
            @Override
            public Answer createFromParcel(Parcel source) {
                return new Answer(source);
            }

            @Override
            public Answer[] newArray(int size) {
                return new Answer[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.id);
        dest.writeString(this.isRequired);
        dest.writeString(this.question);
        dest.writeString(this.status);
        dest.writeInt(this.surveyId);
        dest.writeTypedList(this.surveyQuestionAnswers);
        dest.writeInt(this.type);
    }

    public SurveyQuestion() {
    }

    protected SurveyQuestion(Parcel in) {
        this.description = in.readString();
        this.id = in.readInt();
        this.isRequired = in.readString();
        this.question = in.readString();
        this.status = in.readString();
        this.surveyId = in.readInt();
        this.surveyQuestionAnswers = in.createTypedArrayList(Answer.CREATOR);
        this.type = in.readInt();
    }

    public static final Creator<SurveyQuestion> CREATOR = new Creator<SurveyQuestion>() {
        @Override
        public SurveyQuestion createFromParcel(Parcel source) {
            return new SurveyQuestion(source);
        }

        @Override
        public SurveyQuestion[] newArray(int size) {
            return new SurveyQuestion[size];
        }
    };
}


