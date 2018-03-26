package sha.work.dto.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import sha.work.enums.AuthorityType;

@Data
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String code;
	private String name;
	private String pw;
	private int valid;
	private int role;
	 
	@Enumerated(EnumType.STRING)
	private AuthorityType authorityType;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		List<GrantedAuthority> authorities = new ArrayList<>();
	    authorities.add(new SimpleGrantedAuthority(getAuthorityType().getRole()));
        
        return authorities;
	}
	
	private AuthorityType getAuthorityType() {
		if(role == AuthorityType.ROLE_USER.getId()) {
			return AuthorityType.ROLE_USER;
		}
		
		return AuthorityType.ROLE_ADMIN;
	}
	
	@Override
	public String getPassword() {
		return pw;
	}
	@Override
	public String getUsername() {
		return name;
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
	
	
}
