package com.ips42.fieldservice.service;

import org.springframework.stereotype.Service;

@Service
public class TenantService {

    public String getCurrentTenantId() {
        // todo: get from context
        return "1";
    }
}
