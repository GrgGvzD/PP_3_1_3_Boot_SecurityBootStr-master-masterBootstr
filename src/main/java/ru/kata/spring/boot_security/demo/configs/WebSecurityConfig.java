package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.security.UserDetailsServiceImpl;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService userService;
//    private final PasswordEncoder passwordEncoder;



    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsServiceImpl userService/*, PasswordEncoder passwordEncoder*/) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
//                .antMatchers("/api/**").hasAuthority("ADMIN")
//                .antMatchers("/user", "/user/**").hasAnyAuthority("ADMIN", "USER")
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().successHandler(successUserHandler)///
//                .permitAll()///
//                .and()///
//                .logout()
//                .permitAll();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/admin/users/**").hasAuthority("ADMIN")
                .antMatchers("/api/user/**").hasAuthority("USER")
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                username -> Optional.of(userService.loadUserByUsername(username))
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"))
        ).passwordEncoder(getPasswordEncoder());
    }
}