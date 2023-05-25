package com.example.krontmapi.service;

import com.example.krontmapi.dto.CheckerBoardResponse;
import com.example.krontmapi.dto.ObjectEventsResponse;
import com.example.krontmapi.dto.TwohoursResponse;
import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.EventType;
import com.example.krontmapi.repository.EventRepository;
import com.example.krontmapi.repository.EventTypeRepository;
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

    private List<CheckerBoardResponse> getCheckerBoardResponses(List<CheckerBoardResponse> checkerBoardDto, List<Event> events) {
        for (Event evt : events) {

            var tmr = eventRepository.getTomorrowValue(evt.getProperty().getObject().getObject_id(), evt.getEvent_date());

            CheckerBoardResponse dto = CheckerBoardResponse.builder()
                    .object_id(evt.getProperty().getObject().getObject_id())
                    .object_name(evt.getProperty().getObject().getObject_name())
                    .event_date(evt.getEvent_date())
                    .value(evt.getProperty_value() - tmr)
                    .build();

            checkerBoardDto.add(dto);
        }

        return checkerBoardDto;
    }

    public List<ObjectEventsResponse> getObjectEvents(String id, String date_start, String date_end) throws Exception {

        List<ObjectEventsResponse> eventsDto = new ArrayList<>();

        List<Event> events = null;

        if (Objects.equals(date_start, "null") || Objects.equals(date_end, "null")) {
            events = eventRepository.getListEvents(id);
        } else {
            events = eventRepository.getListEventsWithDate(id, date_start, date_end);
        }

        if (events.isEmpty()) {
            throw new Exception("Ничего не найдено");
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

        if (events.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return getObjectEventsResponses(eventsDto, events);
    }

    public List<CheckerBoardResponse> getCheckerBoardFromObject(Integer id, String date) throws Exception {

        List<CheckerBoardResponse> checkerBoardDto = new ArrayList<>();

        List<Event> events = eventRepository.getCheckerBoardFromObject(id, date);

        if (events.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return getCheckerBoardResponses(checkerBoardDto, events);
    }

    public List<TwohoursResponse> getTwohoursFromObject(Integer id, String date) throws Exception {

        List<TwohoursResponse> twohoursDto = new ArrayList<>();

        List<Event> events = eventRepository.getTwohoursFromObject(id, date);

        if (events.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        for (Event evt : events) {

            var tmr = eventRepository.getTomorrowValue(evt.getProperty().getObject().getObject_id(), evt.getEvent_date());

            TwohoursResponse dto = TwohoursResponse.builder()
                    .object_id(evt.getProperty().getObject().getObject_id())
                    .object_name(evt.getProperty().getObject().getObject_name())
                    .event_date(evt.getEvent_date())
                    .flange_no(evt.getProperty().getObject().getFlange_no())
                    .value(evt.getProperty_value() - tmr)
                    .build();

            twohoursDto.add(dto);
        }

        return twohoursDto;
    }

    public List<CheckerBoardResponse> getTrendsFromObject(String start, String end, Integer id) throws Exception {

        List<CheckerBoardResponse> trendsBoardDto = new ArrayList<>();

        List<Event> events = eventRepository.getTrends(start, end, id);

        if (events.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return getCheckerBoardResponses(trendsBoardDto, events);
    }

    public List<EventType> getAllTypes() throws Exception {
        var eventTypes = eventTypeRepository.findAll();

        if (eventTypes.isEmpty()) {
            throw new Exception("Ничего не найдено");
        }

        return eventTypes;
    }

}
