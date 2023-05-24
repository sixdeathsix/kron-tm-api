package com.example.krontmapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer property_id;

    @ManyToOne
    @JoinColumn(name = "value_type_id", nullable = false)
    private ValueType property_value_type;

    @ManyToOne
    @JoinColumn(name = "object_id", nullable = false)
    private Object object;

    @ManyToOne
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @Column(nullable = false)
    private String path;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PropertyLog> propertyLogs;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Event> events;
}
