package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUserAnswer {
    @SerializedName("userAnswerList")
    private List<UserAnswer> userAnswerList;

    public List<UserAnswer> getUserAnswerList() {
        return userAnswerList;
    }

    public void setUserAnswerList(List<UserAnswer> userAnswerList) {
        this.userAnswerList = userAnswerList;
    }
}
