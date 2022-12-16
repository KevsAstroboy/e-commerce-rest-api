package com.proj.ecommerce.service;

import com.proj.ecommerce.model.User;

public interface IAuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
