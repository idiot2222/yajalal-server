package me.bogeun.yajalal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class League {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "league_name", unique = true, length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    private Integer limitOfTeam;

}
