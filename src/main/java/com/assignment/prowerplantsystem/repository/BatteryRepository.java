package com.assignment.prowerplantsystem.repository;

import com.assignment.prowerplantsystem.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer> {

}
