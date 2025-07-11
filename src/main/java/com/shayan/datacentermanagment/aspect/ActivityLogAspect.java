package com.shayan.datacentermanagment.aspect;


import com.shayan.datacentermanagment.model.ActivityLog;
import com.shayan.datacentermanagment.model.User;
import com.shayan.datacentermanagment.reposiory.ActivityLogRepo;
import com.shayan.datacentermanagment.reposiory.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class ActivityLogAspect {

    private final ActivityLogRepo activityLogRepository;
    private final UserRepository userRepository;


    @Around("@annotation(logActivity)")
    public Object logAction(ProceedingJoinPoint joinPoint, LogActivity logActivity) throws Throwable {
        Object result = null;
        Throwable error = null;

        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable t) {
            error = t;
            throw t;
        } finally {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();

            String action = logActivity.value().isBlank()
                    ? String.format("Called method %s.%s", className, methodName)
                    : logActivity.value();

            User currentUser = getCurrentUser();

            assert currentUser != null;
            ActivityLog logEntry = ActivityLog.builder()
                    .id(currentUser.getId())
                    .action(action)
                    .timestamp(LocalDateTime.now())
                    .user(currentUser)
                    .build();

            activityLogRepository.save(logEntry);

            log.info("User {} performed action: {}", currentUser.getUsername(), action);
        }
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) return null;

        Object principal = authentication.getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User userDetails) {
            return userRepository.findByUsername(userDetails.getUsername());
        }

        return null;
    }



}
