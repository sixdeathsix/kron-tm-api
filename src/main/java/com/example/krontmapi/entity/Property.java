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
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer property_id;

    @ManyToOne
    @JoinColumn(name = "value_type_id")
    private ValueType property_value_type;

    @OneToOne
    @JoinColumn(name = "object_id")
    private Object object;

    @ManyToOne
    @JoinColumn(name = "property_type_id")
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "path_id")
    private Path path;
}
