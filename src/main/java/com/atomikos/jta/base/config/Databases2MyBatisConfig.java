package com.atomikos.jta.base.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.atomikos.jta.base.datasource.Databases2Config;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;


@Configuration
@MapperScan(basePackages = "com.atomikos.jta.dao.databases2", sqlSessionTemplateRef = "databases2SqlSessionTemplate")
public class Databases2MyBatisConfig {
	
	@Bean(name = "databases2DataSource")
	public DataSource databases2DataSource(Databases2Config databases2Config) throws SQLException {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(databases2Config.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(databases2Config.getPassword());
		mysqlXaDataSource.setUser(databases2Config.getUsername());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName("databases2DataSource");

		xaDataSource.setMinPoolSize(databases2Config.getMinPoolSize());
		xaDataSource.setMaxPoolSize(databases2Config.getMaxPoolSize());
		xaDataSource.setMaxLifetime(databases2Config.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(databases2Config.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(databases2Config.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(databases2Config.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(databases2Config.getMaxIdleTime());
		return xaDataSource;
	}

	@Bean(name = "databases2SqlSessionFactory")
	public SqlSessionFactory databases2SqlSessionFactory(@Qualifier("databases2DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/databases2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "databases2SqlSessionTemplate")
	public SqlSessionTemplate databases2SqlSessionTemplate(
			@Qualifier("databases2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
