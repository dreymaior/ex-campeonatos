package com.example.demo.domain;

import java.util.HashMap;
import java.util.Map;

public class Team {
    private String name;
    private Map<League, Integer> pointsByLeague = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<League, Integer> getPointsByLeague() {
        return pointsByLeague;
    }

    public Integer getPointsOnLeague(League league) {
        return this.pointsByLeague.get(league);
    }

    public void addTeamOnLeague(League league) {
        this.pointsByLeague.put(league, 0);
    }

    public void addTeamOnLeague(League league, Integer points) {
        this.pointsByLeague.put(league, points);
    }
}
