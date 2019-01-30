package model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SurveiAnswer {
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("idanswer")
    @Expose
    private Integer idanswer;

    public Integer getIdanswer() {
        return idanswer;
    }


    public String getAnswer() {
        return answer;
    }

    public void setIdanswer(Integer idanswer) {
        this.idanswer = idanswer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SurveiAnswer(Integer idanswer, String answer){
        this.idanswer = idanswer;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return this.getAnswer();
    }
}
