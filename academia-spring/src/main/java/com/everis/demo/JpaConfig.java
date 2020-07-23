/**
 * 
 */
package com.everis.demo;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Isaquiel Fernandes
 *
 */

@Configuration
@EnableTransactionManagement
public class JpaConfig {
	
	@Bean 
	public DataSource getDataSource() {
		DataSourceBuilder<?> dataSourceBuilder =  DataSourceBuilder.create();
		
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/inditex?useTimezone=true&amp;serverTimezone=UTC");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
		
		return dataSourceBuilder.build();
	}

	@Bean
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emt) {
		return new JpaTransactionManager(emt);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainer = new LocalContainerEntityManagerFactoryBean();
		
		localContainer.setPersistenceUnitName("Inditex-PU");
		localContainer.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		return localContainer;
	}
}
