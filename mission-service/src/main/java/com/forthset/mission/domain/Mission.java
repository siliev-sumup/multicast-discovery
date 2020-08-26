package com.forthset.mission.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "missions")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
