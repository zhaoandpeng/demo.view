package com.cn.demo.view.security;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.cn.demo.view.service.impl.BaseUserServiceImpl;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter  {
	
	
	@Resource
	private BaseUserServiceImpl baseUserImpl;
	
	@Resource
	private LocalAuthenticationSuccessHandler localAuthenticationSuccessHandler;

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
//				.defaultSuccessUrl("/main")
				.successHandler(localAuthenticationSuccessHandler)
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
	 
	 
	 @Bean
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
