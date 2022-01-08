/**
 * 
 */
package com.jmuscles.datasource.demo;

import java.util.List;
import java.util.Map;

import com.jmuscles.datasource.DataSourceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author manish goel
 *
 */
@Component
public class SQLQueryExecutor {

	@Autowired
	private DataSourceGenerator dataSourceGenerator;

	@Autowired
	private ExecutorConfigProperties configProperties;

	public int write(Map<String, Object> queryParams, String sqlKey) {
		ExecutorSQLQueryConfig queryConfig = configProperties.getSqlQueries_Write().get(sqlKey);
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				dataSourceGenerator.get(queryConfig.getDskey()));
		return jdbcTemplate.update(queryConfig.getQuery(), queryParams);
	}

	public List<User> read(String sqlKey) {
		ExecutorSQLQueryConfig queryConfig = configProperties.getSqlQueries_Read().get(sqlKey);
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				dataSourceGenerator.get(queryConfig.getDskey()));
		List<User> users = jdbcTemplate.query(queryConfig.getQuery(), new BeanPropertyRowMapper(User.class));
		return users;
	}

}
