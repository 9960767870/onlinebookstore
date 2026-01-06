package com.onlinebookstore.Services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinebookstore.Services.UserService;
import com.onlinebookstore.entity.*;
import com.onlinebookstore.exceptionhandling.CustomException;
import com.onlinebookstore.mapper.UserMapper;
import com.onlinebookstore.repository.RoleRepository;
import com.onlinebookstore.repository.UserRepository;
import com.onlinebookstore.security.BcrypPasswordEncoder;
import com.onlinebookstore.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BcrypPasswordEncoder bcrypPasswordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public AuthRequestResponse login(AuthRequest authRequest) {
        //decript username and password
       // String decodedUserName = jwtUtil.decodeBase64String(authRequest.getUserName());
        String decodedPassword = jwtUtil.decodeBase64String(authRequest.getPassword());
        Optional<User> byUserNameAndMail = userRepository.
                findByUserNameOrEmail(authRequest.getUserName(), authRequest.getEmail());
        AuthRequestResponse authRequestResponse = new AuthRequestResponse();
         if(bcrypPasswordEncoder.passwordEncoder().matches(decodedPassword,byUserNameAndMail.get().getPassword())){
             authRequestResponse  = getUserFromRequest(byUserNameAndMail.get(),authRequestResponse);
         }else{
             new CustomException("Invalid username or password");
         }
    return authRequestResponse;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) throws CustomException {

        Role role = roleRepository
                .findByRoleName(userDTO.getRole().getRoleName())
                .orElseThrow(() -> new CustomException("Role not found"));
        if(role!=null) {
            User user = new User();
            user.setUserName(userDTO.getUserName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(bcrypPasswordEncoder.passwordEncoder().encode(userDTO.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user.setRole(role);
            User save = userRepository.save(user);
            return userMapper.toDTO(save);
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> all = userRepository.findAll();
        return userMapper.toDTO(all);
    }

    @Override
    public UserDTO getUserById(int userId) throws CustomException {

        User byId = userRepository.findById(userId)
                .orElseThrow(()->new CustomException("User not found"));
        return userMapper.toDTO(byId);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws CustomException {
        Role byRoleName = roleRepository
                .findByRoleName(userDTO.getRole().getRoleName())
                .orElseThrow(() -> new CustomException("Role not found"));
        User byUserNameAndEmail = userRepository
                .findByUserNameOrEmail(userDTO.getUserName(), userDTO.getEmail())
                .orElseThrow(()->new CustomException("User not found"));
        byUserNameAndEmail.setUserName(userDTO.getUserName());
        byUserNameAndEmail.setEmail(userDTO.getEmail());
        byUserNameAndEmail.setPassword(bcrypPasswordEncoder.passwordEncoder().encode(userDTO.getPassword()));
        byUserNameAndEmail.setCreatedAt(LocalDateTime.now());
        byUserNameAndEmail.setRole(byRoleName);
        User save = userRepository.save(byUserNameAndEmail);
        return userMapper.toDTO(save);
    }

    @Override
    public String deleteUser(int userId) {
        userRepository.deleteById(userId);
        return "User Deleted Successfully";
    }

    @Override
    public String forgetPassword(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map map = objectMapper.readValue(data, Map.class);
            String userName = map.get("userName").toString();
            String email = map.get("email").toString();
            String password = map.get("password").toString();
            Optional<User> byUserNameAndEmail = userRepository.findByUserNameOrEmail(userName, email);
            byUserNameAndEmail.ifPresent(u -> {
                u.setUserName(userName);
                u.setEmail(email);
                u.setPassword(bcrypPasswordEncoder.passwordEncoder().encode(password));
                User save = userRepository.save(u);
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "User Updated Successfully";
    }

    private AuthRequestResponse getUserFromRequest(User user,AuthRequestResponse authRequest) {
        String token = jwtUtil.generateToken(user);
        authRequest.setUserName(user.getUserName());
        authRequest.setPassword(user.getPassword());
        authRequest.setEmail(user.getEmail());
        authRequest.setToken(token);
        return authRequest;
    }

}
