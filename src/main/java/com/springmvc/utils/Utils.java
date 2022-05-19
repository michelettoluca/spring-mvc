package com.springmvc.utils;

import com.springmvc.type.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String getAuthenticatedUserUsername() {
        String username = null;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }

    public static List<UserRole> getAuthenticatedUserRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<UserRole> roles = new ArrayList<>();
        if (principal instanceof UserDetails) {
            GrantedAuthority[] tmpRoles = ((UserDetails) principal).getAuthorities().toArray(new GrantedAuthority[0]);

            for (GrantedAuthority role : tmpRoles) {
                roles.add(UserRole.valueOf(role.toString().substring(5)));
            }
        }

        return roles;
    }
}
