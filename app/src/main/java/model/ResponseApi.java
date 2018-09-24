package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseApi {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kta")
    @Expose
    private String kta;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("id_user")
    @Expose
    private String idUser;

    public String getNik() {
        return nik;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKta() {
        return kta;
    }

    public void setKta(String kta) {
        this.kta = kta;
    }

    public String getMessage() {
        return message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
