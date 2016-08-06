package tr.com.prolms.base.dto;

import java.sql.Date;

/**
 * KursiyerDto class.
 */
public class KursiyerDto {
  private int id;
  private String adi;
  private String soyadi;
  private Date kayitTarihi;
  private String tcKimlikNo;
  private String email;
  private String telefon;
  private String adres;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAdi() {
    return adi;
  }

  public void setAdi(String adi) {
    this.adi = adi;
  }

  public String getSoyadi() {
    return soyadi;
  }

  public void setSoyadi(String soyadi) {
    this.soyadi = soyadi;
  }

  public Date getKayitTarihi() {
    return kayitTarihi;
  }

  public void setKayitTarihi(Date kayitTarihi) {
    this.kayitTarihi = kayitTarihi;
  }

  public String getTcKimlikNo() {
    return tcKimlikNo;
  }

  public void setTcKimlikNo(String tcKimlikNo) {
    this.tcKimlikNo = tcKimlikNo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelefon() {
    return telefon;
  }

  public void setTelefon(String telefon) {
    this.telefon = telefon;
  }

  public String getAdres() {
    return adres;
  }

  public void setAdres(String adres) {
    this.adres = adres;
  }
}
