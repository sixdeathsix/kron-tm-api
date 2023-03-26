package com.example.krontmapi.controller;

import com.example.krontmapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity getOneObject(@PathVariable Integer id, @RequestParam String date_start, @RequestParam String date_end) {
        try {
            return ResponseEntity.ok(eventService.getOneObjectEvents(id, date_start, date_end));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/types")
    public ResponseEntity getAllEventTypes() {
        try {
            return ResponseEntity.ok(eventService.getAllTypes());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
