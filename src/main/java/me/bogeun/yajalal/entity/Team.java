package me.bogeun.yajalal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "team_name", unique = true, length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @Size(min = 9, max = 100)
    private Integer limitOfPlayer;

}
