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
@Table(name = "objects")
public class Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer object_id;
    private String flange_no;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "object_type_id")
    private ObjectType objectType;

}
