package com.viettel.mbccs.data.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viettel.mbccs.data.model.Survey;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 23/06/2017.
 */

public class GetSurveyKPPResponse implements Parcelable {

    @SerializedName("listSurvey")
    @Expose
    private List<Survey> mSurveys;

    public List<Survey> getSurveys() {
        return mSurveys;
    }

    public void setSurveys(List<Survey> surveys) {
        mSurveys = surveys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.mSurveys);
    }

    public GetSurveyKPPResponse() {
    }

    protected GetSurveyKPPResponse(Parcel in) {
        this.mSurveys = in.createTypedArrayList(Survey.CREATOR);
    }

    public static final Parcelable.Creator<GetSurveyKPPResponse> CREATOR =
            new Parcelable.Creator<GetSurveyKPPResponse>() {
                @Override
                public GetSurveyKPPResponse createFromParcel(Parcel source) {
                    return new GetSurveyKPPResponse(source);
                }

                @Override
                public GetSurveyKPPResponse[] newArray(int size) {
                    return new GetSurveyKPPResponse[size];
                }
            };
}
