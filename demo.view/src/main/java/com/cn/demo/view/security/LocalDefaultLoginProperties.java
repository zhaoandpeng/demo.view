package com.cn.demo.view.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "default.security.login")
public class LocalDefaultLoginProperties {

	private  String  loginPage = "/";

	public String getLoginPage() { return loginPage; }

	public void setLoginPage(String loginPage) { this.loginPage = loginPage; }
	
}
