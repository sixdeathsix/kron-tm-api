package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
