package br.edu.fatecsjc.security;

import br.edu.fatecsjc.models.enums.AuthorityName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTAccount implements UserDetails {

    private Long id;
    private String email;
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public JWTAccount() {
    }

    public JWTAccount(Long id, String email, String password, Set<AuthorityName> authorityNames) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorityNames.stream().map(authorityName -> new SimpleGrantedAuthority(authorityName.getDescription())).collect(Collectors.toSet());
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasHole(AuthorityName authorityName) {

        return this.getAuthorities().contains(new SimpleGrantedAuthority(authorityName.getDescription()));
    }
}
