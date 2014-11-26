		package org.cloudfoundry.samples.music.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Profile("cloud")
@Configuration
public class CloudDataSourceConfig extends AbstractCloudConfig {

	public static final String DEFAULT_DB_MAX_POOL_SIZE = "10";
	
	public static final String DEFAULT_DB_MAX_WAIT_TIME = "200";
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		
		int maxPoolSize = 
				Integer.parseInt(env.getProperty("DB_MAX_POOL_SIZE", DEFAULT_DB_MAX_POOL_SIZE));
		int maxWaitTime = 
				Integer.parseInt(env.getProperty("DB_MAX_WAIT_TIME", DEFAULT_DB_MAX_WAIT_TIME));

		PooledServiceConnectorConfig.PoolConfig poolConfig =
	            new PooledServiceConnectorConfig.PoolConfig(maxPoolSize, maxWaitTime);

	    DataSourceConfig.ConnectionConfig connectionConfig =
	            new DataSourceConfig.ConnectionConfig("characterEncoding=UTF-8");

		return connectionFactory().dataSource(new DataSourceConfig(poolConfig, connectionConfig));

	}
	
}