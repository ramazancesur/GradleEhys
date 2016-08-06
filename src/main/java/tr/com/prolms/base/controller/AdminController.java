package tr.com.prolms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.prolms.base.entity.User;
import tr.com.prolms.base.service.UserService;

import java.security.Principal;
import java.util.List;

/**
 * This controller for admin.
 * Not-admin users don't access methods of this class.
 * If not-admin users want to use this controller,
 * they will see 403 not authorities page.
 */
@Controller
public class AdminController {

  /**
   * User service.
   */
  @Autowired
  private UserService userService;

  /**
   * Access Denied for not admin users.
   * @param user Principal
   * @return String 403
   */
  @RequestMapping(value = "/403", method = RequestMethod.GET)
  public String accessDenied(Principal user, Model model) {
    //TODO get messages from prop file
    model.addAttribute("msg", "Bu sayfaya erişmeye yetkiniz yoktur.");
    return "403";
  }

  /**
   * Admin index page.
   * @return String admin/index
   */
  @RequestMapping(value = "/admin", method = RequestMethod.GET)
  public String index() {
    return "/admin/index";
  }

  /**
   * User list.
   * @param model     model
   * @param principal principal
   * @return view users
   */
  @RequestMapping("/admin/users")
  public String list(Principal principal, Model model) {
    List<User> userList = userService.findAll();
    model.addAttribute(userList);
    return "/admin/users";
  }

}
