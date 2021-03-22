package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ContenderId implements Serializable {
    private Long league_id;

    private Long team_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContenderId that = (ContenderId) o;
        return league_id.equals(that.league_id) && team_id.equals(that.team_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(league_id, team_id);
    }
}
