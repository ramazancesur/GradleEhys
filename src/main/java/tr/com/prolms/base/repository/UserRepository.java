package tr.com.prolms.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.prolms.base.entity.User;

/**
 * This repository provide crud operation for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUsernameAndPassword(String username, String password);

  User findUserByUsername(String username);
}
