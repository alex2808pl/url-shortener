package de.telran.urlshortener.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "urls")
public class ShortUrlEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", unique = true)
    private String key;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String fullUrl;

    @Column(nullable = false)
    private Long clickCount;
}