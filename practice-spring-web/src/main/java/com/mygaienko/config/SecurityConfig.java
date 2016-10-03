package com.mygaienko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import java.util.ArrayList;

/**
 * Created by dmygaenko on 03/10/2016.
 *
 * http://docs.spring.io/spring-security/site/docs/current/reference/html/session-mgmt.html
 * http://keylesson.com/index.php/2016/02/24/spring-security-concurrency-control-example-2665/
 * https://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
               /* .ldapAuthentication()*/
               /* .jdbcAuthentication()*/
                .withUser("user").password("password").roles("USER");
    }

    //TODO http config
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/signup","/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login.jsf")
                    .successHandler(authenticationSuccessHandler())
                    .failureHandler(authenticationFailureHandler())
                    .permitAll()
                .and()
                .addFilter(authenticationFilter())

        ;
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() {
        UsernamePasswordAuthenticationFilter authenticationFilter = new UsernamePasswordAuthenticationFilter();
        authenticationFilter.setSessionAuthenticationStrategy(sas());
        //authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        return authenticationFilter;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login.jsf");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/welcome.jsf");
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login.jsf");
    }

    @Bean
    public ConcurrentSessionFilter concurrencyFilter() {
        return new ConcurrentSessionFilter(sessionRegistry(), "/session-expired.jsf");
    }

    @Bean
    public SessionAuthenticationStrategy sas() {
        return new CompositeSessionAuthenticationStrategy(sessionAuthenticationStrategies());
    }

    @Bean
    public ArrayList<SessionAuthenticationStrategy> sessionAuthenticationStrategies() {
        ArrayList<SessionAuthenticationStrategy> strategies = new ArrayList<SessionAuthenticationStrategy>();
        strategies.add(concurrentSessionControlAuthenticationStrategy());
        strategies.add(new SessionFixationProtectionStrategy());
        strategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry()));
        return strategies;
    }

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
        ConcurrentSessionControlAuthenticationStrategy strategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        strategy.setMaximumSessions(1);
        strategy.setExceptionIfMaximumExceeded(true);
        return strategy;
    }


    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
