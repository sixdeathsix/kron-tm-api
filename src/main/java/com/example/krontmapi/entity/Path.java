package com.example.krontmapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paths")
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer path_id;

    private String path;
}
