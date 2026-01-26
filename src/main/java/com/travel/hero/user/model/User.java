package com.travel.hero.user.model;

import com.travel.hero.currency.enumeration.CurrencyCode;
import com.travel.hero.trip.model.Trip;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyCode currencyCode = CurrencyCode.USD;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt DESC")
    private List<Trip> trips = new ArrayList<>();

    public void addTrip(Trip trip) {
        trips.add(trip);
        trip.setUser(this);
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
        trip.setUser(null);
    }

    public static User create(
            String email,
            String encodedPassword,
            String username
    ) {
        User user = new User();
        user.email = email;
        user.password = encodedPassword;
        user.username = username;
        user.lastLoginAt = null;

        return user;
    }
}


