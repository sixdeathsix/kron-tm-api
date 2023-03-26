package com.example.krontmapi.dto;

import com.example.krontmapi.entity.*;
import com.example.krontmapi.entity.Object;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectPropertiesResponse {
    private Integer property_id;
    private Object object;
    private PropertyType propertyType;
    private Path path;
    private Integer property_log_id;
    private String value;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime update_date;
    private ValueType valueType;
    private Integer event_id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime event_date;
    private EventType eventType;
    private Category category;
}
