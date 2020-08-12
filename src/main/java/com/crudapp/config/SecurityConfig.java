package com.crudapp.config;

import com.crudapp.security.SuccessfulLoginRedirect;
import com.crudapp.security.auth.SecureUserDaoService;
import com.crudapp.security.jwt.JwtConfig;
import com.crudapp.security.jwt.JwtTokenVerifier;
import com.crudapp.security.jwt.JwtUsernameAndPasswordFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.crudapp.security.roles.UserRoles.ADMIN;
import static com.crudapp.security.roles.UserRoles.USER;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
@Builder
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private SecureUserDaoService secureUserDaoService;
    private JwtConfig jwtConfig;
    private SecretKey secretKey;
    private PasswordEncoder passwordEncoder;
    private SuccessfulLoginRedirect successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .addFilter(new JwtUsernameAndPasswordFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordFilter.class)

                .authorizeRequests()

                .antMatchers("/","/*.js","/imgs/*").permitAll()
                .antMatchers( "/login", "/signUp").permitAll()
                .antMatchers("/accountPage", "/accountSettings").hasAnyRole(ADMIN.toString(),USER.toString())
                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/accountPage",true).permitAll()
//                .failureForwardUrl("/")
                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JWT");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean()
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(secureUserDaoService);
        return provider;
    }


}
