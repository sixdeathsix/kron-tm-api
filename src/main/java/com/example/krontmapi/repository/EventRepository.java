package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value="select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id from events e left join properties p on p.property_id = e.property_id left join objects o on o.object_id = p.object_id where o.object_id =:object_id order by event_id DESC limit 1", nativeQuery=true)
    Event getLastEventsFromObject(@Param("object_id") Integer object_id);

    @Query(value="select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id from events e left join properties p on p.property_id = e.property_id left join objects o on o.object_id = p.object_id where o.object_id = :object_id order by event_id DESC limit 5", nativeQuery=true)
    List<Event> getListEventsFromObject(@Param("object_id") Integer object_id);

    @Query(value="select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id from events e left join properties p on p.property_id = e.property_id left join objects o on o.object_id = p.object_id where o.object_id = :object_id and e.event_date between :date_start and :date_end order by event_id DESC;", nativeQuery=true)
    List<Event> getListEventsFromObjectWithDate(@Param("object_id") Integer object_id, @Param("date_start") String date_start, @Param("date_end") String date_end);

    @Query(value="select * from events where property_id = :property_id order by event_id desc limit 1", nativeQuery = true)
    Event getLastByPropertyId(@Param("property_id") Integer property_id);
}
