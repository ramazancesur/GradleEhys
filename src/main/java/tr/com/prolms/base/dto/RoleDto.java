package tr.com.prolms.base.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RoleDto implements Serializable {

  private Long id;

  @NotNull
  @NotEmpty
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getString() {
    return String.format("RoleDto - Id: [%s] Name: [%s]", this.id, this.name);
  }

}
