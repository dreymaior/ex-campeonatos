package com.example.demo.service;

import com.example.demo.domain.Contender;
import com.example.demo.domain.League;
import com.example.demo.domain.Team;
import com.example.demo.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LeagueService implements Serializable {
    @Autowired
    private LeagueRepository repository;
    @Autowired
    private TeamService teamService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("pt", "BR"));

    public List<League> listLeagues() {
        return this.repository.findAll();
    }

    public League getLeague(Long id) {
        return this.repository.findById(id).get();
    }

    public League createLeague(Map<String, String> params) throws ParseException {
        League newLeague = new League();
        newLeague.setName(params.get("name"));
        newLeague.setBeginDate(dateFormat.parse(params.get("begin")));
        newLeague.setEndDate(dateFormat.parse(params.get("end")));
        this.repository.save(newLeague);
        return newLeague;
    }

    public League addTeamOnLeague(Long id, Map<String, Long> params) {
        League league = this.getLeague(id);
        Team team = this.teamService.getTeam(params.get("teamId"));
        Long points = params.get("points");

        Contender contender = new Contender();
        contender.setLeague_id(league.getId());
        contender.setLeague(league);
        contender.setTeam_id(team.getId());
        contender.setTeam(team);
        contender.setPoints(points);
        league.getContenders().add(contender);
        this.repository.save(league);
        return league;
    }

    public List<Contender> getLeagueRanking(Long id) {
        League currentLeague = this.getLeague(id);
        return currentLeague.getContenders();
    }
}
