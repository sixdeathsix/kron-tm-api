package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
    Event findByPropertyProperty_id(Integer property_id);
}
