package tr.com.prolms.base.entity;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OdevSoru class.
 */
@Entity
@Table(name = "odev_soru", schema = "", catalog = "denemedb")
public class OdevSoru {
  private int id;
  private String soru;
  private Date verilmeTarihi;
  private Date teslimTarihi;
  private Dersler ders;
  private Cevaplar cevaplar;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "soru", nullable = false, insertable = true, updatable = true, length = 4000)
  public String getSoru() {
    return soru;
  }

  public void setSoru(String soru) {
    this.soru = soru;
  }

  @Basic
  @Column(name = "verilme_tarihi", nullable = true, insertable = true, updatable = true)
  public Date getVerilmeTarihi() {
    return verilmeTarihi;
  }

  public void setVerilmeTarihi(Date verilmeTarihi) {
    this.verilmeTarihi = verilmeTarihi;
  }

  @Basic
  @Column(name = "teslim_tarihi", nullable = true, insertable = true, updatable = true)
  public Date getTeslimTarihi() {
    return teslimTarihi;
  }

  public void setTeslimTarihi(Date teslimTarihi) {
    this.teslimTarihi = teslimTarihi;
  }

  @ManyToOne
  @JoinColumn(name = "ders_id", referencedColumnName = "id")
  public Dersler getDers() {
    return ders;
  }

  public void setDers(Dersler ders) {
    this.ders = ders;
  }

  @ManyToOne
  @JoinColumn(name = "cevap_id", referencedColumnName = "id", nullable = false)
  public Cevaplar getCevaplar() {
    return cevaplar;
  }

  public void setCevaplar(Cevaplar cevaplar) {
    this.cevaplar = cevaplar;
  }
}
