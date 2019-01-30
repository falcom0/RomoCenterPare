package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveiQuestion {
    @SerializedName("idquestion")
    @Expose
    private Integer idQuestion;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("idsurvei")
    @Expose
    private Integer idSurvei;
    @SerializedName("answers")
    private List<SurveiAnswer> answers;

    public List<SurveiAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SurveiAnswer> answers) {
        this.answers = answers;
    }

    public Integer getIdSurvei() {
        return idSurvei;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setIdSurvei(Integer idSurvei) {
        this.idSurvei = idSurvei;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    @Override
    public String toString() {
        return this.getQuestion();
    }
}
