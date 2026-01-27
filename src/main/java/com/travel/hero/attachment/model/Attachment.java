package com.travel.hero.attachment.model;

import com.travel.hero.attachment.enumeration.AttachmentType;
import com.travel.hero.trip.model.Trip;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "attachments")
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filename;

    @Column(name = "file_path")
    private String filePath;

    @Column(nullable = false)
    private String contentType;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String storageKey;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttachmentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false, updatable = false)
    private Trip trip;

    public static Attachment create(
            Trip trip,
            String filename,
            String contentType,
            Long size,
            String storageKey,
            AttachmentType type
    ) {
        Attachment attachment = new Attachment();
        attachment.trip = trip;
        attachment.filename = filename;
        attachment.contentType = contentType;
        attachment.size = size;
        attachment.storageKey = storageKey;
        attachment.type = type;
        attachment.uploadedAt = LocalDateTime.now(ZoneOffset.UTC);

        return attachment;
    }
}
