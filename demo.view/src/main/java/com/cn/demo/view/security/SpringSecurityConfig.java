package com.cn.demo.view.security;
import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cn.demo.view.service.impl.BaseUserImpl;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	
	@Resource
	private BaseUserImpl baseUserImpl;

	@Resource
	private LocalDefaultLoginProperties localDefaultLoginProperties;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/image/**","/css/**","/layui*/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage(localDefaultLoginProperties.getLoginPage())
				.loginProcessingUrl("/login")
				.failureUrl(localDefaultLoginProperties.getLoginPage())
				.defaultSuccessUrl("/main")
				.permitAll();
	}

	 @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	      auth
//	          .inMemoryAuthentication().passwordEncoder(new LocalPasswordEncoder())//.withUser("user").password("password").roles("USER").and()
//	          .withUser("admin").password("123456").roles("USER");
		  auth.userDetailsService(baseUserImpl).passwordEncoder(new BCryptPasswordEncoder());	
		  
//		  auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("");
	 }
	 
	 
	 @Bean
	 public PasswordEncoder passwordEncoder(){
	        
		 return  new BCryptPasswordEncoder();
	 } 
	 
}
