package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PoskoJadwal extends Posko implements Parcelable {
    @SerializedName("id_posko_jadwal")
    @Expose
    private Integer idPoskoJadwal;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;
    @SerializedName("waktu_selesai")
    @Expose
    private String waktuSelesai;
    @SerializedName("judul")
    @Expose
    private String judul;

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public Integer getIdPoskoJadwal() {
        return idPoskoJadwal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public String getJudul() {
        return judul;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setIdPoskoJadwal(Integer idPoskoJadwal) {
        this.idPoskoJadwal = idPoskoJadwal;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PoskoJadwal createFromParcel(Parcel in) {
            return new PoskoJadwal(in);
        }

        public PoskoJadwal[] newArray(int size) {
            return new PoskoJadwal[size];
        }
    };

    public PoskoJadwal(Parcel in) {
        this.idPoskoJadwal = in.readInt();
        this.deskripsi = in.readString();
        this.judul = in.readString();
        this.waktuMulai = in.readString();
        this.waktuSelesai = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idPoskoJadwal);
        dest.writeString(this.deskripsi);
        dest.writeString(this.judul);
        dest.writeString(this.waktuMulai);
        dest.writeString(this.waktuSelesai);
    }
}
