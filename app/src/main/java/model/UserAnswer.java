package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAnswer {
    @SerializedName("idquestion")
    @Expose
    private Integer idQuestion;
    @SerializedName("idanswer")
    @Expose
    private Integer idAnswer;
    @SerializedName("iduser")
    @Expose
    private Integer idUser;

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public UserAnswer(Integer idQuestion, Integer idAnswer, Integer idUser){
        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.idUser = idUser;
    }
}
