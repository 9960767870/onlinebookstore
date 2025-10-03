package com.onlinebookstore.Services;

import com.onlinebookstore.entity.AuthRequest;
import com.onlinebookstore.entity.AuthRequestResponse;
import com.onlinebookstore.entity.User;
import com.onlinebookstore.entity.UserDTO;
import com.onlinebookstore.exceptionhandling.CustomException;

import java.util.List;


public interface UserService {

    AuthRequestResponse login(AuthRequest authRequest);

    UserDTO createUser(UserDTO userDTO) throws CustomException;

    List<UserDTO> getAllUser();

    UserDTO getUserById(int userId) throws CustomException;

    UserDTO updateUser(UserDTO userDTO) throws CustomException;

    String deleteUser(int userId);

    String forgetPassword(String data);
}
