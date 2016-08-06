package tr.com.prolms.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.com.prolms.base.dto.KursiyerDto;
import tr.com.prolms.base.entity.User;
import tr.com.prolms.base.service.UserService;

import java.util.List;


/**
 * Provides user list. Only admins use this controller.
 */
@Controller
public class UserController {

  /**
   * Models.
   */
  private static final String MODEL_KURSIYERDTO = "kursiyerDto";

  /**
   * User service.
   */
  @Autowired
  private UserService userService;

  /**
   * get User list.
   * @param model model
   * @return String /admin/users
   */
  @RequestMapping("/userlist")
  public String userList(Model model) {
    List<User> userList = userService.findAll();
    model.addAttribute("user", userList);
    return "/admin/users";
  }

  /**
   * get kursiyer add page.
   * @param model model
   * @return String
   */
  @RequestMapping("/kursiyer_add")
  public String addKursiyer(Model model) {
    model.addAttribute(MODEL_KURSIYERDTO, new KursiyerDto());
    return "/admin/kursiyer/add";
  }

  /**
   * save kursiyer.
   * @param kursiyerDto KursiyerDto
   * @return String
   */
  @RequestMapping("/kursiyer_save")
  public String saveKursiyer(@ModelAttribute("kursiyerDto") KursiyerDto kursiyerDto) {

    return "/admin/kursiyer/add";
  }
}
