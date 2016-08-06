package tr.com.prolms.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * <p>Application configuration file. This configuration
 * file provide which property file to use.
 * The properties files are for developer machine,
 * test and product machine environments.
 * For example it can contain database properties.</p>
 * Look at this (file {@link environments}).
 * <p>This use application.properties file.</p>
 */
@Configuration
@ComponentScan(basePackages = {"tr.com.prolms.base.config"})
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class AppConfig {

  /**
   * Definitions properties files.
   * @return properties files
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Properties to support the 'dev' mode of operation.
   * Dev is your machine.
   */
  @Configuration
  @Profile({"default"})
  @PropertySource("classpath:environments/developer.properties")
  static class Dev {
    // Define additional beans for this profile here
  }

  /**
   * Properties to support the 'qa' mode of operation.
   * Qa is remote test machine.
   */
  @Configuration
  @Profile({"qa"})
  @PropertySource("classpath:environments/qa.properties")
  static class Qa {
    // Define additional beans for this profile here
  }

  /**
   * Properties to support the 'prod' mode of operation.
   * Prod is production machine.
   */
  @Configuration
  @Profile("product")
  @PropertySource("classpath:environments/product.properties")
  static class Product {
    // Define additional beans for this profile here
  }
}
