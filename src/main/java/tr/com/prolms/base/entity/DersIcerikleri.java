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
 * DersIcerikleri class.
 */
@Entity
@Table(name = "ders_icerikleri", schema = "", catalog = "denemedb")
public class DersIcerikleri {
  private int id;
  private Date verilmeTarihi;
  private Date teslimTarihi;
  private int icerikTipi;
  private String icerikYolu;
  private Dersler dersler;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  @Basic
  @Column(name = "icerik_tipi", nullable = false, insertable = true, updatable = true)
  public int getIcerikTipi() {
    return icerikTipi;
  }

  public void setIcerikTipi(int icerikTipi) {
    this.icerikTipi = icerikTipi;
  }

  @Basic
  @Column(name = "icerik_yolu", nullable = true, insertable = true, updatable = true, length = 4000)
  public String getIcerikYolu() {
    return icerikYolu;
  }

  public void setIcerikYolu(String icerikYolu) {
    this.icerikYolu = icerikYolu;
  }

  @ManyToOne
  @JoinColumn(name = "ders_id", referencedColumnName = "id")
  public Dersler getDersler() {
    return dersler;
  }

  public void setDersler(Dersler dersler) {
    this.dersler = dersler;
  }
}
