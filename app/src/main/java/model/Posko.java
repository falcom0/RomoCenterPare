package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Posko implements Parcelable {
    @SerializedName("id_posko")
    @Expose
    private Integer idPosko;
    @SerializedName("coordinate")
    @Expose
    private String koordinat;
    @SerializedName("nameposko")
    @Expose
    private String namaPosko;
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;

    public Integer getIdPosko() {
        return idPosko;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public String getNamaPosko() {
        return namaPosko;
    }

    public void setIdPosko(Integer idPosko) {
        this.idPosko = idPosko;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public void setNamaPosko(String namaPosko) {
        this.namaPosko = namaPosko;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Posko createFromParcel(Parcel in) {
            return new Posko(in);
        }

        public Posko[] newArray(int size) {
            return new Posko[size];
        }
    };

    public Posko(){

    }

    public Posko(Parcel in) {
        this.idPosko = in.readInt();
        this.kecamatan = in.readString();
        this.koordinat = in.readString();
        this.namaPosko = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idPosko);
        dest.writeString(this.kecamatan);
        dest.writeString(this.koordinat);
        dest.writeString(this.namaPosko);
    }

    @Override
    public String toString() {
        return this.namaPosko;
    }
}
