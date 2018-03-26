package sha.work.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import sha.work.common.UrlConstants;
import sha.work.enums.AuthorityType;
import sha.work.service.domain.UserService;

@Configuration
public class ShaLotoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static String[] DEFAULT_PERMIT_URL_PATTERN = 
			new String[] {
					UrlConstants.INDEX, 
					UrlConstants.JS_ALL,
					UrlConstants.CSS_ALL,
					UrlConstants.IMG_ALL,
					UrlConstants.LOTO_ALL};
	
	private static String[] ADMIN_PERMIT_URL_PATTERN = 
			new String[] {
					UrlConstants.ADMIN_ALL, 
					UrlConstants.USER_ALL};
	
	private static String[] USER_PERMIT_URL_PATTERN = 
			new String[] {UrlConstants.USER_ALL};

    @Autowired
    private HttpAccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private UserService userService;

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
					.antMatchers(DEFAULT_PERMIT_URL_PATTERN).permitAll()
					.antMatchers(ADMIN_PERMIT_URL_PATTERN).hasAnyRole(AuthorityType.ROLE_ADMIN.getName())
					.antMatchers(USER_PERMIT_URL_PATTERN).hasAnyRole(AuthorityType.ROLE_USER.getName())
					.anyRequest().authenticated()
                .and()
                .formLogin()
					.loginPage(UrlConstants.USER_LOGIN)
					.successForwardUrl(UrlConstants.USER_LOGIN_SUCC)
					.permitAll()
					.and()
                .logout()
                	.invalidateHttpSession(true)
                	.clearAuthentication(true)
                	.logoutRequestMatcher(new AntPathRequestMatcher(UrlConstants.LOGOUT))
					.permitAll()
					.and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    // create two users, admin and user
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService);
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles(AuthorityType.ROLE_USER.getName())
                .and()
                .withUser("admin").password("password").roles(AuthorityType.ROLE_ADMIN.getName());
    }
}

