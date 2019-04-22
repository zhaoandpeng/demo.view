/*
 * package com.cn.demo.view.security;
 * 
 * import java.io.Serializable; import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.cn.demo.view.model.BaseUser;
 * 
 * @SuppressWarnings("serial") public class LocalBaseUser implements
 * UserDetails,Serializable{
 * 
 * 
 * @SuppressWarnings("unused") private BaseUser baseUser;
 * 
 * public LocalBaseUser(BaseUser baseUser) {
 * 
 * this.baseUser = baseUser; }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() { //
 * TODO Auto-generated method stub return null; }
 * 
 * @Override public String getPassword() { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override public String getUsername() { // TODO Auto-generated method stub
 * return null; }
 * 
 * @Override public boolean isAccountNonExpired() { // TODO Auto-generated
 * method stub return false; }
 * 
 * @Override public boolean isAccountNonLocked() { // TODO Auto-generated method
 * stub return false; }
 * 
 * @Override public boolean isCredentialsNonExpired() { // TODO Auto-generated
 * method stub return false; }
 * 
 * @Override public boolean isEnabled() { // TODO Auto-generated method stub
 * return false; }
 * 
 * }
 */