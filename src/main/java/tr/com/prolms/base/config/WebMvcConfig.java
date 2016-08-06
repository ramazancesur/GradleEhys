package tr.com.prolms.base.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * It provides all the features of MVC.
 * This configuration scans controller package
 * and service package.
 */
@Configuration
@ComponentScan(basePackages =
    {
        "tr.com.prolms.base.controller",
        "tr.com.prolms.base.service"
    })
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  private final String utf8 = "UTF-8";

  /**
   * This indicates Resources file where get it.
   * @param registry ResourceHandlerRegistry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }

  /**
   * Configure Default Servlet Handling.
   * @param configurer DefaultServletHandlerConfigurer
   */
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * Configure MessageSource.
   * @return MessageSource
   */
  @Bean(name = "messageSource")
  public MessageSource configureMessageSource() {
    ReloadableResourceBundleMessageSource messageSource = new
        ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages/messages");
    messageSource.setDefaultEncoding(utf8);
    return messageSource;
  }

  /**
   * Control mapping error.
   * @return SimpleMappingExceptionResolver
   */
  @Bean
  public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
    SimpleMappingExceptionResolver simpleMappingExceptionResolver =
        new SimpleMappingExceptionResolver();

    Properties properties = new Properties();
    properties.put(DefaultHandlerExceptionResolver.PAGE_NOT_FOUND_LOG_CATEGORY, "/404");
    properties.put("org.springframework.dao.DataAccessException", "error");
    simpleMappingExceptionResolver.setExceptionMappings(properties);
    return simpleMappingExceptionResolver;
  }

  /**
   * Added InterceptorRegistry.
   * @param registry InterceptorRegistry
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    HandlerInterceptorAdapter handlerInterceptorAdapter = new HandlerInterceptorAdapter() {

      @Override
      public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                               Object handler) throws Exception {
        request.setCharacterEncoding(utf8);
        response.setCharacterEncoding(utf8);
        return true;
      }
    };
    registry.addInterceptor(handlerInterceptorAdapter);
    registry.addInterceptor(localeChangeInterceptor());
  }

  /**
   * Internationalization.
   * @return LocaleChangeInterceptor
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor =
        new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    return localeChangeInterceptor;
  }

  /**
   * Internationalization.
   * @return ControllerClassNameHandlerMapping
   */
  @Bean
  public ControllerClassNameHandlerMapping controllerClassNameHandlerMapping() {
    ControllerClassNameHandlerMapping controllerClassNameHandlerMapping =
        new ControllerClassNameHandlerMapping();
    Object[] objects = new Object[]{localeChangeInterceptor()};
    controllerClassNameHandlerMapping.setInterceptors(objects);
    return controllerClassNameHandlerMapping;
  }

  /**
   * Set datas on Session.
   * @return SessionLocaleResolver
   */
  @Bean
  public SessionLocaleResolver localeResolver() {
    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("tr"));
    return sessionLocaleResolver;
  }

  /**
   * For Negotiation.
   * @param configurer config
   */
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.ignoreAcceptHeader(true).defaultContentType(
        MediaType.TEXT_HTML);
  }
}