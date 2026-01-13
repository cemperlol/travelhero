package com.travel.hero.trip.model;

import com.travel.hero.attachment.enumeration.AttachmentType;
import com.travel.hero.attachment.model.Attachment;
import com.travel.hero.trip.enumeration.TripStatus;
import com.travel.hero.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status = TripStatus.DREAM;

    @Embedded
    private TripDates dates;

    private BigDecimal budget;

    @Column(nullable = false)
    private String color = "#FFFFFF";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("uploadedAt DESC")
    private List<Attachment> attachments = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneOffset.UTC);

    @Version
    private Long version;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(ZoneOffset.UTC);
    }

    public Attachment addAttachment(
            String filename,
            String contentType,
            Long size,
            String storageKey,
            AttachmentType type
    ) {
        Attachment attachment = Attachment.create(
                this,
                filename,
                contentType,
                size,
                storageKey,
                type
        );
        attachments.add(attachment);

        return attachment;
    }

    public void removeAttachment(Attachment attachment) {
        attachments.remove(attachment);
    }

    public static Trip create(
            String name,
            String description,
            TripDates dates,
            BigDecimal budget,
            String color,
            User user
    ) {
        Trip trip = new Trip();
        trip.name = name;
        trip.description = description;
        trip.dates = dates;
        trip.budget = budget;
        trip.color = color;
        trip.user = user;

        return trip;
    }
}

