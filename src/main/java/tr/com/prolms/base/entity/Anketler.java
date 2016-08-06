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
import javax.persistence.Table;

/**
 * Anketler class.
 */
@Entity
@Table(name = "anketler", schema = "", catalog = "denemedb")
public class Anketler {
  private int id;
  private String anketTanim;
  private Date anketTarihi;
  private Collection<AnketSoru> anketSorular;
  private Kisi kisi;

  @Id
  @Column(name = "id")
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "anket_tanim")
  public String getAnketTanim() {
    return anketTanim;
  }

  public void setAnketTanim(String anketTanim) {
    this.anketTanim = anketTanim;
  }

  @Basic
  @Column(name = "anket_tarihi")
  public Date getAnketTarihi() {
    return anketTarihi;
  }

  public void setAnketTarihi(Date anketTarihi) {
    this.anketTarihi = anketTarihi;
  }

  @OneToMany(mappedBy = "anket")
  public Collection<AnketSoru> getAnketSorular() {
    return anketSorular;
  }

  public void setAnketSorular(Collection<AnketSoru> anketSorular) {
    this.anketSorular = anketSorular;
  }

  @ManyToOne
  @JoinColumn(name = "anket_kisi_id")
  public Kisi getKisi() {
    return kisi;
  }

  public void setKisi(Kisi kisi) {
    this.kisi = kisi;
  }
}
