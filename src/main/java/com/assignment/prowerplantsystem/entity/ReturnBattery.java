package com.assignment.prowerplantsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class ReturnBattery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "battery_id", referencedColumnName = "battery_id")
    private Battery battery;

    private Integer totalCapacity;
    private Integer averageCapacity;


}
