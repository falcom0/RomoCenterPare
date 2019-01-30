package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Survei implements Parcelable{
    @SerializedName("idsurvei")
    @Expose
    private Integer idSurvei;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("publish")
    @Expose
    private Integer publish;
    @SerializedName("stop")
    @Expose
    private Integer stop;

    public Survei() {
    }

    public Integer getIdSurvei() {
        return idSurvei;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPublish() {
        return publish;
    }

    public Integer getStop() {
        return stop;
    }

    public void setIdSurvei(Integer idSurvei) {
        this.idSurvei = idSurvei;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public void setPublish(Integer publish) {
        this.publish = publish;
    }

    protected Survei(Parcel in) {
        this.idSurvei = in.readInt();
        this.title = in.readString();
        this.publish = in.readInt();
        this.stop = in.readInt();
    }

    public static final Creator<Survei> CREATOR = new Creator<Survei>() {
        @Override
        public Survei createFromParcel(Parcel in) {
            return new Survei(in);
        }

        @Override
        public Survei[] newArray(int size) {
            return new Survei[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idSurvei);
        dest.writeString(this.title);
        dest.writeInt(this.publish);
        dest.writeInt(this.stop);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
