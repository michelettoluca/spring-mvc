package com.springmvc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("customUserDetailsService")
    private final UserDetailsService userDetailsService;
    @Qualifier("customPasswordEncoder")
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String[] publicPages = new String[]{"/"};
        final String[] authenticatedPages = new String[]{"/profile/**", "/vehicles/**", "/reservations/**"};
        final String[] adminPages = new String[]{"/admin/**"};
//        final String[] customerPages = new String[]{"/reservations/**"};

        http.authorizeRequests()
                .antMatchers(publicPages).permitAll()
                .antMatchers(authenticatedPages).authenticated()
//                .antMatchers(customerPages).access("hasRole('CUSTOMER')")
                .antMatchers(adminPages).access("hasRole('ADMIN')")
                .and().formLogin().loginPage("/sign-in")
                .usernameParameter("username").passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/sign-in?status=error")
                .and().csrf();
    }
}