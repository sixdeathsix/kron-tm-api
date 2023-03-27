package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectEventsResponse;
import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.EventType;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.EventTypeRepository;
import com.example.krontmapi.repository.ObjectRepository;
import com.example.krontmapi.repository.PropertyLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventService {

    private final ObjectRepository objectRepository;
    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;

    private final PropertyLogRepository propertyLogRepository;

    public List<ObjectEventsResponse> getOneObjectEvents(Integer id, String date_start, String date_end) throws Exception {

        if (!objectRepository.existsById(id)) {
            throw new Exception("Объект не найден");
        }

        List<ObjectEventsResponse> eventsDto = new ArrayList<>();

        List<Event> events = null;

        if (Objects.equals(date_start, "null") || Objects.equals(date_end, "null")) {
            events = eventRepository.getListEventsFromObject(id);
        } else {
            events = eventRepository.getListEventsFromObjectWithDate(id, date_start, date_end);
        }

        for (Event evt : events) {

            var propertyLog = propertyLogRepository.getLogByEvent(evt.getEvent_id());

            ObjectEventsResponse eventDto = ObjectEventsResponse.builder()
                    .event_id(evt.getEvent_id())
                    .event_date(evt.getEvent_date())
                    .property(evt.getProperty())
                    .eventType(evt.getEventType())
                    .category(evt.getCategory())
                    .property_log_id(propertyLog.getProperty_log_id())
                    .value(propertyLog.getValue())
                    .update_date(propertyLog.getUpdate_date())
                    .valueType(propertyLog.getValueType())
                    .build();

            eventsDto.add(eventDto);
        }

        return eventsDto;
    }

    public List<EventType> getAllTypes() throws Exception {
        var eventTypes = eventTypeRepository.findAll();

        if (eventTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return eventTypes;
    }

}
