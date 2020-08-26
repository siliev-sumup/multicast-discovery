package com.forthset.authentication.service.impl;

import com.forthset.authentication.repository.UserRepository;
import com.forthset.authentication.rest.model.UserDto;
import com.forthset.authentication.domain.User;
import com.forthset.authentication.domain.mapper.UserMapper;
import com.forthset.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public User createUser(UserDto userDto) {
    return userRepository.save(userMapper.modelToUser(userDto));
  }
}
