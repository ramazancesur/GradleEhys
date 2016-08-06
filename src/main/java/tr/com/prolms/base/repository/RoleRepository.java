package tr.com.prolms.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tr.com.prolms.base.entity.Role;

import java.util.List;

/**
 * This repository provide crud operation for Role entity.
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

  String myCache = "myCache";

  Role findOne(Long roleId);

  //  @Cacheable(value = myCache)
  List<Role> findAll();

  //  @CacheEvict(value = myCache, allEntries = true)
  <S extends Role> S save(S entity);

  //  @CacheEvict(value = myCache, allEntries = true)
  void delete(Long roleId);


}
