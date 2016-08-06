package tr.com.prolms.base.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ROLES")
@XmlRootElement
public class Role extends BaseEntity implements Serializable {

  @Id
  @Column(name = "ROLE_ID")
  @GeneratedValue
  private Long roleId;

  @Column
  private String name;

  @NotFound(action = NotFoundAction.IGNORE)
  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<User>();

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonBackReference
  @XmlTransient
  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

}
