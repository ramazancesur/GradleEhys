package tr.com.prolms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tr.com.prolms.base.entity.User;
import tr.com.prolms.base.service.UserService;

import java.security.Principal;

/**
 * This controller provides login operations.
 */
@Controller
public class LoginController {

  /**
   * Pages.
   */
  private static final String PAGE_WELCOME = "/welcome";

  @Autowired
  UserService userService;

  /**
   * First page of app.
   * @return String index
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Principal user) {
    if (user != null) {
      return PAGE_WELCOME;
    }
    return "/index";
  }

  /**
   * Login page.
   * @return String login
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Principal user) {
    if (user != null) {
      return PAGE_WELCOME;
    }
    return "/login";
  }

  /**
   * Welcompe page. You can see this page after login.
   * @param user  Principal
   * @param model Model
   * @return String welcome
   * @throws Exception exp
   */
  @RequestMapping(value = PAGE_WELCOME, method = RequestMethod.GET)
  public String welcome(Principal user, Model model) throws Exception {
    User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = user1.getUsername();
    model.addAttribute("message", name);
    return PAGE_WELCOME;
  }

  /**
   * When user log in wrong password and username.
   * @param model Model
   * @return error in login page
   */
  @RequestMapping(value = "/error", method = RequestMethod.GET)
  public String loginError(Model model) {
    //TODO get messages from prop file
    model.addAttribute("message", "Hata verdi.");
    return "/login";
  }

  /**
   * Not found page.
   * @param model Model
   * @return String 404
   */
  @RequestMapping(value = "/404", method = RequestMethod.GET)
  public String loginError1(Model model) {

    return "/404";
  }
}
