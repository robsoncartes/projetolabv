package br.edu.fatecsjc.security;

import br.edu.fatecsjc.models.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.catalina.authenticator.SingleSignOnEntry;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        // method setAuth.. UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler()); //
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Account account = new ObjectMapper().readValue(request.getInputStream(), Account.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword(), new ArrayList<>());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        // super.successfulAuthentication(request, response, chain, authResult);

        String email = ((JWTAccount) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(email);
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        response.addHeader("Username", email);

        // Collection<String> headerNames = response.getHeaderNames();
        // System.err.println("HeadersNames: " + headerNames.toString());
        // System.err.println(response.getHeader("Username"));
    }

    private static class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

        /**
         * Called when an authentication attempt fails.
         *
         * @param request   the request during which the authentication attempt occurred.
         * @param response  the response.
         * @param exception the exception which was thrown to reject the authentication
         */
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {

            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
        }

        private String json() {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            return "{\"timestamp\": " + sdf.format(new Date().getTime()) + ", "
                    + "\"status\": 401, "
                    + "\"error\": \"Not authorized.\", "
                    + "\"message\": \"Authentication failed: Invalid email or password.\", "
                    + "\"path\": \"/login\"}";
        }
    }
}
