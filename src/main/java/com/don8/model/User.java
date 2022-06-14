package com.don8.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    BigInteger uid;
    @Size(max = 100)
    @NotBlank(message = "Name is mandatory")
    String name;
    @Size(max = 1000)
    String aids;
    @NotNull
    BigInteger phone;
    @Email
    @NotBlank(message = "Email is mandatory")
    @Size(max = 100)
    String email;
    @Size(max = 100)
    String profile_image;
    @NotBlank(message = "Password is mandatory")
    @Size(max = 200)
    String password;
    @NotBlank(message = "Role is mandatory")
    @Size(max = 50)
    String role;
//    @NotNull(message = "Created Date is mandatory")
    Timestamp created_date;
//    @NotNull(message = "Updated Date is mandatory")
    Timestamp updated_date;

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
