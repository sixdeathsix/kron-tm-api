package com.example.krontmapi.service;

import com.example.krontmapi.dto.MonitoringResponse;
import com.example.krontmapi.dto.ObjectResponse;
import com.example.krontmapi.entity.ObjectType;
import com.example.krontmapi.entity.Property;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.ObjectTypeRepository;
import com.example.krontmapi.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PropertyRepository propertyRepository;

    public List<ObjectResponse> getAllObjects() throws Exception {
        var objects = objectRepository.findAll();

        if (objects.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        List<ObjectResponse> objectsDto = new ArrayList<>();

        for (Object obj : objects) {

            List<Property> properties = propertyRepository.findByObject(obj);

            ObjectResponse objectDto = ObjectResponse.builder()
                    .object_id(obj.getObject_id())
                    .object_name(obj.getObject_name())
                    .object_type(obj.getObjectType().getObject_type())
                    .flange_no(obj.getFlange_no())
                    .description(obj.getDescription())
                    .coor_x(obj.getLoc_x())
                    .coor_y(obj.getLoc_y())
                    .properties(properties)
                    .build();

            objectsDto.add(objectDto);
        }

        return objectsDto;
    }

    public List<MonitoringResponse> getAllMonitoringObjects() throws Exception {

        var objects = objectRepository.findAll();

        if (objects.isEmpty()) {
            throw new Exception("Объекты не найдены");
        }

        List<MonitoringResponse> objectsDto = new ArrayList<>();

        for (Object obj : objects) {

            var event = eventRepository.getLastEventsFromObject(obj.getObject_id());
            var tmr = event == null ? 0 : eventRepository.getTomorrowValue(obj.getObject_id(), event.getEvent_date());

            MonitoringResponse objectDto = MonitoringResponse.builder()
                    .object_id(obj.getObject_id())
                    .object_name(obj.getObject_name())
                    .object_type(obj.getObjectType().getObject_type())
                    .flange_no(obj.getFlange_no())
                    .description(obj.getDescription())
                    .event_type(event == null ? null : event.getEventType().getEvent_type())
                    .event_date(event == null ? null : event.getEvent_date())
                    .value(event == null ? null : event.getProperty_value() - tmr)
                    .tomorrow_value(event == null ? null : tmr)
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

    public Object createObject(Object object) throws Exception {

        var type = objectTypeRepository.findById(object.getObjectType().getObject_type_id());
        object.setObjectType(type.get());

        return objectRepository.save(object);

    }

    public Integer deleteObject(Integer id) {

        objectRepository.deleteById(id);

        return id;
    }

    public Object updateObject(Object object) throws Exception {

        Object obj = objectRepository.findById(object.getObject_id()).get();
        obj.setObject_name(object.getObject_name());
        obj.setFlange_no(object.getFlange_no());
        obj.setDescription(object.getDescription());
        obj.setLoc_x(object.getLoc_x());
        obj.setLoc_y(object.getLoc_y());

        return objectRepository.save(obj);
    }


}
