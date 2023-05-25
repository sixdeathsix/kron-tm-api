package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectPropertiesResponse;
import com.example.krontmapi.entity.Property;
import com.example.krontmapi.entity.PropertyType;
import com.example.krontmapi.entity.ValueType;
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
    private final ObjectRepository objectRepository;
    private final EventRepository eventRepository;
    private final ValueTypeRepository valueTypeRepository;

    public List<ObjectPropertiesResponse> getObjectProperties(Integer id) throws Exception {

        var object = objectRepository.findById(id).get();

        var properties = propertyRepository.findByObject(object);

        if (properties.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        List<ObjectPropertiesResponse> propertiesDto = new ArrayList<>();

        for (Property prp : properties) {

            var event = eventRepository.getLastByPropertyId(prp.getProperty_id());

            ObjectPropertiesResponse propertyDto = ObjectPropertiesResponse.builder()
                    .flange_no(object.getFlange_no())
                    .property_type(prp.getPropertyType().getProperty_type())
                    .path(prp.getPath())
                    .value(event == null ? null : event.getProperty_value())
                    .value_type(prp.getProperty_value_type().getValue_type())
                    .event_date(event == null ? null : event.getEvent_date())
                    .event_type(event == null ? null : event.getEventType().getEvent_type())
                    .property_id(prp.getProperty_id())
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

    public List<ValueType> getAllValues() throws Exception {
        var values = valueTypeRepository.findAll();

        if (values.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return values;
    }

}
