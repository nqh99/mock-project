package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.demo.service.UserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Bean
	public AuthenticationSuccessHandler successLoginHandler() {
		LoginSuccessHandler loginHandler = new LoginSuccessHandler();
		loginHandler.setAlwaysUseDefaultTargetUrl(true);
		loginHandler.setDefaultTargetUrl("/dashboard");
		System.out.println("Modified authentication success handler.");
		return loginHandler;
	}
	
	@Bean
	public AuthenticationFailureHandler failureLoginHandler() {
		LoginFailureHandler loginFailurehandler = new LoginFailureHandler();
		System.out.println("Modified authentication failure handler.");
		return loginFailurehandler;
	}
		
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/favicon.ico", "/favicon", "/images/*","/assets/**","/assets/*");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/register","/login").permitAll()
		//Phan nay tam thoi chua lam, khi nao du cac controller minh se lam phan quyen cuoi cung
			.antMatchers("/add_product").hasAuthority("ROLE_ADMIN")
			.anyRequest().authenticated()
			.and().formLogin()
			.loginPage("/login").loginProcessingUrl("/login")
			.usernameParameter("userName").passwordParameter("password")
			.defaultSuccessUrl("/view_product", true)
			.failureHandler(failureLoginHandler())
			.successHandler(successLoginHandler())
			.permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login")
			.permitAll();
	}
}
