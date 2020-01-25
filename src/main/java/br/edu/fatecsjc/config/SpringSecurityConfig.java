package br.edu.fatecsjc.config;

import br.edu.fatecsjc.security.JWTAuthenticationFilter;
import br.edu.fatecsjc.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Qualifier("JWTAccountDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/accounts/**",
            "/exams/**",
            "/login/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test"))
            http.headers().frameOptions().disable();

        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();

        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource getCorsConfigurationSource() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
