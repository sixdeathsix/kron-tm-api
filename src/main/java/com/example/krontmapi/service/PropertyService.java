package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectPropertiesResponse;
import com.example.krontmapi.entity.Property;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.PropertyLogRepository;
import com.example.krontmapi.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final ObjectRepository objectRepository;
    private final PropertyLogRepository propertyLogRepository;
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
                    .property_id(prp.getProperty_id())
                    .object(prp.getObject())
                    .propertyType(prp.getPropertyType())
                    .path(prp.getPath())
                    .property_log_id(propertyLog.getProperty_log_id())
                    .value(propertyLog.getValue())
                    .update_date(propertyLog.getUpdate_date())
                    .valueType(propertyLog.getValueType())
                    .event_id(event.getEvent_id())
                    .event_date(event.getEvent_date())
                    .eventType(event.getEventType())
                    .category(event.getCategory())
                    .build();

            propertiesDto.add(propertyDto);
        }

        return propertiesDto;
    }

    public List<Property> getAllTypes() throws Exception {
        var properties = propertyRepository.findAll();

        if (properties.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return properties;
    }

}
