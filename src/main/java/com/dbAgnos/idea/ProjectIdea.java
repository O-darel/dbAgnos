package com.dbAgnos.idea;

import com.dbAgnos.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // Better for descriptions, cross-db usually supported or standard varchar
    private String description;

    private String tags; // Comma separated tags e.g. "java,springboot,react"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // DB link to user
    private User user;
}
