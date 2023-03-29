package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectPropertiesResponse;
import com.example.krontmapi.entity.Property;
import com.example.krontmapi.entity.PropertyType;
import com.example.krontmapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final PropertyLogRepository propertyLogRepository;
    private final ObjectRepository objectRepository;
    private final EventRepository eventRepository;

    public List<ObjectPropertiesResponse> getObjectProperties(Integer id) throws Exception {

        var object = objectRepository.findById(id).get();

        var properties = propertyRepository.findByObject(object);

        if (properties.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        List<ObjectPropertiesResponse> propertiesDto = new ArrayList<>();

        for (Property prp : properties) {

            var propertyLog = propertyLogRepository.getLastByPropertyId(prp.getProperty_id());
            var event = eventRepository.getLastByPropertyId(prp.getProperty_id());

            ObjectPropertiesResponse propertyDto = ObjectPropertiesResponse.builder()
                    .flange_no(object.getFlange_no())
                    .property_type(prp.getPropertyType().getProperty_type())
                    .path(prp.getPath().getPath())
                    .value(propertyLog.getValue())
                    .value_type(propertyLog.getValueType().getValue_type())
                    .event_date(event.getEvent_date())
                    .event_type(event.getEventType().getEvent_type())
                    .build();

            propertiesDto.add(propertyDto);
        }

        return propertiesDto;
    }

    public List<PropertyType> getAllTypes() throws Exception {
        var properties = propertyTypeRepository.findAll();

        if (properties.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return properties;
    }

}
