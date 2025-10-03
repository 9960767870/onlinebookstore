package com.onlinebookstore.mapper;

import com.onlinebookstore.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.role", target = "role")
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDTO(List<User> user);
}
