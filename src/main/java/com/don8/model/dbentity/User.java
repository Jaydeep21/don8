package com.don8.model.dbentity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "user", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User extends AuditModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="uid", insertable=true, updatable=true, unique=true, nullable=false)
    private Long uid;
    @Size(max = 100)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    private BigInteger phone;
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(max = 100)
    private String email;
    @Size(max = 100)
    private String profile_image;
    @NotBlank(message = "Password is mandatory")
    @Size(max = 200)
    private String password;
    @NotBlank(message = "Role is mandatory")
    @Size(max = 50)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid);
    }
}
