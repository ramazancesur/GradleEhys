package tr.com.prolms.base.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USERS")
@XmlRootElement
public class User extends BaseEntity {

  @Id
  @Column
  private Long id;

  @Column
  private String name;

  @Column
  private String surname;

  @Column
  private String email;

  @Column
  private String password;

  @Column
  private String username;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLES",
      joinColumns = {@JoinColumn(name = "USER_ID")},
      inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
  private Set<Role> roles = new HashSet<>();

  public Long getUserId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}