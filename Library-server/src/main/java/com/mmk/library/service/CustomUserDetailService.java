package com.mmk.library.service;

import com.mmk.library.entity.User;
import com.mmk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public static  Map<String,String> USER_ROLES = new HashMap<>();


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null && !(email.equalsIgnoreCase("admin123@gmail.com"))) {
            throw  new UsernameNotFoundException("No User Found");
        }
        else if (user == null && email.equalsIgnoreCase("admin123@gmail.com")){

            User adminUser = new User();

            adminUser.setFirstName("Admin");
            adminUser.setLastName("MK");
            adminUser.setEmail(email);
            adminUser.setRole("ADMIN");
            adminUser.setMember("ADMIN");
            adminUser.setMemberId("A001");
            adminUser.setPassword("Welcome01");

            user = userService.createUser(adminUser);

        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(List.of(user.getRole()))
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority>  authorities = new ArrayList<>();

        for(String role: roles) {
            USER_ROLES.put("role",role);
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
}
