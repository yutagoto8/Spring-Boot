package jp.co.cybermissions.itspj.java.learningwebapplication.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.cybermissions.itspj.java.learningwebapplication.Repository.LoginUserRepository;
import jp.co.cybermissions.itspj.java.learningwebapplication.models.LoginUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginUserRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = rep.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        return createUserDetails(user);
    }

    public User createUserDetails(LoginUser user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
}
