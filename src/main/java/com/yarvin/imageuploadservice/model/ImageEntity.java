package com.yarvin.imageuploadservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String originalImagePath;

    private String enhancedImagePath;

    private Timestamp uploaded_at;

    private Status status;

    @OneToOne
    private UserEnity userId;

    private Long sizeBefore;

    private Long sizeAfter;
}
