package com.example.demo.service;

import com.example.demo.domain.Contender;
import com.example.demo.domain.League;
import com.example.demo.domain.Team;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    @Autowired
    private LeagueService leagueService;

    public List<Team> listTeams() {
        return this.repository.findAll();
    }

    public Team getTeam(Long id) {
        return this.repository.findById(id).get();
    }

    public Team createTeam(Map<String, String> params) {
        Team team = new Team();
        team.setName(params.get("name"));
        this.repository.save(team);
        return team;
    }

    public Team addTeamOnLeague(Long id, Map<String, Long> params) {
        League league = this.leagueService.getLeague(params.get("leagueId"));

        if (Objects.nonNull(league)) {
            Team team;
            Long points = params.get("points");
            if (Objects.nonNull(points)) {
                team = this.addTeamOnLeague(id, league, points);
            } else {
                team = this.addTeamOnLeague(id, league);
            }
            return team;
        }
        return null;
    }

    public Team addTeamOnLeague(Long id, League league) {
        Team team = this.getTeam(id);
        Contender contender = new Contender();
        contender.setLeague(league);
        contender.setTeam(team);
        contender.setPoints(0L);
        team.getContends().add(contender);
        this.repository.save(team);
        return team;
    }

    public Team addTeamOnLeague(Long id, League league, Long points) {
        Team team = this.getTeam(id);
        Contender contender = new Contender();
        contender.setLeague(league);
        contender.setTeam(team);
        contender.setPoints(points);
        team.getContends().add(contender);
        this.repository.save(team);
        return team;
    }

    public List<Contender> getTeamLeagues(Long id) {
        Team team = this.getTeam(id);
        return team.getContends();
    }
}
