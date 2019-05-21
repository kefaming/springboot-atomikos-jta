package com.atomikos.jta.base.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sqlserver.datasource.databases1")
public class Databases1Config {

	private String url;
	private String username;
	private String password;
	private int port;
	private String database;

	/** min-pool-size 最小连接数 **/
	private int minPoolSize;
	/** max-pool-size 最大连接数 **/
	private int maxPoolSize;
	/** max-lifetime 连接最大存活时间 **/
	private int maxLifetime;
	/** borrow-connection-timeout 获取连接失败重新获等待最大时间，在这个时间内如果有可用连接，将返回 **/
	private int borrowConnectionTimeout;
	/** login-timeout java数据库连接池，最大可等待获取datasouce的时间 **/
	private int loginTimeout;
	/** maintenance-interval 连接回收时间 **/
	private int maintenanceInterval;
	/** max-idle-time 最大闲置时间，超过最小连接池连接的连接将将关闭 **/
	private int maxIdleTime;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getMaxLifetime() {
		return maxLifetime;
	}

	public void setMaxLifetime(int maxLifetime) {
		this.maxLifetime = maxLifetime;
	}

	public int getBorrowConnectionTimeout() {
		return borrowConnectionTimeout;
	}

	public void setBorrowConnectionTimeout(int borrowConnectionTimeout) {
		this.borrowConnectionTimeout = borrowConnectionTimeout;
	}

	public int getLoginTimeout() {
		return loginTimeout;
	}

	public void setLoginTimeout(int loginTimeout) {
		this.loginTimeout = loginTimeout;
	}

	public int getMaintenanceInterval() {
		return maintenanceInterval;
	}

	public void setMaintenanceInterval(int maintenanceInterval) {
		this.maintenanceInterval = maintenanceInterval;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}
}
