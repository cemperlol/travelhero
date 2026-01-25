package com.travel.hero.user.model;

import com.travel.hero.currency.enumeration.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter @Setter
public class User {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode currencyCode = CurrencyCode.USD;

    public static User create(
            String email,
            String password,
            String username
    ) {
        User user = new User();
        user.email = email;
        user.password = password;
        user.username = username;

        return user;
    }
}


