package edu.mns.game.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.sql.DataSource;
import java.util.List;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceMnsGame userDetailsServiceMnsGame;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceMnsGame).passwordEncoder(getPasswordEncoder());
    }


    @Bean
    public CorsConfigurationSource configurationCrossOrigin(){
        CorsConfiguration myConfig = new CorsConfiguration();

        myConfig.setAllowedOrigins(List.of("*"));
        myConfig.setAllowedHeaders(List.of("HEAD","GET", "POST", "PUT", "DELETE", "PATCH"));
        myConfig.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", myConfig);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");

        /*http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()*/
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/connection", "/disconnection","/inscription").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER","CREATOR")
                .antMatchers("/creator/**").hasRole("CREATOR")
                .antMatchers("/admin/**","/user/**","/creator/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}