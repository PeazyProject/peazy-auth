package com.peazy.auth.service.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peazy.auth.model.args.CreateCustomerUserRequest;
import com.peazy.auth.model.entity.CustomerUserEntity;
import com.peazy.auth.model.entity.SupplierUserEntity;
import com.peazy.auth.repository.CustomerUserRepository;
import com.peazy.auth.repository.SupplierUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerUserRepository customerUserRepository;

    @Autowired
    private SupplierUserRepository supplierUserRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerUserEntity> customerUserOptional = customerUserRepository.findByUserName(username);
        if (customerUserOptional.isPresent()) {
            return new User(customerUserOptional.get().getUserName(),
            customerUserOptional.get().getUserPassword(),
            new ArrayList<>());
        }
        Optional<SupplierUserEntity> supplierUserOptional = supplierUserRepository.findByUserName(username);
        if (supplierUserOptional.isPresent()) {
            return new User(supplierUserOptional.get().getUserName(), supplierUserOptional.get().getUserPassword(),
                    new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public UserDetails loadUserByUseremail(String userEmail) throws UsernameNotFoundException {

        Optional<CustomerUserEntity> customerUserOptional = customerUserRepository.findByUserEmail(userEmail);
        Optional<SupplierUserEntity> supplierUserOptional = supplierUserRepository.findByUserEmail(userEmail);

        if (customerUserOptional.isPresent()) {
            return new User(customerUserOptional.get().getUserAccount(),
                    customerUserOptional.get().getUserPassword(),
                    new ArrayList<>());
        }
        if (customerUserOptional.isPresent()) {
            return new User(supplierUserOptional.get().getUserAccount(), supplierUserOptional.get().getUserPassword(),
                    new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found with userEmail: " + userEmail);
    }

    public CustomerUserEntity save(CreateCustomerUserRequest request) {
        CustomerUserEntity newUser = new CustomerUserEntity();
        BeanUtils.copyProperties(request, newUser);
        newUser.setUserPassword(bcryptEncoder.encode(request.getUserPassword()));
        newUser.setLoginErrorCnt("0");
        newUser.setActivatedStatus("0");
        newUser.setIsFinishWholesale("0");
        newUser.setIsPaidDeposit("0");
        newUser.setCreateUser(request.getUserName());
        newUser.setUpdateUser(request.getUserName());
        newUser.setUpdateDt(request.getCreateDt());
        return customerUserRepository.save(newUser);
    }

    public boolean checkUserPassword(String password, String checkedPassword) {
        return bcryptEncoder.matches(password, checkedPassword);
    }

}
