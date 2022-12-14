package com.peazy.auth.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.args.CreateUserRequest;
import com.peazy.auth.model.entity.UserEntity;
import com.peazy.auth.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByUserName(username);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getUserName(),
            userOptional.get().getUserPassword(),
            new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public UserDetails loadUserByUseremail(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByUserEmail(userEmail);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getUserAccount(),
            userOptional.get().getUserPassword(),
            new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with userEmail: " + userEmail);
    }

    public UserEntity save(CreateUserRequest request) {
        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(request, newUser);
        newUser.setUserPassword(bcryptEncoder.encode(request.getUserPassword()));
        newUser.setCreateUser(request.getUserName());
        newUser.setUpdateUser(request.getUserName());
        Date currentDateTime = new Date();
        newUser.setCreateDt(currentDateTime);
        newUser.setUpdateDt(currentDateTime);
        return userRepository.save(newUser);
    }

    public boolean checkUserPassword(String password, String checkedPassword) {
        return bcryptEncoder.matches(password, checkedPassword);
    }

}
