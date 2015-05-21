package com.swg.base.backend.service;

import org.springframework.stereotype.Service;

@Service(value = "securityService")
public class SecurityServiceMock implements SecurityService {
    @Override
    public Boolean isLoggedIn() {
        return true;
    }
}