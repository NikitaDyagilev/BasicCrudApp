package com.crudapp.crudapp.DBConfig;

import java.sql.Driver;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration("dataConnection")
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
     
     @Value("${spring.datasource.name}")
     private String username;
     @Value("${spring.datasource.password}")
     private String password;
     @Value("${spring.datasource.url}")
     private String url;
     @Value("${spring.datasource.driverclassname}")
     private String driverClassName;
     
     @Bean
     public DataSource dataSource(){
          try{
               SimpleDriverDataSource db = 
               new SimpleDriverDataSource();
               Class<? extends Driver> driverClass = 
               (Class<? extends Driver>) Class.forName(driverClassName);
               db.setDriverClass(driverClass);
               db.setUsername(username);
               db.setPassword(password);
               db.setUrl(url);
               return db;
               } catch(Exception e){
                    System.out.println("There was an error when ");
                    return  null;
               }
     }

     

     @Bean
     public Properties jpaProperties(){
          Properties jpaProp = new Properties();
          jpaProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
          jpaProp.put("hibernate.format_sql",true );
          jpaProp.put("hibernate.user_sql_comments", true);
          jpaProp.put("hibernate.show_sql", true);
          jpaProp.put("hibernate.max_fetch_depth", 3);
          jpaProp.put("hibernate.jdbc.batch_size", 10);
          jpaProp.put("hibernate.jdbc.fetch_size", 50);
          jpaProp.put("jpa.database-platform","org.hibernate.dialect.MySQL8Dialect");
          jpaProp.put("jpa.generate-ddl", "true");
          jpaProp.put("jpa.hibernate.ddl-auto", "update");
          jpaProp.put("jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStandardImpl");
          jpaProp.put("jpa.properties.hibernate.globally_quoted_identifiers", "true");
          jpaProp.put("jpa.properties.hibernate.globally_quoted_identifiers_skip_column_definitions", "true");
          jpaProp.put("jpa.properties.hibernate.jdbc.time_zone", "UTC");
          jpaProp.put("jpa.show-sql", "false");
          return jpaProp;
     }

     @Bean
     public EntityManagerFactory entityManagerFactory(){
          LocalContainerEntityManagerFactoryBean emf = new
               LocalContainerEntityManagerFactoryBean();
          emf.setDataSource(dataSource());
          emf.setJpaProperties(jpaProperties());
          emf.setPackagesToScan("../model/Account.java");
          emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
          emf.afterPropertiesSet();
          return emf.getObject();
     }

}