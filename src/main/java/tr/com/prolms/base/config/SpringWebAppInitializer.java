package tr.com.prolms.base.config;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import tr.com.prolms.base.util.PropertiesLoader;

import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This is the first running class of project.
 * When the project starts up, this class initial for the all configuration.
 * <p> You can see all configuration classes for what.
 * For example AppConfig class for RootConfig. </p>
 * You can specify servlet mapping of your project.
 */
public class SpringWebAppInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer {

  private static final PropertiesLoader propertiesLoader = new PropertiesLoader();

  /**
   * web.xml java based configuration.
   * @param servletContext ServletContext
   * @throws ServletException ServletException
   */
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    WebApplicationContext context = getContext();
    ContextLoaderListener listener = new ContextLoaderListener(context);
    servletContext.addListener(listener);

    setJavaMelodyConfiguration(servletContext);

    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet",
        new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/*");
  }

  /**
   * setJavaMelodyConfiguration.
   * @param servletContext ServletContext
   */
  private void setJavaMelodyConfiguration(ServletContext servletContext) {
    Properties properties = propertiesLoader.load("application.properties");

    String melodyLog = properties.getProperty("melody.log");
    String melodyDisabled = properties.getProperty("melody.disabled");
    String melodyPath = properties.getProperty("melody.monitoring-path");
    String melodyDirectory = properties.getProperty("melody.storage-directory");
    String melodySeconds = properties.getProperty("melody.resolution-seconds");
    String melodyUser = properties.getProperty("melody.username");
    String melodyPass = properties.getProperty("melody.password");

    servletContext.setInitParameter("javamelody.log", melodyLog);
    servletContext.setInitParameter("javamelody.disabled", melodyDisabled);
    servletContext.setInitParameter("javamelody.monitoring-path", melodyPath);
    servletContext.setInitParameter("javamelody.storage-directory", melodyDirectory);
    servletContext.setInitParameter("javamelody.resolution-seconds", melodySeconds);
    servletContext.setInitParameter("javamelody.authorized-users", melodyUser + ":" + melodyPass);
  }

  /**
   * get Context.
   * @return AnnotationConfigWebApplicationContext
   */
  private AnnotationConfigWebApplicationContext getContext() {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.setConfigLocation("tr.com.prolms.base");
    return context;
  }

  /**
   * RootConfigClasses from AppConfig.class.
   * @return AppConfig class
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  /**
   * ServletConfigClasses from WebMvcConfig.class.
   * @return WebMvcConfig class
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{WebMvcConfig.class};
  }

  /**
   * Get Servlet Mappings.
   * @return String[]
   */
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

}