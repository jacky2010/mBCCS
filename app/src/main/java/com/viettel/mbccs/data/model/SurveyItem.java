package com.viettel.mbccs.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by minhnx on 6/9/17.
 */

public class SurveyItem implements Parcelable {
    @SerializedName("createUser")
    @Expose
    private String createUser;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("endMessage")
    @Expose
    private String endMessage;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("introMessage")
    @Expose
    private String introMessage;
    @SerializedName("isSkipIntro")
    @Expose
    private String isSkipIntro;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("ownerCode")
    @Expose
    private String ownerCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("title")

    @Expose
    private String title;
    @SerializedName("surveyQuestions")
    @Expose
    private List<SurveyQuestion> mSurveyQuestions;

    @SerializedName("toDate")
    @Expose
    private String toDate;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void setEndMessage(String endMessage) {
        this.endMessage = endMessage;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroMessage() {
        return introMessage;
    }

    public void setIntroMessage(String introMessage) {
        this.introMessage = introMessage;
    }

    public String getIsSkipIntro() {
        return isSkipIntro;
    }

    public void setIsSkipIntro(String isSkipIntro) {
        this.isSkipIntro = isSkipIntro;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<SurveyQuestion> getSurveyQuestions() {
        return mSurveyQuestions;
    }

    public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
        mSurveyQuestions = surveyQuestions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createUser);
        dest.writeString(this.description);
        dest.writeString(this.endMessage);
        dest.writeString(this.fromDate);
        dest.writeInt(this.id);
        dest.writeString(this.introMessage);
        dest.writeString(this.isSkipIntro);
        dest.writeString(this.lang);
        dest.writeString(this.ownerCode);
        dest.writeString(this.status);
        dest.writeString(this.title);
        dest.writeTypedList(this.mSurveyQuestions);
        dest.writeString(this.toDate);
    }

    public SurveyItem() {
    }

    protected SurveyItem(Parcel in) {
        this.createUser = in.readString();
        this.description = in.readString();
        this.endMessage = in.readString();
        this.fromDate = in.readString();
        this.id = in.readInt();
        this.introMessage = in.readString();
        this.isSkipIntro = in.readString();
        this.lang = in.readString();
        this.ownerCode = in.readString();
        this.status = in.readString();
        this.title = in.readString();
        this.mSurveyQuestions = in.createTypedArrayList(SurveyQuestion.CREATOR);
        this.toDate = in.readString();
    }

    public static final Parcelable.Creator<SurveyItem> CREATOR =
            new Parcelable.Creator<SurveyItem>() {
                @Override
                public SurveyItem createFromParcel(Parcel source) {
                    return new SurveyItem(source);
                }

                @Override
                public SurveyItem[] newArray(int size) {
                    return new SurveyItem[size];
                }
            };
}
