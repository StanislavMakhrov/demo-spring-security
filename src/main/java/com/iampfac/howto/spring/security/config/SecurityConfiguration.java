package com.iampfac.howto.spring.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.iampfac.howto.spring.security.jdbc.CustomJdbcUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends GlobalMethodSecurityConfiguration {

	private DataSource dataSource;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		CustomJdbcUserDetailsService customJdbcUserDetailsService = new CustomJdbcUserDetailsService();
		customJdbcUserDetailsService.setDataSource(dataSource);

		DaoAuthenticationProvider customJdbcProvider = new DaoAuthenticationProvider();
		customJdbcProvider.setUserDetailsService(customJdbcUserDetailsService);

		auth.jdbcAuthentication().dataSource(dataSource);
		auth.inMemoryAuthentication().withUser("memdemo").password("secret").roles("USER").and().withUser("memadmin").password("53cr37").roles("ADMIN");
		auth.authenticationProvider(customJdbcProvider);
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
