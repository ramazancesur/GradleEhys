package tr.com.prolms.base.service;

import tr.com.prolms.base.entity.Role;

import java.util.List;

public interface RoleManagementService {

  List<Role> findAll();

  Role create(Role role);

  Role findRoleById(long id);

  Role update(Role role);

  void deleteRole(long id);
}
