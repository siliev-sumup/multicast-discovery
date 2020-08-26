package com.forthset.authentication.domain.mapper;

import com.forthset.authentication.rest.model.UserDto;
import com.forthset.authentication.domain.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Mappings({
      @Mapping(source = "email", target = "email"),
      @Mapping(source = "firstName", target = "firstName"),
      @Mapping(source = "lastName", target = "lastName"),
      @Mapping(source = "phone", target = "phone"),
      @Mapping(target = "password", ignore=true)
  })
  public abstract UserDto userToModel(User user);

  public abstract User modelToUser(UserDto userDto);

  @AfterMapping
  void setPassword(UserDto userDto, @MappingTarget User user) {
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
  }
}
