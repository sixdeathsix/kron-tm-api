package com.example.krontmapi.dto;

import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse {

    private Integer object_id;
    private String object_name;
    private String flange_no;
    private String description;
    private ObjectType objectType;
    private Event event;
}
