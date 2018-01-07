package com.github.it89.investordaybook.security;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AppUserService appUserService;
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		//AppUser appUser = appUserService.findByLogin(ssoId);
		//TODO: !!!
        AppUser appUser = new AppUser();
        appUser.setLogin("test");
        appUser.setPassword("test");
		if(appUser == null){
			throw new UsernameNotFoundException("User not found");
		}
			return new org.springframework.security.core.userdetails.User(appUser.getLogin(), appUser.getPassword(),
				 true, true, true, true, getGrantedAuthorities(appUser));
	}

    private List<GrantedAuthority> getGrantedAuthorities(AppUser appUser){
		// TODO: Create roles
        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
