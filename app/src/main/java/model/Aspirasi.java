package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aspirasi implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("no_aspirasi")
    @Expose
    private String noAspirasi;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;
    @SerializedName("kronologi")
    @Expose
    private String kronologi;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("prioritas")
    @Expose
    private String prioritas;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNoAspirasi(String noAspirasi) {
        this.noAspirasi = noAspirasi;
    }

    public String getNoAspirasi() {
        return noAspirasi;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setKronologi(String kronologi) {
        this.kronologi = kronologi;
    }

    public String getKronologi() {
        return kronologi;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getPrioritas() {
        return prioritas;
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Aspirasi createFromParcel(Parcel in) {
            return new Aspirasi(in);
        }

        public Aspirasi[] newArray(int size) {
            return new Aspirasi[size];
        }
    };

    public Aspirasi(Parcel in) {
        this.id = in.readInt();
        this.noAspirasi = in.readString();
        this.judul = in.readString();
        this.tujuan = in.readString();
        this.kronologi = in.readString();
        this.status = in.readString();
        this.prioritas = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.noAspirasi);
        dest.writeString(this.judul);
        dest.writeString(this.tujuan);
        dest.writeString(this.kronologi);
        dest.writeString(this.status);
        dest.writeString(this.prioritas);
    }
}

