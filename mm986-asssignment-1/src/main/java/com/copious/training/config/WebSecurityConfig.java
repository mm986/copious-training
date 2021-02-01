package com.copious.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserDetailsService userDetailsService;

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected static final String[] AUTH_WHITELIST = {
			"/assignment1/v2/api-docs",
			"/assignment1/swagger/resources",
			"/assignment1/swagger/resources/**",
			"/assignment1/configuration/ui",
			"/assignment1/configuration/security",
			"/assignment1/swagger-ui.html",
			"/assignment1/swagger.json",
			"/assignment1/web0jars/**",
			"/assignment1/webjars/**",
			"/assignment1/authenticate"
	};

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.headers()
					.frameOptions().deny()
					.cacheControl().disable()
					.httpStrictTransportSecurity()
					.and()
				.xssProtection()
					.block(false);
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(AUTH_WHITELIST);
	}
}
