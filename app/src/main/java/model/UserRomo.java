package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRomo {
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("password")
    @Expose
    private String password;

    public String getNik() {
        return nik;
    }

    public String getPassword() {
        return password;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
