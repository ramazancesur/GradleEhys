package tr.com.prolms.base.entity;

import java.sql.Date;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Kisi class.
 */
@Entity
public class Kisi {
  private int id;
  private String adi;
  private String soyadi;
  private Date kayitTarihi;
  private String tcKimlikNo;
  private String yetkitanimi;
  private String email;
  private String sifre;
  private String telefon;
  private String adres;
  private Collection<Anketler> anketler;
  private Yetkiler yetkiler;
  private Collection<Notlar> notlar;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "adi", nullable = true, insertable = true, updatable = true, length = 50)
  public String getAdi() {
    return adi;
  }

  public void setAdi(String adi) {
    this.adi = adi;
  }

  @Basic
  @Column(name = "soyadi", nullable = true, insertable = true, updatable = true, length = 50)
  public String getSoyadi() {
    return soyadi;
  }

  public void setSoyadi(String soyadi) {
    this.soyadi = soyadi;
  }

  @Basic
  @Column(name = "kayit_tarihi", nullable = true, insertable = true, updatable = true)
  public Date getKayitTarihi() {
    return kayitTarihi;
  }

  public void setKayitTarihi(Date kayitTarihi) {
    this.kayitTarihi = kayitTarihi;
  }

  @Basic
  @Column(name = "tc_kimlik_no", nullable = true, insertable = true, updatable = true, length = 11)
  public String getTcKimlikNo() {
    return tcKimlikNo;
  }

  public void setTcKimlikNo(String tcKimlikNo) {
    this.tcKimlikNo = tcKimlikNo;
  }

  @Basic
  @Column(name = "yetkitanimi", nullable = true, insertable = true, updatable = true, length = 50)
  public String getYetkitanimi() {
    return yetkitanimi;
  }

  public void setYetkitanimi(String yetkitanimi) {
    this.yetkitanimi = yetkitanimi;
  }

  @Basic
  @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 100)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "sifre", nullable = true, insertable = true, updatable = true, length = 50)
  public String getSifre() {
    return sifre;
  }

  public void setSifre(String sifre) {
    this.sifre = sifre;
  }

  @Basic
  @Column(name = "telefon", nullable = true, insertable = true, updatable = true, length = 13)
  public String getTelefon() {
    return telefon;
  }

  public void setTelefon(String telefon) {
    this.telefon = telefon;
  }

  @Basic
  @Column(name = "adres")
  public String getAdres() {
    return adres;
  }

  public void setAdres(String adres) {
    this.adres = adres;
  }

  @OneToMany(mappedBy = "kisi")
  public Collection<Anketler> getAnketler() {
    return anketler;
  }

  public void setAnketler(Collection<Anketler> anketler) {
    this.anketler = anketler;
  }

  @ManyToOne
  @JoinColumn(name = "yetki_id", referencedColumnName = "id")
  public Yetkiler getYetkiler() {
    return yetkiler;
  }

  public void setYetkiler(Yetkiler yetkiler) {
    this.yetkiler = yetkiler;
  }

  @OneToMany(mappedBy = "kisi")
  public Collection<Notlar> getNotlar() {
    return notlar;
  }

  public void setNotlar(Collection<Notlar> notlar) {
    this.notlar = notlar;
  }
}
