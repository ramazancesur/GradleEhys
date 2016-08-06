package tr.com.prolms.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import tr.com.prolms.base.service.UserServiceImpl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>This provide Security configuration.
 * {@see WebSecurityConfigurerAdapter}</p>
 * <p>Provides the security of the files and operations by roles.</p>
 */
@Configuration
@EnableWebMvcSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * User Service.
   */
  @Autowired
  private UserServiceImpl serviceRepositoryUser;

  /**
   * The authentication process allows for the database.
   * @param registry AuthenticationManagerBuilder
   */
  @Override
  protected void configure(AuthenticationManagerBuilder registry) throws Exception {
    registry.userDetailsService(serviceRepositoryUser)
        .passwordEncoder(passwordEncoder());
  }

  /**
   * It controls the navigation in the website.
   * @param web WebSecurity
   * @throws Exception error
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web
        .ignoring()
        .antMatchers("/resources/**");
  }

  /**
   * Control to project access from urls.
   * It brings the user restrictions.
   * @param http HttpSecurity
   * @throws Exception error
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/", "/login", "/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
        .usernameParameter("username").passwordParameter("password")
        .defaultSuccessUrl("/welcome").failureHandler(new FailClassForLogin()).permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/403");
  }

  /**
   * User password encoding to BCrypt.
   * @return PasswordEncoder
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private class FailClassForLogin implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)
        throws IOException, ServletException {
      response.sendRedirect("/error");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }
  }
}
