package com.assignment.prowerplantsystem.repository;

import com.assignment.prowerplantsystem.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer> {

    @Query(value = "SELECT b FROM Battery b WHERE b.name IN (:names)")
    List<Battery> findBatteriesByNameList(@Param("names") List<String> names);
}
