package com.sahu.webtech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.sahu.webtech.service.impl.CustomOAuth2UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private CustomOAuth2UserServiceImpl customOAuth2UserServiceImpl;
	
	@Autowired
	private OAuthLoginSuccessHandler  authLoginSuccessHandler;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/", "/login", "/registration", "/forget-password").permitAll()
					.anyRequest().authenticated()
				)
				.formLogin(form -> 
					form.loginPage("/login")
					.failureUrl("/login?error")
					.usernameParameter("username")
					.passwordParameter("password")
					.defaultSuccessUrl("/client/user/dashboard", true)
				)
				.oauth2Login(oauth ->
					oauth.loginPage("/login")
					.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserServiceImpl))
					.successHandler(authLoginSuccessHandler)
				)
				.csrf(csrf -> csrf.disable())
				.logout(logout -> 
					logout.logoutUrl("/logout")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/login?logout")
				) 
				.exceptionHandling(exc -> exc.accessDeniedPage("/access-denied"))
				.sessionManagement(session-> 
					session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
					.invalidSessionUrl("/invalid-session")
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false)
					.expiredUrl("/login?session-expire")
				);
			
		return http.build();
	}
	
}
