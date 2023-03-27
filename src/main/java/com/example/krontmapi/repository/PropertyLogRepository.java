package com.example.krontmapi.repository;

import com.example.krontmapi.entity.PropertyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropertyLogRepository extends JpaRepository<PropertyLog, Integer> {

    @Query(value="select e.event_id, pl.property_log_id, pl.update_date, pl.value, pl.property_id, pl.value_type_id from events e left join properties p on p.property_id = e.property_id left join objects o on o.object_id = p.object_id left join property_logs pl on pl.property_id = p.property_id and pl.update_date = e.event_date where e.event_id = :event_id", nativeQuery = true)
    PropertyLog getLogByEvent(@Param("event_id") Integer event_id);

    @Query(value = "select * from property_logs where property_id = :property_id order by property_log_id desc limit 1", nativeQuery = true)
    PropertyLog getLastByPropertyId(@Param("property_id") Integer property_id);
}
