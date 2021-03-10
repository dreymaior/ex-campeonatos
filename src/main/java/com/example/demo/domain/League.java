package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class League {
    private String name;
    private Date beginDate;
    private Date endDate;
    private List<Team> contenders = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Team> getContenders() {
        return contenders;
    }

    public void addContender(Team team) {
        contenders.add(team);
    }

    public static class RankingComparator implements Comparator<Team> {
        private League league;

        public RankingComparator(League league) {
            this.league = league;
        }

        // Compara a pontuação dos times no mesmo campeonato
        @Override
        public int compare(Team o1, Team o2) {
            return o2.getPointsByLeague().get(league).compareTo(o1.getPointsByLeague().get(league));
        }
    }
}
