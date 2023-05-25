package com.example.krontmapi.controller;

import com.example.krontmapi.entity.Property;
import com.example.krontmapi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/property")
    public ResponseEntity createPropertyToObject(@RequestBody Property property) {
        try {
            return ResponseEntity.ok(adminService.createPropertyForObject(property));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/property")
    public ResponseEntity deleteObjectProperty(@RequestParam Integer property_id) {
        try {
            return ResponseEntity.ok(adminService.deleteObjectProperty(property_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
