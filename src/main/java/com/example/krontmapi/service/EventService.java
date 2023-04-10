package com.example.krontmapi.service;

import com.example.krontmapi.dto.ObjectEventsResponse;
import com.example.krontmapi.dto.TwohoursResponse;
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

    private final EventTypeRepository eventTypeRepository;
    private final EventRepository eventRepository;

    private List<ObjectEventsResponse> getObjectEventsResponses(List<ObjectEventsResponse> eventsDto, List<Event> events) {
        for (Event evt : events) {

            ObjectEventsResponse eventDto = ObjectEventsResponse.builder()
                    .object_name(evt.getProperty().getObject().getObject_name())
                    .flange_no(evt.getProperty().getObject().getFlange_no())
                    .event_type(evt.getEventType().getEvent_type())
                    .category(evt.getCategory().getCategory())
                    .event_date(evt.getEvent_date())
                    .property_type(evt.getProperty().getPropertyType().getProperty_type())
                    .value(evt.getProperty_value())
                    .loc_x(evt.getProperty().getObject().getLoc_x())
                    .loc_y(evt.getProperty().getObject().getLoc_y())
                    .build();

            eventsDto.add(eventDto);
        }

        return eventsDto;
    }

    public List<ObjectEventsResponse> getObjectEvents(String id, String date_start, String date_end) throws Exception {

        List<ObjectEventsResponse> eventsDto = new ArrayList<>();

        List<Event> events = null;

        if (Objects.equals(date_start, "null") || Objects.equals(date_end, "null")) {
            events = eventRepository.getListEvents(id);
        } else {
            events = eventRepository.getListEventsWithDate(id, date_start, date_end);
        }

        return getObjectEventsResponses(eventsDto, events);
    }

    public List<ObjectEventsResponse> getObjectEventsFromObject(Integer id, String date_start, String date_end) throws Exception {

        List<ObjectEventsResponse> eventsDto = new ArrayList<>();

        List<Event> events = null;

        if (Objects.equals(date_start, "null") || Objects.equals(date_end, "null")) {
            events = eventRepository.getListEventsFromObject(id);
        } else {
            events = eventRepository.getListEventsFromObjectWithDate(id, date_start, date_end);
        }

        return getObjectEventsResponses(eventsDto, events);
    }

    public List<TwohoursResponse> getTwohoursFromObject(Integer id, String date) {

        List<TwohoursResponse> twohoursDto = new ArrayList<>();

        List<Event> events = eventRepository.getTwohoursFromObject(id, date);

        for (Event evt : events) {

            TwohoursResponse dto = TwohoursResponse.builder()
                    .object_id(evt.getProperty().getObject().getObject_id())
                    .object_name(evt.getProperty().getObject().getObject_name())
                    .event_date(evt.getEvent_date())
                    .flange_no(evt.getProperty().getObject().getFlange_no())
                    .value(evt.getProperty_value())
                    .build();

            twohoursDto.add(dto);
        }

        return twohoursDto;
    }

    public List<EventType> getAllTypes() throws Exception {
        var eventTypes = eventTypeRepository.findAll();

        if (eventTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return eventTypes;
    }

}
