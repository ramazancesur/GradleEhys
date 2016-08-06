package tr.com.prolms.base.entity;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Notlar class.
 */
@Entity
public class Notlar {
  private int id;
  private String notIcerik;
  private Date notTarihi;
  private int aktif;
  private Kisi kisi;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = " not_icerik", nullable = false, insertable = true, updatable = true, length = 45)
  public String getNotIcerik() {
    return notIcerik;
  }

  public void setNotIcerik(String notIcerik) {
    this.notIcerik = notIcerik;
  }

  @Basic
  @Column(name = "not_tarihi", nullable = false, insertable = true, updatable = true)
  public Date getNotTarihi() {
    return notTarihi;
  }

  public void setNotTarihi(Date notTarihi) {
    this.notTarihi = notTarihi;
  }

  @Basic
  @Column(name = "aktif", nullable = false, insertable = true, updatable = true)
  public int getAktif() {
    return aktif;
  }

  public void setAktif(int aktif) {
    this.aktif = aktif;
  }

  @ManyToOne
  @JoinColumn(name = "not_kisi_id", referencedColumnName = "id", nullable = false)
  public Kisi getKisi() {
    return kisi;
  }

  public void setKisi(Kisi kisi) {
    this.kisi = kisi;
  }
}
