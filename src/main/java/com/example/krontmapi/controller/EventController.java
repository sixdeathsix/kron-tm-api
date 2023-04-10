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

    @GetMapping()
    public ResponseEntity getObjectEvents(@RequestParam String id, @RequestParam String date_start, @RequestParam String date_end) {
        try {
            return ResponseEntity.ok(eventService.getObjectEvents(id, date_start, date_end));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getObjectEventsFromObject(@PathVariable Integer id, @RequestParam String date_start, @RequestParam String date_end) {
        try {
            return ResponseEntity.ok(eventService.getObjectEventsFromObject(id, date_start, date_end));
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

    @GetMapping("/twohours")
    public ResponseEntity getTwohoursFromObject(@RequestParam Integer id, @RequestParam String date) {
        try {
            return ResponseEntity.ok(eventService.getTwohoursFromObject(id, date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/checkerboard")
    public ResponseEntity getCheckerBoardFromObject(@RequestParam Integer id, @RequestParam String date) {
        try {
            return ResponseEntity.ok(eventService.getCheckerBoardFromObject(id, date));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/trends")
    public ResponseEntity getTrendsFromObject(@RequestParam String start, @RequestParam String end, @RequestParam Integer id) {
        try {
            return ResponseEntity.ok(eventService.getTrendsFromObject(start, end, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
