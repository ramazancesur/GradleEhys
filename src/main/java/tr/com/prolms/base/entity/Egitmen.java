package tr.com.prolms.base.entity;

import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Egitmen class.
 */
@Entity
public class Egitmen {
  private int id;
  private BigDecimal ucreti;
  private Kurslar kurslar;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "ucreti", columnDefinition = "DECIMAL")
  public BigDecimal getUcreti() {
    return ucreti;
  }

  public void setUcreti(BigDecimal ucreti) {
    this.ucreti = ucreti;
  }

  @ManyToOne
  @JoinColumn(name = "kurs_id", referencedColumnName = "id", nullable = false)
  public Kurslar getKurslar() {
    return kurslar;
  }

  public void setKurslar(Kurslar kurslar) {
    this.kurslar = kurslar;
  }
}
