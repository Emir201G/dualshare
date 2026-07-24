package com.app.dualshare.model;

import com.app.dualshare.enums.MediaType;
import com.app.dualshare.repository.StoryRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "public_id")
    private String publicId;
    @Enumerated(EnumType.STRING)
    @Column(name = "media_type", nullable = false)
    private MediaType mediaType;
    @Column(name = "media_url")
    private String mediaUrl;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @OneToMany(
            mappedBy = "story",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<StoryRecipient> recipients;
}
