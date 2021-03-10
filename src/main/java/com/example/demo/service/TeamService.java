package com.example.demo.service;

import com.example.demo.domain.League;
import com.example.demo.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private LeagueService leagueService;
    private List<Team> teams = new ArrayList<>();

    public List<Team> listTeams() {
        return this.teams;
    }

    public Team getTeam(Integer id) {
        return this.teams.get(id);
    }

    public Team createTeam(Map<String, String> params) {
        Team team = new Team();
        team.setName(params.get("name"));
        this.teams.add(team);
        return team;
    }

    public Map<String, String> addTeamOnLeague(Integer id, Map<String, Integer> params) {
        Map<String, String> response = new HashMap<>();
        League league = this.leagueService.getLeague(params.get("leagueId"));

        if (Objects.nonNull(league)) {
            Team team;
            Integer points = params.get("points");
            if (Objects.nonNull(points)) {
                team = this.addTeamOnLeague(id, league, points);
            } else {
                team = this.addTeamOnLeague(id, league);
            }
            league.addContender(team);
            response.put("message", "Success.");
            return response;
        }
        response.put("message", "Failed.");
        response.put("reason", "Campeonato não pode ser nulo.");
        return response;
    }

    public Team addTeamOnLeague(Integer id, League league) {
        Team team = this.teams.get(id);
        team.addTeamOnLeague(league);
        return team;
    }

    public Team addTeamOnLeague(Integer id, League league, Integer points) {
        Team team = this.teams.get(id);
        team.addTeamOnLeague(league, points);
        return team;
    }

    public List<Team> getTeamsOnLeague(Integer id) {
        League league = this.leagueService.getLeague(id);
        return this.teams.stream()
                .filter(team -> team.getPointsByLeague().keySet().contains(league))
                .collect(Collectors.toList());
    }
}
