package com.example.krontmapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "property_logs")
public class PropertyLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer property_log_id;

    private Float value;

    private LocalDateTime update_date;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "value_type_id")
    private ValueType valueType;

}
