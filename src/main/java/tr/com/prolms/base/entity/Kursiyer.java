package tr.com.prolms.base.entity;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Kursiyer class.
 */
@Entity
public class Kursiyer {
  private int id;
  private Boolean kursUcretiOdendi;
  private Collection<KursOdeme> kursOdemeler;
  private Kurslar kurslar;
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
  @Column(name = "kurs_ucreti_odendi", columnDefinition = "BIT")
  public Boolean getKursUcretiOdendi() {
    return kursUcretiOdendi;
  }

  public void setKursUcretiOdendi(Boolean kursUcretiOdendi) {
    this.kursUcretiOdendi = kursUcretiOdendi;
  }

  @OneToMany(mappedBy = "kursiyer")
  public Collection<KursOdeme> getKursOdemeler() {
    return kursOdemeler;
  }

  public void setKursOdemeler(Collection<KursOdeme> kursOdemeler) {
    this.kursOdemeler = kursOdemeler;
  }

  @ManyToOne
  @JoinColumn(name = "kurs_id", referencedColumnName = "id")
  public Kurslar getKurslar() {
    return kurslar;
  }

  public void setKurslar(Kurslar kurslar) {
    this.kurslar = kurslar;
  }

  @ManyToOne
  @JoinColumn(name = "kisi_id", referencedColumnName = "id")
  public Kisi getKisi() {
    return kisi;
  }

  public void setKisi(Kisi kisi) {
    this.kisi = kisi;
  }
}
