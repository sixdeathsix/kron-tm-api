package com.example.krontmapi.repository;

import com.example.krontmapi.entity.EventType;
import com.example.krontmapi.entity.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRepository extends JpaRepository<EventType, Integer> {
}
