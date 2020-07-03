package com.crudapp.security.auth;

import com.crudapp.model.User;
import com.crudapp.repository.JpaUserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@AllArgsConstructor
@Service
public class SecureUserDaoService implements UserDetailsService {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Qualifier("jpaUserRepositoryImpl")
    private final JpaUserRepository jpaRepo;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = jpaRepo.findByUsername(username)
                    .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found",username)));
        } catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("Username not found");
        } catch (Exception e){
            throw new Exception("There was a problem when trying to reach a user with that username ",e);
        }
        SecureUser secureUser = UserToSecureUser.userToSecureUser(user);
        return secureUser;
    }
}
