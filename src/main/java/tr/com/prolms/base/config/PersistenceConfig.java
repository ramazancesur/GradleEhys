package tr.com.prolms.base.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;
import javax.sql.DataSource;

/**
 * <p>Persistence configuration is for
 * database configuration. This configuration
 * provides (DataSource {@link javax.sql.DataSource}),
 * JpaTransactionManager and LocalContainerEntityManagerFactoryBean.</p>
 * <p>This file scan entity package for models.</p>
 * <p>This file active repository package for Jpa Repositories.</p>
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"tr.com.prolms.base.entity"})
@EnableJpaRepositories(basePackages = {"tr.com.prolms.base.repository"})
public class PersistenceConfig {

  private final String propertyNameHibernateDialect = "hibernate.dialect";
  private final String propertyNameHibernateShowSql = "hibernate.show_sql";
  private final String propertyNameHibernateHbm2DdlAuto = "hibernate.hbm2ddl.auto";
  private final String propertyNameHibernateFormatSql = "hibernate.format_sql";
  private final String propertyNameHibernateNamingStrategy = "spring.jpa.hibernate.naming_strategy";

  /**
   * Environment obj.
   */
  @Autowired
  private Environment env;

  /**
   * Database url.
   */
  @Value("${db.url}")
  private String dbUrl;

  /**
   * Database class name.
   */
  @Value("${db.driverClassName}")
  private String dbDriverClassName;

  /**
   * Database username.
   */
  @Value("${db.username}")
  private String dbUserName;

  /**
   * Database password.
   */
  @Value("${db.password}")
  private String dbPassword;


  /**
   * Transaction Manager.
   * @return JpaTransactionManager
   */
  @Bean
  JpaTransactionManager transactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return jpaTransactionManager;
  }

  /**
   * Set EntityManager and Hibernate config.
   * @return LocalContainerEntityManagerFactoryBean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

    factory.setDataSource(dataSource());
    factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    factory.setPackagesToScan("tr.com.prolms.base.entity");

    Properties jpaProperties = new Properties();
    jpaProperties.put(propertyNameHibernateDialect,
        env.getProperty(propertyNameHibernateDialect));
    jpaProperties.put(propertyNameHibernateShowSql,
        env.getProperty(propertyNameHibernateShowSql));
    jpaProperties.put(propertyNameHibernateHbm2DdlAuto,
        env.getProperty(propertyNameHibernateHbm2DdlAuto));
    jpaProperties.put(propertyNameHibernateFormatSql,
        env.getProperty(propertyNameHibernateFormatSql));
    jpaProperties.put(propertyNameHibernateNamingStrategy,
        env.getProperty(propertyNameHibernateNamingStrategy));

    factory.setJpaProperties(jpaProperties);

    return factory;
  }

  /**
   * Hibernate Exception Translator.
   * @return HibernateExceptionTranslator
   */
  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }

  /**
   * Database configuration.
   * @return DataSource
   */
  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(dbDriverClassName);
    dataSource.setUrl(dbUrl);
    dataSource.setUsername(dbUserName);
    dataSource.setPassword(dbPassword);
    return dataSource;
  }

}
