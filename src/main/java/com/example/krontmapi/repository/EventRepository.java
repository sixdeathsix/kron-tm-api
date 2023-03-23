package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Event;
import com.example.krontmapi.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value="select * from events where property_id=:property_id order by event_id DESC limit 1", nativeQuery=true)
    Event getLastEventFromObject(@Param("property_id") Integer integer);
}
