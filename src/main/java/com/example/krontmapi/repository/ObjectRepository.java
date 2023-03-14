package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectRepository extends JpaRepository<Object, Integer> {

}
