package com.curso.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.domains.Users;
import com.curso.repositories.UsersRepository;
import com.curso.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsersRepository userRepository;

    public UserDetailServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new UserSS(user.orElse(null));
    }

}
