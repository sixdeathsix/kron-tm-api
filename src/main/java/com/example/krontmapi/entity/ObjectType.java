package com.example.krontmapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "object_types")
public class ObjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer object_type_id;

    private String object_type;

    @OneToMany
    private List<Object> object;
}
