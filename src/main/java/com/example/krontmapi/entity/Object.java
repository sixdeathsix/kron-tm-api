package com.example.krontmapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "objects")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer object_id;

    private String object_name;
    private String flange_no;
    private String description;

    @ManyToOne
    @JoinColumn(name = "object_type_id")
    private ObjectType objectType;
    private String loc_x;
    private String loc_y;

    @OneToMany(mappedBy = "object", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Property> properties;
}
