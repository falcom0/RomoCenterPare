package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {
    @SerializedName("id_user")
    @Expose
    private String nik;
    @SerializedName("password")
    @Expose
    private String password;
}
