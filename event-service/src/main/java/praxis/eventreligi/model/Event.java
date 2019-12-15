package praxis.eventreligi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "judul")
    @NotNull(message = "judul harus diisi")
    private String judul;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "tempat")
    @NotNull(message = "tempat harus diisi")
    private String tempat;

    @Column(name = "waktu")
    @NotNull(message = "waktu harus diisi")
    private String waktu;

    @Column(name = "lokasi")
    @NotNull(message = "lokasi harus diisi")
    private String lokasi;

    @Column(name = "id_user")
    @NotNull
    private long idUser;

    @Column(name = "tgl_pendaftaran")
    @NotNull(message = "tgl pendaftaran harus diisi")
    private String tglPendaftaran;

    @Column(name = "tgl_penutupan")
    @NotNull(message = "tgl penutupan harus diisi")
    private String tglPenutupan;

    @Column(name = "status")
    @NotNull
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getTglPendaftaran() {
        return tglPendaftaran;
    }

    public void setTglPendaftaran(String tglPendaftaran) {
        this.tglPendaftaran = tglPendaftaran;
    }

    public String getTglPenutupan() {
        return tglPenutupan;
    }

    public void setTglPenutupan(String tglPenutupan) {
        this.tglPenutupan = tglPenutupan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}