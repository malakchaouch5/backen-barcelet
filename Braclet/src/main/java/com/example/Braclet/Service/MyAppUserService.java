package com.example.Braclet.Service;

import java.util.Collections;

import com.example.Braclet.Controlleur.MyAppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MyAppUserService implements UserDetailsService {

    private final MyAppUserRepository repository;

    @Autowired
    public MyAppUserService(MyAppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return repository.findByUsername(usernameOrEmail)
                .or(() -> repository.findByEmail(usernameOrEmail))
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(Collections.emptyList()) // No roles for now
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + usernameOrEmail));
    }

}
