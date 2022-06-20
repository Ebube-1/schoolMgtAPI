package com.example.learning.service.serviceImpl;

import com.example.learning.entity.Teacher;
import com.example.learning.exception.UserNotFoundException;
import com.example.learning.repository.TeachersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final TeachersRepository teachersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teachersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Student does not exist"));
        return new org.springframework.security.core.userdetails.User(teacher.getEmail(),
                teacher.getPassword(), mapRolesToAuthorities(teacher.getRoles()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<com.springboot.blog.entity.Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
