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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.atomikos.jta.base.datasource.Databases1Config;
import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.atomikos.jta.dao.databases1", sqlSessionTemplateRef = "databases1SqlSessionTemplate")
public class Databases1MyBatisConfig {

	// 配置数据源
	@Primary
	@Bean(name = "databases1DataSource")
	public DataSource databases1DataSource(Databases1Config databases1Config) throws SQLException {
		// MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		// SQLServerXADataSource sqlServerXADataSource = new SQLServerXADataSource();
		// sqlServerXADataSource.setURL(databases1Config.getUrl());
		// sqlServerXADataSource.setPinGlobalTxToPhysicalConnection(true);
		// sqlServerXADataSource.setPassword(databases1Config.getPassword());
		// sqlServerXADataSource.setUser(databases1Config.getUsername());
		// sqlServerXADataSource.setPinGlobalTxToPhysicalConnection(true);

		// SQLServerXADataSource ds = new SQLServerXADataSource();
		// ds.setUser("root");
		// ds.setPassword("123456");
		// ds.setServerName("192.168.229.133");
		// ds.setPortNumber(1433);
		// ds.setDatabaseName("message");

		SQLServerXADataSource ds = new SQLServerXADataSource();
		ds.setServerName(databases1Config.getUrl());
		ds.setPortNumber(databases1Config.getPort());
		ds.setDatabaseName(databases1Config.getDatabase());
		ds.setUser(databases1Config.getUsername());
		ds.setPassword(databases1Config.getPassword());
		// XAConnection xaCon = ds.getXAConnection();

		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(ds);
		xaDataSource.setUniqueResourceName("databases1DataSource");
		//
		xaDataSource.setMinPoolSize(databases1Config.getMinPoolSize());
		xaDataSource.setMaxPoolSize(databases1Config.getMaxPoolSize());
		xaDataSource.setMaxLifetime(databases1Config.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(databases1Config.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(databases1Config.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(databases1Config.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(databases1Config.getMaxIdleTime());
		return xaDataSource;
	}

	@Bean(name = "databases1SqlSessionFactory")
	public SqlSessionFactory databases1SqlSessionFactory(@Qualifier("databases1DataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/databases1/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "databases1SqlSessionTemplate")
	public SqlSessionTemplate databases1SqlSessionTemplate(
			@Qualifier("databases1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
