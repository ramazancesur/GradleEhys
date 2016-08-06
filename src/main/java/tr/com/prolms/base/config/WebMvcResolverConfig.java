package tr.com.prolms.base.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import tr.com.prolms.base.entity.Role;
import tr.com.prolms.base.entity.User;
import tr.com.prolms.base.viewresolver.ExcelViewResolver;
import tr.com.prolms.base.viewresolver.JsonViewResolver;
import tr.com.prolms.base.viewresolver.PdfViewResolver;
import tr.com.prolms.base.viewresolver.XmlViewResolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This provides resolver configuration for MVC.
 * <p>You can set view path and view extension by ServletContextTemplateResolver.</p>
 * It include difference view resolver type. For
 * example {@link #thymeleafViewResolver},{@link #contentNegotiatingViewResolver}
 * {@link #jaxb2MarshallingXmlViewResolver},{@link #jsonViewResolver} and etc...
 */
@Configuration
public class WebMvcResolverConfig {

  private final String utf8 = "UTF-8";

  /**
   * Set Resolve pages.
   * @return ServletContextTemplateResolver
   */
  @Bean
  public ServletContextTemplateResolver templateResolver() {
    ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".html");
    resolver.setTemplateMode("HTML5");
    resolver.setCharacterEncoding(utf8);
    resolver.setCacheable(true);
    return resolver;
  }

  /**
   * For SpringTemplate.
   * @return SpringTemplateEngine
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    Set<IDialect> additionalDialects = new HashSet<>();
    additionalDialects.add(new SpringSecurityDialect());
    additionalDialects.add(new LayoutDialect());
    engine.setAdditionalDialects(additionalDialects);
    engine.setTemplateResolver(templateResolver());
    return engine;
  }

  /**
   * Config Thymeleaf View Resolver.
   * @return ViewResolver
   */
  @Bean
  public ViewResolver thymeleafViewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    viewResolver.setViewNames(new String[]{"*"});
    viewResolver.setCache(false);
    viewResolver.setCharacterEncoding(utf8);
    return viewResolver;
  }

  /**
   * Configure ContentNegotiatingViewResolver.
   * @param manager ContentNegotiationManager
   * @return All ViewResolver
   */
  @Bean
  public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setContentNegotiationManager(manager);

    // Define all possible view resolvers
    List<ViewResolver> resolvers = new ArrayList<>();

    resolvers.add(jaxb2MarshallingXmlViewResolver());
    resolvers.add(jsonViewResolver());
    resolvers.add(thymeleafViewResolver());
    resolvers.add(pdfViewResolver());
    resolvers.add(excelViewResolver());

    resolver.setViewResolvers(resolvers);
    return resolver;
  }

  /**
   * Configure View resolver to provide XML output Uses JAXB2 marshaller.
   * @return XmlViewResolver
   */
  @Bean
  public ViewResolver jaxb2MarshallingXmlViewResolver() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(User.class, Role.class);
    return new XmlViewResolver(marshaller);
  }

  /**
   * Configure View resolver to provide JSON output using JACKSON.
   * @return JsonViewResolver
   */
  @Bean
  public ViewResolver jsonViewResolver() {
    return new JsonViewResolver();
  }

  /**
   * Configure View resolver to provide PDF output using lowagie.
   * @return PdfViewResolver
   */
  @Bean
  public ViewResolver pdfViewResolver() {
    return new PdfViewResolver();
  }

  /**
   * Configure View resolver to provide XLS output using Apache POI.
   * @return ExcelViewResolver
   */
  @Bean
  public ViewResolver excelViewResolver() {
    return new ExcelViewResolver();
  }


}