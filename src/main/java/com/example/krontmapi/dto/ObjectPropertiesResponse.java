package com.example.krontmapi.dto;

import com.example.krontmapi.entity.*;
import com.example.krontmapi.entity.Object;
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
public class ObjectPropertiesResponse {

    private String flange_no;
    private String property_type;
    private String path;
    private Float value;
    private String value_type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime event_date;
    private String event_type;

}
