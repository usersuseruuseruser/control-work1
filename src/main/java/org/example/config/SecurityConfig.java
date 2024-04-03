package org.example.config;

import org.example.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.ModelAndView;


@Configuration
@EnableWebSecurity
@ComponentScan("org.example.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final org.example.security.AuthProvider authProvider;

    public SecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/Main", "/registration", "/sign_in", "/users/*","/test/*","/testResults","/searchUser","/forum").permitAll()
                .antMatchers("/edit","/users/*","/logout").hasAnyRole("USER");

        http.csrf().disable()
                .formLogin()
                .loginPage("/registration")
                .loginProcessingUrl("/registration")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile", true)
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/main")
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    new ModelAndView("exception", "message", accessDeniedException.getMessage());
                })
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60* 24 * 7);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
}
