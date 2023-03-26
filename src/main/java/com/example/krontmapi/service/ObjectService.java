package com.example.krontmapi.service;

import com.example.krontmapi.dto.MonitoringResponse;
import com.example.krontmapi.entity.ObjectType;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.ObjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.krontmapi.entity.Object;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ObjectService {

    private final ObjectRepository objectRepository;
    private final ObjectTypeRepository objectTypeRepository;
    private final EventRepository eventRepository;

    public List<MonitoringResponse> getAll() throws Exception {

        var objects = objectRepository.findAll();

        if (objects.isEmpty()) {
            throw new Exception("Объекты не найдены");
        }

        List<MonitoringResponse> objectsDto = new ArrayList<>();

        for (Object obj : objects) {

            var event = eventRepository.getLastEventsFromObject(obj.getObject_id());

            MonitoringResponse objectDto = MonitoringResponse.builder()
                    .object_id(obj.getObject_id())
                    .object_name(obj.getObject_name())
                    .flange_no(obj.getFlange_no())
                    .description(obj.getDescription())
                    .objectType(obj.getObjectType())
                    .event(event)
                    .build();

            objectsDto.add(objectDto);
        }

        return objectsDto;
    }

    public List<ObjectType> getAllTypes() throws Exception {
        var objectTypes = objectTypeRepository.findAll();

        if (objectTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return objectTypes;
    }

}
