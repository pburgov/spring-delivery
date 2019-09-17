package com.pburgov.springdelivery.config;

import com.pburgov.springdelivery.dao.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity( securedEnabled = true, prePostEnabled = true )
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginSuccessHandler successHandler;
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SpringSecurityConfig(
        LoginSuccessHandler successHandler, UserService userService,
        BCryptPasswordEncoder passwordEncoder ) {
        this.successHandler = successHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/customers/list").hasAnyRole("ADMIN")
                .antMatchers("/**", "/css/**", "/js/**", "/images/**", "/fotos/**", "/repartos/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .defaultSuccessUrl("/repartos/list")
                .permitAll()
            .and()
                .logout()
                .permitAll()
            .and()
               .exceptionHandling().accessDeniedPage("/error_403");

    }

    @Autowired
    public void configurerGlobal( AuthenticationManagerBuilder build ) throws Exception {

        build.userDetailsService(userService).passwordEncoder(passwordEncoder);

    }
}
