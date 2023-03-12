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
@Table(name = "value_types")
public class ValueType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer value_type_id;

    private String value_type;
}
