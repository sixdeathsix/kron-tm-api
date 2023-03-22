package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findByProperty(Property property);
}
