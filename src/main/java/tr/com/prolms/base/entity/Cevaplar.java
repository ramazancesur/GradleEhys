package tr.com.prolms.base.entity;

import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Cevaplar class.
 */
@Entity
public class Cevaplar {
  private int id;
  private int cevapVerenId;
  private String cevap;
  private Date cevapTarihi;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "cevap_veren_id", nullable = false, insertable = true, updatable = true)
  public int getCevapVerenId() {
    return cevapVerenId;
  }

  public void setCevapVerenId(int cevapVerenId) {
    this.cevapVerenId = cevapVerenId;
  }

  @Basic
  @Column(name = "cevap", nullable = false, insertable = true, updatable = true, length = 4000)
  public String getCevap() {
    return cevap;
  }

  public void setCevap(String cevap) {
    this.cevap = cevap;
  }

  @Basic
  @Column(name = " cevap_tarihi", nullable = false, insertable = true, updatable = true)
  public Date getCevapTarihi() {
    return cevapTarihi;
  }

  public void setCevapTarihi(Date cevapTarihi) {
    this.cevapTarihi = cevapTarihi;
  }
}
