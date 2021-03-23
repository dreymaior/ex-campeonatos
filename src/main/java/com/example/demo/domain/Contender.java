package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(ContenderId.class)
public class Contender implements Serializable {

    @Id
    private Long league_id;

    @Id
    private Long team_id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id", insertable = false, updatable = false)
    private League league;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Team team;

    @Column(name = "points")
    private Long points;
}
