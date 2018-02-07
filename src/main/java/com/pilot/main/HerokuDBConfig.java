package com.pilot.main;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "herokuEntityManagerFactory",
    transactionManagerRef = "herokuTransactionManager", basePackages = {"com.pilot.main.herokurepo.repo"})
public class  HerokuDBConfig{

  @Bean(name = "herokuDataSource")
  @ConfigurationProperties(prefix = "postgres.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "herokuEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean herokuEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("herokuDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.pilot.main.herokurepo.domain").persistenceUnit("pilot_fuel_industry_stats")
        .build();
  }

  @Bean(name = "herokuTransactionManager")
  public PlatformTransactionManager herokuTransactionManager(
      @Qualifier("herokuEntityManagerFactory") EntityManagerFactory herokuEntityManagerFactory) {
    return new JpaTransactionManager(herokuEntityManagerFactory);
  }

}
