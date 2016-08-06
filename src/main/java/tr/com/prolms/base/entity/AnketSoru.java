package tr.com.prolms.base.entity;

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
 * AnketSoru class.
 */
@Entity
@Table(name = "anket_soru", schema = "", catalog = "denemedb")
public class AnketSoru {
  private int id;
  private String soru;
  private Anketler anket;
  private Collection<AnketSoruCevap> anketSoruCevaplar;

  @Id
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "soru", nullable = false, insertable = true, updatable = true, length = 4000)
  public String getSoru() {
    return soru;
  }

  public void setSoru(String soru) {
    this.soru = soru;
  }

  @ManyToOne
  @JoinColumn(name = "anket_id", referencedColumnName = "id")
  public Anketler getAnket() {
    return anket;
  }

  public void setAnket(Anketler anket) {
    this.anket = anket;
  }

  @OneToMany(mappedBy = "anketSoru")
  public Collection<AnketSoruCevap> getAnketSoruCevaplar() {
    return anketSoruCevaplar;
  }

  public void setAnketSoruCevaplar(Collection<AnketSoruCevap> anketSoruCevaplar) {
    this.anketSoruCevaplar = anketSoruCevaplar;
  }
}
