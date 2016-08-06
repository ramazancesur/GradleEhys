package tr.com.prolms.base.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * When the exception occurs, this controller runs.
 * You can catch every exception in this controller.
 */
@ControllerAdvice
public class AdviceController {

  /**
   * Error handling.
   * @param exception exp
   * @param request   request
   * @return error page
   */
  @ExceptionHandler(Exception.class)
  public ModelAndView exception(Exception exception, WebRequest request) {
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("msg", exception.getMessage());
    return modelAndView;
  }
}
