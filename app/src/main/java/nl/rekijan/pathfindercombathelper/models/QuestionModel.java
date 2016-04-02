package nl.rekijan.pathfindercombathelper.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Custom class to set fields for Answers
 *
 * @author Erik-Jan Krielen ej.krielen@gmail.com
 * @since 2-4-2016
 */
public class QuestionModel implements Parcelable {
    private String question;
    private String category;

    public QuestionModel(String question, String category) {
        this.question = question;
        this.category = category;
    }

    protected QuestionModel(Parcel in) {
        question = in.readString();
        category = in.readString();
    }

    //Parcelable region
    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(category);
    }
    //End Parcelable region

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}