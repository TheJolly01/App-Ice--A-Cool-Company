package com.example.icetime.iceTimeApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public static PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf().disable()
                                .authorizeHttpRequests(
                                                (authorize) -> authorize.requestMatchers("/register/**").permitAll()
                                                                .requestMatchers("/").permitAll()
                                                                .requestMatchers("/team").permitAll()
                                                                .requestMatchers("/error").permitAll()
                                                                .requestMatchers("/resources/**", "/static/**",
                                                                                "/css/**", "/img/**",
                                                                                "/js/**")
                                                                .permitAll()
                                                                .requestMatchers("/day/**").authenticated()
                                                                .requestMatchers("/events/**").permitAll()
                                                                .requestMatchers("/todos/**").authenticated())
                                .formLogin(
                                                form -> form
                                                                .loginPage("/login")
                                                                .loginProcessingUrl("/login")
                                                                .defaultSuccessUrl("/day", true)
                                                                .permitAll())
                                .logout(
                                                logout -> logout
                                                                .logoutUrl("/logout")
                                                                .logoutSuccessUrl("/")
                                                                .permitAll());
                return http.build();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(passwordEncoder());
        }

}
