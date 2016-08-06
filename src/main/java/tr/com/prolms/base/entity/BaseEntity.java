package tr.com.prolms.base.entity;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class BaseEntity extends ResourceSupport {
  /**
   * Column Definition.
   */
  @org.hibernate.annotations.Type(type = "true_false")
  @Column(name = "IS_DELETED", columnDefinition = "BIT(1) DEFAULT 0")
  private boolean isDeleted;

  /**
   * f
   * Column Definition.
   */
  @Column(name = "CREATION_TIME")
  private Date creationTime;

  /**
   * Column Definition.
   */
  @Column(name = "MODIFICATION_TIME")
  private Date modificationTime;

  /**
   * Get creation time.
   * @return create time
   */
  public Date getCreationTime() {
    return creationTime;
  }

  /**
   * Set creationTime.
   * @param creationTime Date
   */
  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  /**
   * Get modification time.
   * @return create time
   */
  public Date getModificationTime() {
    return modificationTime;
  }

  /**
   * Set IsDeleted.
   * @param modificationTime Date
   */
  public void setModificationTime(Date modificationTime) {
    this.modificationTime = modificationTime;
  }

  /**
   * IsDeleted.
   * @return String IsDeleted
   */
  public boolean getIsDeleted() {
    return isDeleted;
  }

  /**
   * Set IsDeleted.
   * @param isDeleted String IsDeleted
   */
  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  /**
   * update.
   */
  @PreUpdate
  public void preUpdate() {
    modificationTime = new Date();
  }

  /**
   * new create.
   */
  @PrePersist
  public void prePersist() {
    Date now = new Date();
    creationTime = now;
    modificationTime = now;
  }

}
