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
public class TwohoursResponse {
    private Integer object_id;
    private String object_name;
    private String flange_no;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime event_date;
    private Float value;
}
