package com.company.monitoring.entity;

import com.company.monitoring.enums.WaterType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "measurements")
@Entity
@Getter
@Setter
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name= "water_volume")
    private double water;

    @Enumerated(EnumType.STRING)
    @Column(name = "water_type", nullable = false)
    private WaterType waterType;

    @Column(name = "gas")
    private double gas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}