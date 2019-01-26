package com.mutitenantmanager.configure.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class DataSourceFactory {

	@Bean
	@Primary
	public DataSource dataSource() {
		AbstractRoutingDataSource dataSource = new AuthoringDataSourceRouting();
		Map<Object, Object> targetDataSources = new HashMap();
		targetDataSources.put(1L, getOracle());
		targetDataSources.put(2L, getPostgres());
		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(getPostgres());
		dataSource.afterPropertiesSet();
		return dataSource;
	}

	public DataSource getOracle() {
		DataSourceBuilder datasource = DataSourceBuilder.create(this.getClass().getClassLoader());
		datasource.driverClassName("org.postgresql.Driver");
		datasource.url("jdbc:postgresql://localhost:5431/postgres");
		datasource.password("mysecretpassword");
		datasource.username("postgres");
		return datasource.build();
	}

	public DataSource getPostgres() {
		DataSourceBuilder datasource = DataSourceBuilder.create(this.getClass().getClassLoader());
		datasource.driverClassName("org.postgresql.Driver");
		datasource.url("jdbc:postgresql://localhost:5432/postgres");
		datasource.password("postgres");
		datasource.username("postgres");
		return datasource.build();
	}
}
