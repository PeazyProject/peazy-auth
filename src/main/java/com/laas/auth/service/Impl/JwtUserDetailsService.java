package com.laas.auth.service.Impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.laas.auth.enumerate.UserErrorCodeEnumImpl;
import com.laas.auth.exception.ErrorCodeException;
import com.laas.auth.model.args.CreateUserRequest;
import com.laas.auth.model.entity.ADRedirectSettingEntity;
import com.laas.auth.model.entity.UserEntity;
import com.laas.auth.repository.ADRedirectSettingRepository;
import com.laas.auth.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ADRedirectSettingRepository adRedirectSettingRepository;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String loginAccount) throws UsernameNotFoundException {
		Optional<UserEntity> userOptional = userRepository.findByLoginAccount(loginAccount);
		if (userOptional.isPresent()) {
		    
		    if(Integer.compare(userOptional.get().getIsActivated(), 0) == 0) {
                throw new ErrorCodeException(UserErrorCodeEnumImpl.USER_IS_NOT_ACTIVATED);
            }
		    
			return new User(userOptional.get().getLoginAccount(),userOptional.get().getLoginSecretCode() ,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with loginAccount: " + loginAccount);
		}
	}
	 
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new User(userOptional.get().getLoginAccount(), userOptional.get().getLoginSecretCode(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
	
	
	public UserEntity save(CreateUserRequest user) {
		UserEntity newUser = new UserEntity();
		newUser.setLoginAccount(user.getLoginAccount());
		newUser.setLoginSecretCode(bcryptEncoder.encode(user.getLoginSecretCode()));
		newUser.setUserName(user.getUserName());
		newUser.setEmail(user.getEmail());
		newUser.setStatus("I");
		return userRepository.save(newUser);
	}
	
	public boolean checkUserPxd(String pxd,String loginSecretCode) {
		return bcryptEncoder.matches(pxd, loginSecretCode);
	}
	
	public String getRedirectUrlFromEmail(String emailSuffix) {
		Optional<ADRedirectSettingEntity> optional= adRedirectSettingRepository.findByEmailSuffix(emailSuffix);
		if(optional.isPresent()) {
			return optional.get().getRedirectUrl();
		}
		return "";
	}
}