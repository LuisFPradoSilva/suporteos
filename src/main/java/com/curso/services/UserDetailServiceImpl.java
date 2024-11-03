package com.curso.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.curso.domains.Person;
import com.curso.repositories.PersonRepository;
import com.curso.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    //private final UsersRepository userRepository;
    private final PersonRepository personRepository;

    //public UserDetailServiceImpl(UsersRepository userRepository) {
    public UserDetailServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new UserSS(user.orElse(null));
    }

}
