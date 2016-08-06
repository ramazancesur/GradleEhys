package tr.com.prolms.base.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.com.prolms.base.entity.User;
import tr.com.prolms.base.model.SecurityUser;
import tr.com.prolms.base.repository.UserRepository;

import java.util.List;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

  @Resource
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User create(User user) {
    return userRepository.save(user);
  }

  public User findUserById(long id) {
    return userRepository.findOne(id);
  }

  public User login(String email, String password) {
    return userRepository.findByUsernameAndPassword(email, password);
  }

  public User update(User user) {
    return userRepository.save(user);
  }

  public void deleteUser(long id) {
    userRepository.delete(id);
  }

  public User findUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = this.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("UserName " + username + " not found");
    }
    return new SecurityUser(user);
  }
}
