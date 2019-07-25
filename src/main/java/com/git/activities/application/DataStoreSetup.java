/*package com.git.activities.application;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource; 
 
 
 @Configuration 
 @EnableJpaRepositories(basePackages = { "com.git.activities.*" }) 
 @ComponentScan(value = "com.git.activities.*") 
 @EntityScan(basePackages = { "com.git.activities.*" }) 

public class DataStoreSetup {

	@Value("${spring.datasource.url}")
	String databaseUrl;

	@Value("${spring.datasource.username}")
	String databaseUser;

	@Value("${spring.datasource.password}")
	String databasePassword;

	@Value("${spring.datasource.driver-class-name}")
	String databaseDriver;


	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(databaseDriver);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(databaseUser);
		dataSource.setPassword(databasePassword);
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		//entityManagerFactoryBean.setPackagesToScan("com.deltadental.platform.entity");
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", mariaDB53Dialect);
		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		return entityManagerFactoryBean;
	}

} 
*/