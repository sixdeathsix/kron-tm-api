package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Property;
import com.example.krontmapi.entity.PropertyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyLogRepository extends JpaRepository<PropertyLog, Integer> {
}
