package com.forthset.authentication.rest;

import com.forthset.authentication.rest.model.UserDto;
import com.forthset.authentication.service.UserService;
import com.forthset.authentication.domain.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @Valid final UserDto userDto)
      throws Exception {
    return ResponseEntity.ok(
            userMapper.userToModel(
                    userService.createUser(userDto)
            )
    );
  }
}
