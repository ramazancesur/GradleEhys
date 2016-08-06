package tr.com.prolms.base.entity;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by coskun.deniz on 28.07.2015.
 */
public class RoleBuilder {

  private Role role;


  public RoleBuilder() {
    this.role = new Role();
  }

  public RoleBuilder id(Long id) {
    ReflectionTestUtils.setField(role, "roleId", id);
    return this;
  }

  public RoleBuilder name(String name) {
    role.setName(name);
    return this;
  }

  public Role build() {
    return role;
  }
}
