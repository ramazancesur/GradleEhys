package tr.com.prolms.base.entity;

import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Kurslar class.
 */
@Entity
public class Kurslar {
  private static final String kurslarMappedBy = "kurslar";
  private int id;
  private String kursAdi;
  private String kursDonemi;
  private BigDecimal kursUcreti;
  private Integer dersSaati;
  private Collection<Dersler> dersler;
  private Collection<Egitmen> egitmenler;
  private Collection<KursOdeme> kursOdemeler;
  private Collection<Kursiyer> kursiyerler;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "kurs_adi", nullable = true, insertable = true, updatable = true, length = 50)
  public String getKursAdi() {
    return kursAdi;
  }

  public void setKursAdi(String kursAdi) {
    this.kursAdi = kursAdi;
  }

  @Basic
  @Column(name = "kurs_donemi", nullable = true, insertable = true, updatable = true, length = 100)
  public String getKursDonemi() {
    return kursDonemi;
  }

  public void setKursDonemi(String kursDonemi) {
    this.kursDonemi = kursDonemi;
  }

  @Basic
  @Column(name = "kurs_ucreti", columnDefinition = "DECIMAL")
  public BigDecimal getKursUcreti() {
    return kursUcreti;
  }

  public void setKursUcreti(BigDecimal kursUcreti) {
    this.kursUcreti = kursUcreti;
  }

  @Basic
  @Column(name = "ders_saati", nullable = true, insertable = true, updatable = true)
  public Integer getDersSaati() {
    return dersSaati;
  }

  public void setDersSaati(Integer dersSaati) {
    this.dersSaati = dersSaati;
  }

  @OneToMany(mappedBy = kurslarMappedBy)
  public Collection<Dersler> getDersler() {
    return dersler;
  }

  public void setDersler(Collection<Dersler> dersler) {
    this.dersler = dersler;
  }

  @OneToMany(mappedBy = kurslarMappedBy)
  public Collection<Egitmen> getEgitmenler() {
    return egitmenler;
  }

  public void setEgitmenler(Collection<Egitmen> egitmenler) {
    this.egitmenler = egitmenler;
  }

  @OneToMany(mappedBy = kurslarMappedBy)
  public Collection<KursOdeme> getKursOdemeler() {
    return kursOdemeler;
  }

  public void setKursOdemeler(Collection<KursOdeme> kursOdemeler) {
    this.kursOdemeler = kursOdemeler;
  }

  @OneToMany(mappedBy = kurslarMappedBy)
  public Collection<Kursiyer> getKursiyerler() {
    return kursiyerler;
  }

  public void setKursiyerler(Collection<Kursiyer> kursiyerler) {
    this.kursiyerler = kursiyerler;
  }
}
