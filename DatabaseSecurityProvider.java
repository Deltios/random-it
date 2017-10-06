package com.journaldev.security;

import com.journaldev.hibernate.data.User;
import com.journaldev.session.UserSession;
import com.journaldev.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by JUZAU33 on 8/08/2017.
 */
@Service
public class DatabaseSecurityProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.authenticateUser(username, password);

        if (user == null) {
            throw new BadCredentialsException("Invalid username or password!");
        }

        Collection<GrantedAuthority> authorityCollection = new ArrayList<>();
        authorityCollection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "USER";
            }
        });

        userSession.setUser(user);

        return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), authorityCollection);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
