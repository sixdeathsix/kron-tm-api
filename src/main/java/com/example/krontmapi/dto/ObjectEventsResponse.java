package com.example.krontmapi.dto;

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
    private String object_name;
    private String flange_no;
    private String event_type;
    private String category;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime event_date;
    private String property_type;
    private Float value;
    private String loc_x;
    private String loc_y;
}
