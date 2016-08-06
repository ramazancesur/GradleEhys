package tr.com.prolms.base.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SecurityUser extends User implements UserDetails {

  /**
   * constr.
   * @param user kullanıcı
   */
  public SecurityUser(User user) {
    if (user != null) {
      this.setName(user.getName());
      this.setEmail(user.getEmail());
      this.setPassword(user.getPassword());
      this.setRoles(user.getRoles());
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> authorities = new ArrayList<>();
    Set<Role> userRoles = this.getRoles();

    if (userRoles != null) {
      for (Role role : userRoles) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        authorities.add(authority);
      }
    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
