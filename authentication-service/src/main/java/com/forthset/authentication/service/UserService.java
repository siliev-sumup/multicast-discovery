package com.forthset.authentication.service;

import com.forthset.authentication.rest.model.UserDto;
import com.forthset.authentication.service.impl.UserServiceImpl;
import com.forthset.authentication.domain.User;

/**
 * The interface for creating Snapcard users
 * @see UserServiceImpl
 */
public interface UserService {

  User createUser(UserDto userDto) throws Exception;
}
