package model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jadwal implements Parcelable {
    @SerializedName("id_jadwal")
    @Expose
    private Integer idJadwal;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_akhir")
    @Expose
    private String statusAkhir;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("jumlah_peserta")
    @Expose
    private String jumlahPeserta;
    @SerializedName("tokoh")
    @Expose
    private String tokoh;
    @SerializedName("pendamping")
    @Expose
    private String pendamping;
    @SerializedName("live")
    @Expose
    private Integer live;
    @SerializedName("noted")
    @Expose
    private String noted;
    @SerializedName("uraian")
    @Expose
    private String uraian;
    @SerializedName("foto_kegiatan")
    @Expose
    private String fotoKegiatan;
    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;
    @SerializedName("waktu_selesai")
    @Expose
    private String waktuSelesai;

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setFotoKegiatan(String fotoKegiatan) {
        this.fotoKegiatan = fotoKegiatan;
    }

    public void setJumlahPeserta(String jumlahPeserta) {
        this.jumlahPeserta = jumlahPeserta;
    }

    public void setLive(Integer live) {
        this.live = live;
    }

    public void setNoted(String noted) {
        this.noted = noted;
    }

    public void setPendamping(String pendamping) {
        this.pendamping = pendamping;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusAkhir(String statusAkhir) {
        this.statusAkhir = statusAkhir;
    }

    public void setTokoh(String tokoh) {
        this.tokoh = tokoh;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getLive() {
        return live;
    }

    public String getStatus() {
        return status;
    }

    public String getTokoh() {
        return tokoh;
    }

    public String getUraian() {
        return uraian;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public String getFotoKegiatan() {
        return fotoKegiatan;
    }

    public String getJumlahPeserta() {
        return jumlahPeserta;
    }

    public String getNoted() {
        return noted;
    }

    public String getPendamping() {
        return pendamping;
    }

    public String getStatusAkhir() {
        return statusAkhir;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Jadwal createFromParcel(Parcel in) {
            return new Jadwal(in);
        }

        public Jadwal[] newArray(int size) {
            return new Jadwal[size];
        }
    };

    public Jadwal(Parcel in) {
        this.idJadwal = in.readInt();
        this.alamat = in.readString();
        this.deskripsi = in.readString();
        this.fotoKegiatan = in.readString();
        this.judul = in.readString();
        this.jumlahPeserta = in.readString();
        this.live = in.readInt();
        this.noted = in.readString();
        this.pendamping = in.readString();
        this.status = in.readString();
        this.statusAkhir = in.readString();
        this.tanggal = in.readString();
        this.tokoh = in.readString();
        this.uraian = in.readString();
        this.waktuMulai = in.readString();
        this.waktuSelesai = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idJadwal);
        dest.writeString(this.alamat);
        dest.writeString(this.deskripsi);
        dest.writeString(this.fotoKegiatan);
        dest.writeString(this.judul);
        dest.writeString(this.jumlahPeserta);
        dest.writeInt(this.live);
        dest.writeString(this.noted);
        dest.writeString(this.pendamping);
        dest.writeString(this.status);
        dest.writeString(this.statusAkhir);
        dest.writeString(this.tanggal);
        dest.writeString(this.tokoh);
        dest.writeString(this.uraian);
        dest.writeString(this.waktuMulai);
        dest.writeString(this.waktuSelesai);
    }
}
