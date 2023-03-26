package com.example.krontmapi.controller;

import com.example.krontmapi.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @GetMapping("/object/{id}")
    public ResponseEntity getObjectProperties(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(propertyService.getObjectProperties(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/types")
    public ResponseEntity getAllEventTypes() {
        try {
            return ResponseEntity.ok(propertyService.getAllTypes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
