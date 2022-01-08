/**
 * 
 */
package com.jmuscles.datasource.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author manish goel
 *
 */
@Configuration
@ConfigurationProperties(value = "executors-config")
public class ExecutorConfigProperties {

	private Map<String, ExecutorSQLQueryConfig> sqlQueries_Write = new HashMap<>();
	private Map<String, ExecutorSQLQueryConfig> sqlQueries_Read = new HashMap<>();

	public Map<String, ExecutorSQLQueryConfig> getSqlQueries_Write() {
		return sqlQueries_Write;
	}

	public void setSqlQueries_Write(Map<String, ExecutorSQLQueryConfig> sqlQueries_Write) {
		this.sqlQueries_Write = sqlQueries_Write;
	}

	public Map<String, ExecutorSQLQueryConfig> getSqlQueries_Read() {
		return sqlQueries_Read;
	}

	public void setSqlQueries_Read(Map<String, ExecutorSQLQueryConfig> sqlQueries_Read) {
		this.sqlQueries_Read = sqlQueries_Read;
	}

}
