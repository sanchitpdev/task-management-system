package com.sanchitp.dev.task.management.system.security.util;

import com.sanchitp.dev.task.management.system.security.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static CustomUserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()){
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails){
            return (CustomUserDetails) principal;
        }

        return null;
    }
}
