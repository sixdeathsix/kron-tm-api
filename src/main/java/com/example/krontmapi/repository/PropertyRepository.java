package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Object;
import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findByObject(Object object);
}
