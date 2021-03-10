package com.example.demo.controller;

import com.example.demo.domain.League;
import com.example.demo.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LeagueController {
    @Autowired
    private LeagueService service;

    @GetMapping("/campeonatos")
    public List<League> listLeagues() {
        return this.service.listLeagues();
    }

    @GetMapping("/campeonatos/{id}")
    public League getLeague(@PathVariable("id") Integer id) {
        return this.service.getLeague(id);
    }

    @GetMapping("/campeonatos/{id}/tabela")
    public List<Map<String, Integer>> getLeagueRanking(@PathVariable Integer id) {
        return this.service.getLeagueRanking(id);
    }

    @PostMapping("/campeonatos")
    public League createLeague(@RequestBody Map<String, String> json) throws ParseException {
        return this.service.createLeague(json);
    }

    @PostMapping("/campeonatos/{id}/times")
    public Map<String, String> addTeamOnLeague(@PathVariable Integer id, @RequestBody Map<String, Integer> json) {
        return this.service.addTeamOnLeague(id, json);
    }
}
