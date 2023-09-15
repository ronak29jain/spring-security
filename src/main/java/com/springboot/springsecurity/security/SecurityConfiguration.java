package com.springboot.springsecurity.security;

import com.springboot.springsecurity.entity.UserMongo;
import com.springboot.springsecurity.filter.JwtRequestFilter;
import com.springboot.springsecurity.repository.UserMongoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfiguration {

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    UserMongoRepository userMongoRepository;

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/code/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/dashboard").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                        .requestMatchers("/api/mongo/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sendEmail").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/mongo/register").hasRole("SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/register").hasRole("SUPERADMIN")
                        .requestMatchers("/manage", "/users").hasAnyRole("SUPERADMIN", "ADMIN")
                        .requestMatchers("/dashboard", "/profile").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                        .requestMatchers("/", "/home", "/forgotpassword", "/changepassword", "/confirm/{token}").permitAll()
                        .anyRequest().authenticated()
                ).exceptionHandling()
//			.and().sessionManagement()
//          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .formLogin(Customizer.withDefaults())
                .oauth2Login((form) -> form
                        .permitAll()
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                                Authentication authentication) throws IOException, ServletException {
                                // run custom logics upon successful login
                                System.out.println("this is working");
                                String redirectUrl = null;
                                if (authentication.getPrincipal() instanceof DefaultOAuth2User) {

                                    System.out.println("if statement is working");
                                    DefaultOAuth2User userDetails = (DefaultOAuth2User) authentication.getPrincipal();

                                    String userEmail = userDetails.getAttribute("email") != null ?
                                            userDetails.getAttribute("email") : userDetails.getAttribute("login") + "@github.com";
                                    String userFirstName = userDetails.getAttribute("given_name") != null ?
                                            userDetails.getAttribute("given_name") : userDetails.getAttribute("login");
                                    String userLastName = userDetails.getAttribute("family_name") != null ?
                                            userDetails.getAttribute("family_name") : "";

                                    System.out.println(userMongoRepository.findByEmail(userEmail));

                                    if (userMongoRepository.findByEmail(userEmail).isEmpty()) {
                                        UserMongo user = new UserMongo();

                                        user.setFirstName(userFirstName);
                                        user.setLastName(userLastName);
                                        user.setEmail(userEmail);
                                        user.setRole("ROLE_USER");
                                        user.setStatus("Active");

                                        String randompasswordString = com.springboot.springsecurity.util.PassyUtil.generatePassayPassword();
                                        String password = passwordEncoder().encode(randompasswordString);
                                        user.setPassword(password);

                                        userMongoRepository.save(user);
                                    }
                                }
                                redirectUrl = "/dashboard";
                                new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
                            }
                        })
                );
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
