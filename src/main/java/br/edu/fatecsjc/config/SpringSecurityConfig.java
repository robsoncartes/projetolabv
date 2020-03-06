package br.edu.fatecsjc.config;

import br.edu.fatecsjc.security.JWTAuthenticationFilter;
import br.edu.fatecsjc.security.JWTAuthorizationFilter;
import br.edu.fatecsjc.security.JWTUtil;
import br.edu.fatecsjc.services.exceptions.JWTAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Creates an instance with the default configuration enabled.
     */

    @Autowired
    private Environment environment;

    // @Qualifier("JWTAccountDetailsService")
    @Autowired
    private JWTAccountDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
    };

    public static final String[] PUBLIC_MATCHERS_GET = {
            "/activities/**",
            "/exams/**",
            "/questions/**",
            "/choices/**",
            "/answers/**"
    };

    public static final String[] PUBLIC_MATCHERS_POST = {
            "/accounts/**",
            "/login/**"
    };

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test"))
            http.headers().frameOptions().disable();

        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .anyRequest().authenticated();

        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
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
