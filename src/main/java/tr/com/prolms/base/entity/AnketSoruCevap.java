package tr.com.prolms.base.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AnketSoruCevap class.
 */
@Entity
@Table(name = "anket_soru_cevap", schema = "", catalog = "denemedb")
public class AnketSoruCevap {
  private int id;
  private int cevapVerenId;
  private AnketSoru anketSoru;

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

  @ManyToOne
  @JoinColumn(name = "soru_id", referencedColumnName = "id")
  public AnketSoru getAnketSoru() {
    return anketSoru;
  }

  public void setAnketSoru(AnketSoru anketSoru) {
    this.anketSoru = anketSoru;
  }
}
