package be.bstorm.technifutur_java23_demospringsecurity.models.entities;

import be.bstorm.technifutur_java23_demospringsecurity.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Gamer implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gamer_id", nullable = false)
    private Long id;

    @Column(name = "gamer_pseudo", nullable = false, unique = true)
    private String pseudo;

    @Column(name = "gamer_email", nullable = false, unique = true)
    private String email;

    @Column(name = "gamer_password", nullable = false)
    private String password;

    @Column(name = "gamer_birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "gamer_active", nullable = false)
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return this.pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
