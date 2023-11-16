package com.example.securingweb.entity;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User implements UserDetails{
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 45)
    private String name;
    
    @Column(nullable = false, unique = true, length = 45)
    private String email;
     
    @Column(nullable = false, length = 64)
    private String password;

    // https://stackoverflow.com/questions/197045/setting-default-values-for-columns-in-jpa
    private String role = "USER";
    
    
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
     
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    @Override
    public String getPassword() {
        return this.password;
    }
 
    @Override
    public String getUsername() {
        return this.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
    // getters and setters are not shown   

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		System.out.println("esto no es llamado en este caso pero si editara el rol de un usuario seguramente seria llamado");
		this.role = role;
	}
    
}