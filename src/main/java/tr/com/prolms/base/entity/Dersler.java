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
 * Dersler class.
 */
@Entity
public class Dersler {
  private int id;
  private Integer kursDersSaati;
  private Integer kalanSaat;
  private Collection<DersIcerikleri> dersIcerikleri;
  private Kurslar kurslar;
  private Collection<OdevSoru> odevSorular;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "kurs_ders_saati", nullable = true, insertable = true, updatable = true)
  public Integer getKursDersSaati() {
    return kursDersSaati;
  }

  public void setKursDersSaati(Integer kursDersSaati) {
    this.kursDersSaati = kursDersSaati;
  }

  @Basic
  @Column(name = "kalan_saat", nullable = true, insertable = true, updatable = true)
  public Integer getKalanSaat() {
    return kalanSaat;
  }

  public void setKalanSaat(Integer kalanSaat) {
    this.kalanSaat = kalanSaat;
  }

  @OneToMany(mappedBy = "dersler")
  public Collection<DersIcerikleri> getDersIcerikleri() {
    return dersIcerikleri;
  }

  public void setDersIcerikleri(Collection<DersIcerikleri> dersIcerikleri) {
    this.dersIcerikleri = dersIcerikleri;
  }

  @ManyToOne
  @JoinColumn(name = "kurs_id", referencedColumnName = "id")
  public Kurslar getKurslar() {
    return kurslar;
  }

  public void setKurslar(Kurslar kurslar) {
    this.kurslar = kurslar;
  }

  @OneToMany(mappedBy = "ders")
  public Collection<OdevSoru> getOdevSorular() {
    return odevSorular;
  }

  public void setOdevSorular(Collection<OdevSoru> odevSorular) {
    this.odevSorular = odevSorular;
  }
}
