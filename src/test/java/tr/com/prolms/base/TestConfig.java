package tr.com.prolms.base;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tr.com.prolms.base.service.RoleManagementService;
import tr.com.prolms.base.service.UserService;

@Configuration
@ComponentScan(basePackages = {"tr.com.prolms.base.controller"})
public class TestConfig {
  private String utf8 = "UTF-8";

  @Bean
  public RoleManagementService roleManagementService() {
    return Mockito.mock(RoleManagementService.class);
  }

  @Bean
  public UserService userService() {
    return Mockito.mock(UserService.class);
  }
}
