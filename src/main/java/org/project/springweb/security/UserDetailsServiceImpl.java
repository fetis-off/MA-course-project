package org.project.springweb.security;

import lombok.RequiredArgsConstructor;
import org.project.springweb.exception.EntityNotFoundException;
import org.project.springweb.repository.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(
               () -> new EntityNotFoundException("Couldn't load user by email: "
                       + email)
       );
    }
}
