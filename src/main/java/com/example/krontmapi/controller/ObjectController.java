package com.example.krontmapi.controller;

import com.example.krontmapi.service.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.krontmapi.entity.Object;

@RestController
@RequestMapping("/api/v1/object")
@RequiredArgsConstructor
public class ObjectController {

    private final ObjectService objectService;

    @GetMapping()
    public ResponseEntity getAllObjects() {
        try {
            return ResponseEntity.ok(objectService.getAllObjects());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/monitoring")
    public ResponseEntity getAllMonitoringObjects() {
        try {
            return ResponseEntity.ok(objectService.getAllMonitoringObjects());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/types")
    public ResponseEntity getAllObjectTypes() {
        try {
            return ResponseEntity.ok(objectService.getAllTypes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity createObject (@RequestBody Object obj) {
        try {
            return ResponseEntity.ok(objectService.createObject(obj));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
