/*
 * package com.cn.demo.view.security;
 * 
 * import java.sql.SQLException;
 * 
 * import javax.annotation.Resource;
 * 
 * import org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.cn.demo.view.model.BaseUser; import
 * com.cn.demo.view.service.BaseUserService;
 * 
 * @Service public class LocalUserDetailsService implements UserDetailsService{
 * 
 * @Resource private BaseUserService baseUserService;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { BaseUser baseUser = null; try { baseUser =
 * this.baseUserService.get(BaseUser.class, username); if(null==baseUser) {
 * throw new NullPointerException("查无此用户!"); }
 * 
 * } catch (SQLException e) { e.printStackTrace(); } return new
 * LocalBaseUser(baseUser); }
 * 
 * }
 */