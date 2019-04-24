package com.cn.demo.view.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cn.demo.view.annotation.TableInfoAnnotation;

/**
 * @author Mr.Zhao
 * @date 2019-03-28
 */
@TableInfoAnnotation(tableName = "base_user", primaryKey = "id")
public class BaseUser implements Serializable, UserDetails{
	
	private static final long serialVersionUID = -828643532655725840L;

	private String id;

    private String username;

    private String password;

    private String address;

    private String mobile;

    private String tel;

    private String sex;

    private String birthday;

    private String headImg;

    private Integer age;
    
	@SuppressWarnings("rawtypes")
	private List role;
	
	private List<BaseMenu> roleResources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

	@Override
	@SuppressWarnings("unchecked")
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return role;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("rawtypes")
	public void setRole(List role) {
		this.role = role;
	}

	public List<BaseMenu> getRoleResources() {
		return roleResources;
	}

	public void setRoleResources(List<BaseMenu> roleResources) {
		this.roleResources = roleResources;
	}
	
	
}