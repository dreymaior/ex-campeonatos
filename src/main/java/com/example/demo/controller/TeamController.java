package com.example.demo.controller;

import com.example.demo.domain.Team;
import com.example.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping("/times")
    public List<Team> listTeams() {
        return this.service.listTeams();
    }

    @GetMapping("/times/{id}")
    public Team getTeam(@PathVariable Long id) {
        return this.service.getTeam(id);
    }

    @PostMapping("/times")
    public Team createTeam(@RequestBody Map<String, String> json) {
        return this.service.createTeam(json);
    }

    @PostMapping("/times/{id}/campeonatos")
    public Team addTeamOnLeague(@PathVariable Long id, Map<String, Long> json) {
        return this.service.addTeamOnLeague(id, json);
    }
}
