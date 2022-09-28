package me.bogeun.yajalal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "player_name", length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer height;

    @Column(nullable = false)
    private Integer weight;

    @Column(length = 100)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Position mainPosition;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "sub_positions")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Position> subPositions = new HashSet<>();

}
