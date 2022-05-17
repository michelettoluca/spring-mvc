//package com.springmvc.config.security;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class RememberMeServices extends PersistentTokenBasedRememberMeServices {
//    public RememberMeServices(String key, UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
//        super(key, userDetailsService, persistentTokenRepository);
//    }
//
//    @Override
//    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
//        String isRegularLogin = request.getParameter("isRegularLogin");
//
//        if (isRegularLogin != null && isRegularLogin.equals("true")) {
//            return super.rememberMeRequested(request, parameter);
//        } else {
//            return true;
//        }
//    }
//}
