package com.atomikos.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.atomikos.jta.base.datasource.Databases1Config;
import com.atomikos.jta.base.datasource.Databases2Config;
import com.atomikos.util.ClassLoadingHelper;

@SpringBootApplication
@EnableConfigurationProperties(value = {Databases1Config.class, Databases2Config.class})
public class JtaApplication {

	public static void main(String[] args) {
		System.setProperty("com.atomikos.icatch.file",
				ClassLoadingHelper.loadResourceFromClasspath(JtaApplication.class, "transactions.properties").getPath());
		SpringApplication.run(JtaApplication.class, args);
	}
}
