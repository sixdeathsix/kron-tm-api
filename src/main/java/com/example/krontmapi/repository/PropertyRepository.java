package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Integer> {
    Property findByObject_Object_idAndProperty_id(Integer object_id, Integer property_type_id);
}
