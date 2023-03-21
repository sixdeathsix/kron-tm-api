package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectResponse;
import com.example.krontmapi.entity.ObjectType;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.ObjectTypeRepository;
import com.example.krontmapi.repository.PropertyRepository;
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
    private final PropertyRepository propertyRepository;
    private final EventRepository eventRepository;

    public List<ObjectResponse> getAll() throws Exception {

        var objects = objectRepository.findAll();

        if (objects.isEmpty()) {
            throw new Exception("Объекты не найдены");
        }

        List<ObjectResponse> objectsDto = new ArrayList<>();

        for (Object obj : objects) {

            var property = propertyRepository.findByObject_Object_idAndProperty_id(obj.getObject_id(), 1);
            var event = eventRepository.findByPropertyProperty_id(property.getProperty_id());

            ObjectResponse objectDto = ObjectResponse.builder()
                    .object_id(obj.getObject_id())
                    .object_name(obj.getObject_name())
                    .flange_no(obj.getFlange_no())
                    .description(obj.getDescription())
                    .objectType(obj.getObjectType())
                    .property(event.getEventType())
                    .build();

            objectsDto.add(objectDto);
        }

        return objectsDto;
    }

    public Object getOneObject(Integer id) throws Exception {

        if (!objectRepository.existsById(id)) {
            throw new Exception("Объект не найден");
        }

        return objectRepository.findById(id).get();
    }

    public List<ObjectType> getAllTypes() throws Exception {
        var objectTypes = objectTypeRepository.findAll();

        if (objectTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return objectTypes;
    }

}
