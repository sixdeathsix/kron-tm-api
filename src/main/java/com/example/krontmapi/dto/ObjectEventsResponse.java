package com.example.krontmapi.dto;

import com.example.krontmapi.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEventsResponse {

    private Integer event_id;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime event_date;
    private Property property;
    private EventType eventType;
    private Category category;
    private Integer property_log_id;
    private String value;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime update_date;
    private ValueType valueType;
}
