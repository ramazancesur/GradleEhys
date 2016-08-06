package tr.com.prolms.base.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * KursOdeme class.
 */
@Entity
@Table(name = "kurs_odeme", schema = "", catalog = "denemedb")
public class KursOdeme {
  private int id;
  private BigDecimal odemeMiktari;
  private int odemeTipi;
  private Date odemeTarihi;
  private Kurslar kurslar;
  private Kursiyer kursiyer;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "odeme_miktari", columnDefinition = "DECIMAL")
  public BigDecimal getOdemeMiktari() {
    return odemeMiktari;
  }

  public void setOdemeMiktari(BigDecimal odemeMiktari) {
    this.odemeMiktari = odemeMiktari;
  }

  @Basic
  @Column(name = "odeme_tipi", nullable = false, insertable = true, updatable = true)
  public int getOdemeTipi() {
    return odemeTipi;
  }

  public void setOdemeTipi(int odemeTipi) {
    this.odemeTipi = odemeTipi;
  }

  @Basic
  @Column(name = "odeme_tarihi", nullable = false, insertable = true, updatable = true)
  public Date getOdemeTarihi() {
    return odemeTarihi;
  }

  public void setOdemeTarihi(Date odemeTarihi) {
    this.odemeTarihi = odemeTarihi;
  }

  @ManyToOne
  @JoinColumn(name = "kurs_id", referencedColumnName = "id", nullable = false)
  public Kurslar getKurslar() {
    return kurslar;
  }

  public void setKurslar(Kurslar kurslar) {
    this.kurslar = kurslar;
  }

  @ManyToOne
  @JoinColumn(name = "kursiyer_id", referencedColumnName = "id", nullable = false)
  public Kursiyer getKursiyer() {
    return kursiyer;
  }

  public void setKursiyer(Kursiyer kursiyer) {
    this.kursiyer = kursiyer;
  }
}
