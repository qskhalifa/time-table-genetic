package io.qusay;

import io.qusay.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // Removes default policies
@EnableGlobalMethodSecurity(prePostEnabled = true) // Enables @Pre/@Post annotations for method-level security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SpringDataJpaUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(User.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().anyRequest().permitAll();

                // Basic login!
                // FUTURE: Use more advanced login. 2FA?
                http.httpBasic()
                .and()

                // FUTURE: Do NOT disallow CSRF requests. This is just for making CURL/Postman access easy for testing
                .csrf().disable()

                .logout().logoutSuccessUrl("/");
    }
}
