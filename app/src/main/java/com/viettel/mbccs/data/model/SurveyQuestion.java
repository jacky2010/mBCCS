package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.viettel.mbccs.constance.SurveyType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 6/10/17.
 */

public class SurveyQuestion implements Parcelable {

    private String content;
    private int code;
    private List<Answer> mAnswers;
    private String type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    public void setAnswers(List<Answer> answers) {
        mAnswers = answers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Answer implements Parcelable {
        private int code;
        private String content;
        private boolean isSelect;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.code);
            dest.writeString(this.content);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        }

        public Answer() {
        }

        protected Answer(Parcel in) {
            this.code = in.readInt();
            this.content = in.readString();
            this.isSelect = in.readByte() != 0;
        }

        public final Creator<Answer> CREATOR = new Creator<Answer>() {
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
        dest.writeString(this.content);
        dest.writeInt(this.code);
        dest.writeList(this.mAnswers);
        dest.writeString(this.type);
    }

    public SurveyQuestion() {
    }

    protected SurveyQuestion(Parcel in) {
        this.content = in.readString();
        this.code = in.readInt();
        this.mAnswers = new ArrayList<Answer>();
        in.readList(this.mAnswers, Answer.class.getClassLoader());
        this.type = in.readString();
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
