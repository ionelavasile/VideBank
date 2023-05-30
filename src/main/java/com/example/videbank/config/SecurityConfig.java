package com.example.videbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@Profile("profile1")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROL_USER = "USER";
    private static final String ROL_ADMIN = "ADMIN";
    private static final String ROL_POWER_USER = "POWER_USER";

    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public UserDetailsService userDetailsService(){

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("Elena")
                .password(bCryptPasswordEncoder().encode("12345"))
                .roles(ROL_USER)
                .build());

        manager.createUser(User.withUsername("Ionela")
                .password(bCryptPasswordEncoder().encode("1111"))
                .roles(ROL_ADMIN)
                .build());

        manager.createUser(User.withUsername("powerUser")
                .password(bCryptPasswordEncoder().encode("0000"))
                .roles(ROL_POWER_USER, ROL_USER, ROL_ADMIN)
                .build());

        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/").hasAnyRole("ROL_USER", "ROL_ADMIN")
                .antMatchers("/accounts/**").hasAnyRole("ROL_USER", "ROL_ADMIN")
                .antMatchers("/transactions/**").hasRole("ROL_ADMIN")
                .and()
                .formLogin().loginPage("/api/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .csrf().disable()
                .cors().disable();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}

