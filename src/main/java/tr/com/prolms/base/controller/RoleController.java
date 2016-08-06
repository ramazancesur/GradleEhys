package tr.com.prolms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.com.prolms.base.dto.RoleDto;
import tr.com.prolms.base.dto.RoleMapper;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.service.RoleManagementService;

import java.util.List;


/**
 * Provides roles operations.
 * You can read, add, edit, delete roles.
 */
@Controller
@RequestMapping(value = "/roles")
public class RoleController {

  //region STATIC FINAL STRING
  static final String REDIRECT_ROLES = "redirect:/roles";
  static final String MODEL_MESSAGE = "message";
  static final String VIEW_ROLE_LIST = "/roles/list";
  static final String VIEW_ROLE_EDIT = "/roles/edit";
  static final String VIEW_ROLE_READ = "/roles/read";
  static final String MODEL_ATTRIBUTE_ROLES_LIST = "roles";
  static final String MODEL_ATTRIBUTE_ROLEDTO = "roleDto";
  /**
   * Role service.
   */
  @Autowired
  RoleManagementService roleManagementService;
  //endregion

  //region DATABASE OPERATIONS

  /**
   * GET /roles - Role list page.
   * @return VIEW_ROLE_LIST
   */
  @RequestMapping(method = RequestMethod.GET)
  public Object listRoles(Model model) {
    List<Role> roles = roleManagementService.findAll();
    addHypermediaLinksToEntities(roles);
    model.addAttribute(MODEL_ATTRIBUTE_ROLES_LIST, roles);
    return VIEW_ROLE_LIST;
  }

  /**
   * GET /roles/{id} - Get a role.
   * @return VIEW_ROLE_READ
   */
  @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public Object readRole(Model model,
                         @PathVariable("id") Long id) {
    Role roles = roleManagementService.findRoleById(id);
    if (roles == null) {
      //TODO check for html
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    RoleDto roleDto = RoleMapper.getDto(roles);
    model.addAttribute(MODEL_ATTRIBUTE_ROLEDTO, roleDto);
    return VIEW_ROLE_READ;
  }

  /**
   * POST /roles - Add role.
   * @param roleDto       dto
   * @param redirectAttrs send message
   * @return REDIRECT_ROLES
   */
  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Object addRole(RedirectAttributes redirectAttrs,
                        @ModelAttribute RoleDto roleDto) {
    Role role = RoleMapper.getRole(new Role(), roleDto);
    roleManagementService.create(role);
    //TODO get messages from prop file
    String message = role.getName() + " başarıyla eklendi.";
    redirectAttrs.addFlashAttribute(MODEL_MESSAGE, message);
    return REDIRECT_ROLES;
  }

  /**
   * PUT /roles/{id}  - Edit role.
   * @param redirectAttrs send message
   * @param roleDto       dto
   * @param id            id
   * @return REDIRECT_ROLES
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.POST)
  public Object editRole(RedirectAttributes redirectAttrs,
                         @ModelAttribute RoleDto roleDto,
                         @PathVariable("id") Long id) {
    String message = null;
    Long idR = roleDto.getId();
    Role oldRole = roleManagementService.findRoleById(idR);
    Role newRole = RoleMapper.getRole(oldRole, roleDto);
    roleManagementService.update(newRole);
    //TODO get messages from prop file
    message = newRole.getName() + " başarıyla güncellendi.";
    redirectAttrs.addFlashAttribute(MODEL_MESSAGE, message);
    return REDIRECT_ROLES;
  }

  /**
   * DELETE /roles/{id} - Delete role.
   * @param redirectAttrs send message
   * @param id            id
   * @return REDIRECT_ROLES
   */
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
  public Object deleteRole(RedirectAttributes redirectAttrs,
                           @PathVariable("id") Long id) {
    Role role = roleManagementService.findRoleById(id);
    roleManagementService.deleteRole(id);
    //TODO get messages from prop file
    String message = role.getName() + " silindi.";
    redirectAttrs.addFlashAttribute(MODEL_MESSAGE, message);
    return REDIRECT_ROLES;
  }
  //endregion

  //region PAGE_NAVIGATION

  /**
   * Go to page for new data.
   * @param model model
   * @return roles/edit.html
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public Object newRole(Model model) {
    RoleDto roleDto = new RoleDto();
    model.addAttribute(MODEL_ATTRIBUTE_ROLEDTO, roleDto);
    model.addAttribute("isNew", true);
    return VIEW_ROLE_EDIT;
  }

  /**
   * Go to editing page.
   * @param model model
   * @param id    id
   * @return roles/edit.html
   */
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public Object editingRole(Model model,
                            @PathVariable(value = "id") Long id) {
    Role role = roleManagementService.findRoleById(id);
    RoleDto roleDto = RoleMapper.getDto(role);
    model.addAttribute(MODEL_ATTRIBUTE_ROLEDTO, roleDto);
    //TODO get messages from prop file
    String message = role.getName() + " görüntülendi";
    model.addAttribute(MODEL_MESSAGE, message);
    model.addAttribute("isNew", false);
    return VIEW_ROLE_EDIT;
  }
  //endregion

  //region HELPER METHOD

  /**
   * Add hypermedia links to entity.
   * @param roles entity
   */
  private void addHypermediaLinksToEntities(List<Role> roles) {
    for (Role role : roles) {
      RoleDto roleDto = new RoleDto();
      //this give us edit,delete and create link for model
      Link linkEdit = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
          .methodOn(RoleController.class)
          .editRole(null, RoleMapper.getDto(role), role.getRoleId())).withRel("put");
      Link linkDelete = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
          .methodOn(RoleController.class)
          .deleteRole(null, role.getRoleId())).withRel("delete");
      Link linkAdd = ControllerLinkBuilder.linkTo(ControllerLinkBuilder
          .methodOn(RoleController.class)
          .addRole(null, roleDto)).withRel("post");
      role.add(linkEdit);
      role.add(linkDelete);
      role.add(linkAdd);
    }
  }
  //endregion
}
