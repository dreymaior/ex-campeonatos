package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @OrderBy("points desc")
    private List<Contender> contends;
}
