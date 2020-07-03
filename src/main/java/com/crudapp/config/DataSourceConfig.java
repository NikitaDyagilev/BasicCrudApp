package com.crudapp.config;

import java.sql.Driver;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:application.properties")
@EntityScan(basePackages = "com.crudapp.model")
@EnableJpaRepositories(basePackages = {"com.crudapp.repository"})
@ComponentScan(basePackages = {"com.crudapp"})
public class DataSourceConfig {

	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Bean
	public DataSource dataSource() {
		try {
			SimpleDriverDataSource db = new SimpleDriverDataSource();
			Class<? extends Driver> driverClass =
					(Class<? extends Driver>) Class.forName(driverClassName);
			db.setDriverClass(driverClass);
			db.setUsername(username);
			db.setPassword(password);
			db.setUrl(url);
			return db;
		} catch (Exception e) {
			System.out.println("There was an error when trying to create  ");
			return null;
		}
	}

	@Bean
	public PlatformTransactionManager transactionManager(){
		return new JpaTransactionManager(entityManagerFactory());
	}

	@Bean
	public JpaVendorAdapter jtaTransactionAdapter(){
		return new HibernateJpaVendorAdapter();
	}

	public Properties jpaHibernateProps(){
		Properties prop = new Properties();
		prop.put("hibernate.hbm2ddl.auto", "none");
		return prop;
	}


	@Bean("entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf =
				new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("com.crudapp.model");
		emf.setJpaProperties(jpaHibernateProps());
		emf.setJpaVendorAdapter(jtaTransactionAdapter());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPersistenceUnitName("emf");
		emf.afterPropertiesSet();
		return emf.getNativeEntityManagerFactory();
	}

}