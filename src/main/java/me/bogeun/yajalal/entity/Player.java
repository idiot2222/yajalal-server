package me.bogeun.yajalal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "player_name", length = 10)
    private String name;

    private Integer length;
    private Integer weight;

    @Column(length = 100)
    private String description;

    @Enumerated(EnumType.STRING)
    private Position mainPosition;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "sub_positions")
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Position> subPositions = new ArrayList<>();

}
