package com.peazy.auth.service.impl;

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
        Optional<UserEntity> userOptional = userRepository.findByName(username);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getName(),
            userOptional.get().getPassword(),
            new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getEmail(),
            userOptional.get().getPassword(),
            new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with user email: " + email);
    }

    public UserEntity save(CreateUserRequest request) {
        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(request, newUser);
        newUser.setPassword(bcryptEncoder.encode(request.getUserPassword()));
        newUser.setCreateUser(request.getUserName());
        newUser.setUpdateUser(request.getUserName());

        newUser.setLoginErrorCnt("0");
        newUser.setActivatedStatus("0");
        newUser.setIsFinishWholesale("N");
        newUser.setIsPaidDeposit("N");
        newUser.setIsInvoice("N");

        Date currentDateTime = new Date();
        newUser.setCreateDt(currentDateTime);
        newUser.setUpdateDt(currentDateTime);
        return userRepository.save(newUser);
    }

    public boolean checkUserPassword(String password, String checkedPassword) {
        return bcryptEncoder.matches(password, checkedPassword);
    }

}
