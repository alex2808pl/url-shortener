package de.telran.urlshortener.security;

import de.telran.urlshortener.security.entity.UserEntity;
import de.telran.urlshortener.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        List<UserEntity> users = userRepository.findByLogin(username);
//        if (users==null || users.isEmpty()) {
//            throw new UsernameNotFoundException("User with login '" + username + "' not found");
//        }
//
//        UserEntity user = users.get(0);
//
//        Set<String> mySet = Set.of(user.getRoles().split(","));
//        UserDetails userDetails = User.withUsername(username)
//                .username(user.getLogin())
//                .password(user.getPassword())
//                .authorities(getAuthorities(user.getRoles()))
//                .build();
//        return userDetails;
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//            role.getAuthorities().forEach(authority ->
//                    authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()))
//            );
//        }
//        return authorities;
//    }
//}
