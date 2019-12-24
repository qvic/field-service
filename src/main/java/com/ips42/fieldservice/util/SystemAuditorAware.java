package com.ips42.fieldservice.util;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SystemAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(System.getProperty("user.name"));
    }
}
