//package com.springmvc.config.security;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.StrictHttpFirewall;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = {"com.springmvc"})
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//
//    private final DataSource dataSource;
//
//    // Questo potrebbe rompersi
//    @Qualifier("persistentTokenRepository")
//    private final PersistentTokenRepository persistentTokenRepository;
//
//    public SecurityConfig(
//            UserDetailsService userDetailsService,
//            DataSource dataSource,
//            PersistentTokenRepository persistentTokenRepository
//    ) {
//        this.userDetailsService = userDetailsService;
//        this.dataSource = dataSource;
//        this.persistentTokenRepository = persistentTokenRepository;
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//
//        return authenticationProvider;
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//
//        firewall.setAllowUrlEncodedSlash(true);
//        firewall.setAllowSemicolon(true);
//
//        return firewall;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//
//        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService((userDetailsService())).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**").permitAll()
//                .antMatchers("/sign-in/**").permitAll()
//                .antMatchers("/").hasAnyRole("ANONYMOUS", "ADMIN")
//                .antMatchers(new String[]{
//                        "/admin"
//                }).access("hasRole('ADMIN')")
//                .antMatchers("/vehicles").hasRole("USER")
//                .and()
//                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .formLogin()
//                .loginPage("/sign-in")
//                .loginProcessingUrl("/sign-in?status=processing")
//                .failureUrl("/sign-in?status=error")
//                .usernameParameter("userId")
//                .passwordParameter("password")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/sign-in?status=forbidden")
//                .and()
//                .logout()
//                .logoutUrl("/sign-in?status=signed-out");
//    }
//
//    public AuthenticationFilter authenticationFilter() throws Exception {
//        AuthenticationFilter filter = new AuthenticationFilter();
//
//        filter.setAuthenticationManager(authenticationManagerBean());
//        filter.setAuthenticationFailureHandler(failureHandler());
//        filter.setAuthenticationSuccessHandler(successHandler());
//        filter.setRememberMeServices(rememberMeService());
//
//        return filter;
//    }
//
//    @Bean
//    public SimpleUrlAuthenticationFailureHandler failureHandler() {
//        return new SimpleUrlAuthenticationFailureHandler("/sign-in?status=forbidden");
//    }
//
//    @Bean
//    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
//        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
//        auth.setTargetUrlParameter("targetUrl");
//
//        return auth;
//    }
//
//    @Bean
//    public PersistentTokenBasedRememberMeServices rememberMeService() {
//        String key = "rememberMeKey";
//
//        PersistentTokenBasedRememberMeServices rememberMeServices = new RememberMeServices(key, userDetailsService, persistentTokenRepository);
//
//        rememberMeServices.setCookieDomain("rememberMe");
//        rememberMeServices.setTokenValiditySeconds(60 * 60 * 4);
//        rememberMeServices.setParameter("rememberMe");
//        rememberMeServices.setUseSecureCookie(false);
//
//        return rememberMeServices;
//    }
//
//    @Bean
//    PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setDataSource(dataSource);
//
//        return tokenRepository;
//    }
//}
