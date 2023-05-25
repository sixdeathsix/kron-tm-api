package com.example.krontmapi.repository;

import com.example.krontmapi.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value, " +
                   "(select property_value from events where property_id = e.property_id and event_date like CONCAT(subdate(date(e.event_date), 1), '%') order by event_date DESC limit 1) as tmr " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id =:object_id " +
                   "order by e.event_date DESC limit 1", nativeQuery = true)
    Event getLastEventsFromObject(@Param("object_id") Integer object_id);

    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where e.event_date = CURRENT_DATE " +
                   "and o.object_id like %:object_id " +
                   "order by e.event_id DESC", nativeQuery = true)
    List<Event> getListEvents(@Param("object_id") String object_id);

    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where e.event_date between :date_start and :date_end " +
                   "and o.object_id like %:object_id " +
                   "order by e.event_date DESC", nativeQuery = true)
    List<Event> getListEventsWithDate(@Param("object_id") String object_id, @Param("date_start") String date_start, @Param("date_end") String date_end);

    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "order by e.event_id DESC limit 5", nativeQuery = true)
    List<Event> getListEventsFromObject(@Param("object_id") Integer object_id);

    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "and e.event_date between :date_start and :date_end " +
                   "order by event_id DESC;", nativeQuery = true)
    List<Event> getListEventsFromObjectWithDate(@Param("object_id") Integer object_id, @Param("date_start") String date_start, @Param("date_end") String date_end);

    @Query(value = "select * from events where property_id = :property_id order by event_id desc limit 1", nativeQuery = true)
    Event getLastByPropertyId(@Param("property_id") Integer property_id);

    @Query(value = "select e.event_id, e.event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "and e.event_date like concat(:date, '%') " +
                   "order by e.event_date", nativeQuery = true)
    List<Event> getTwohoursFromObject(@Param("object_id") Integer object_id, @Param("date") String date);

    @Query(value = "select coalesce((select e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "and e.event_date like CONCAT(subdate(date(:date), 1), '%') " +
                   "order by e.event_date DESC limit 1), 0)", nativeQuery = true)
    Float getTomorrowValue(@Param("object_id") Integer object_id, @Param("date") LocalDateTime date);

    @Query(value = "select e.event_id, date(e.event_date) as event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "and e.event_date like CONCAT(:date, '%') " +
                   "and e.event_date = (select max(e2.event_date) from events e2 where date(e2.event_date) = date(e.event_date))", nativeQuery = true)
    List<Event> getCheckerBoardFromObject(@Param("object_id") Integer object_id, @Param("date") String date);

    @Query(value = "select e.event_id, date(e.event_date) as event_date, e.category_id, e.event_type_id, e.property_id, e.property_value " +
                   "from events e " +
                   "left join properties p on p.property_id = e.property_id " +
                   "left join objects o on o.object_id = p.object_id " +
                   "where o.object_id = :object_id " +
                   "and e.event_date between :start and :end " +
                   "and e.event_date = (select max(e2.event_date) " +
                   "from events e2 " +
                   "where date(e2.event_date) = date(e.event_date))", nativeQuery = true)
    List<Event> getTrends(@Param("start") String start, @Param("end") String end, @Param("object_id") Integer object_id);
}