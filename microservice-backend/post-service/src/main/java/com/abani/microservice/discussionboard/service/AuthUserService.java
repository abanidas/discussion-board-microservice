package com.abani.microservice.discussionboard.service;

import java.util.Map;

public interface AuthUserService {

    Map<Object, Object> findAuthUserByUsername(String username);
}
