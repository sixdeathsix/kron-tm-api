package com.example.krontmapi.service;

import com.example.krontmapi.entity.Property;
import com.example.krontmapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final PropertyRepository propertyRepository;

    public Property createPropertyForObject(Property property) throws Exception {
        return propertyRepository.save(property);
    }

    public Integer deleteObjectProperty(Integer property_id) throws Exception {

        propertyRepository.deleteById(property_id);

        return property_id;
    }

}
