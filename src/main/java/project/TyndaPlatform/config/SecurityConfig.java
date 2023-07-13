package project.TyndaPlatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.TyndaPlatform.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.exceptionHandling().accessDeniedPage("/403-error");

        AuthenticationManagerBuilder builder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        httpSecurity
                .formLogin()
                .loginPage("/login") // "/login" Controller page
                .loginProcessingUrl("/auth") // <form action="/auth" method="post">
                .usernameParameter("user_email") // <input type = "email" name = "user_email">
                .passwordParameter("user_password") // <input type = "password" name = "user_password">
                .defaultSuccessUrl("/") // response.sendRedirect("/profile")
                .failureUrl("/login?autherror");

        httpSecurity
                .logout()
                .logoutUrl("/log-out") // post request to /sign-out
                .logoutSuccessUrl("/login");

        httpSecurity.csrf().disable();

         return httpSecurity.build();
    }
}