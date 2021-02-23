package com.tms.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${user-login.path}")
    private String LOGIN_PATH;

    @Value("${user-details.path}")
    private String USER_DETAILS_PATH;

    @Value("${user-register.path}")
    private String SIGN_IN_PATH;

    @Value("${admin.path}")
    private String ADMIN_PATH;

    @Value("${users.actuator.path}")
    private String USERS_WS_ACTUATOR_PATH;

    @Value("${zuul.actuator.path}")
    private String ACTUATOR_PATH;

    @Autowired
    AuthorizationFilter authorizationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers(LOGIN_PATH).permitAll()
                .antMatchers(SIGN_IN_PATH).permitAll()
                .antMatchers(HttpMethod.GET, USER_DETAILS_PATH).hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, USER_DETAILS_PATH).hasAuthority("USER")
                .antMatchers(ADMIN_PATH).hasAuthority("ADMIN")
                .antMatchers(ACTUATOR_PATH).permitAll()
                .antMatchers(USERS_WS_ACTUATOR_PATH).permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                //TODO: tests this
                // .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .cors();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).collect(Collectors.toList()));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
//    @Bean
//    public ZuulHostFilter zuulHostFilter() {
//        return new ZuulHostFilter();
//    }
}
