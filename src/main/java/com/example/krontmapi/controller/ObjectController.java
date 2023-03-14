package com.example.krontmapi.controller;

import com.example.krontmapi.service.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/object")
@RequiredArgsConstructor
public class ObjectController {

    private final ObjectService objectService;

    @GetMapping
    public ResponseEntity getAllObjects() {
        try {
            return ResponseEntity.ok(objectService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneObject(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(objectService.getOneObject(id));
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
}
